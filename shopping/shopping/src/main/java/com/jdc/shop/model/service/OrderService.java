package com.jdc.shop.model.service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.page.PageResult;
import com.jdc.shop.model.dto.vo.OrderDetailsVo;
import com.jdc.shop.model.dto.vo.OrderListVo;
import com.jdc.shop.utilities.DateTimes;
import com.jdc.shop.utilities.LoginUser;
import com.jdc.shop.utilities.Strings;

public class OrderService {

	private DataSource dataSource;
	private AddressService addressService;
	private OrderItemService itemService;
	private OrderMessageService messageService;
	private OrderDeliveryService deliveryService;
	private OrderPaidService paidService;

	public OrderService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		addressService = new AddressService(dataSource);
		itemService = new OrderItemService(dataSource);
		messageService = new OrderMessageService(dataSource);
		deliveryService = new OrderDeliveryService(dataSource);
		paidService = new OrderPaidService(dataSource);
	}

	
	private static final String LIST_QUERY_BASE = """
			select p.id, p.purchase_date orderDate, p.status, p.account_id, 
			p.remark, p.account_id customerId, a.name customerName, p.address_id,  
			sum(pi.quantity * pi.unit_price) totalAmount 
			from purchase p join account a on p.account_id = a.id 
			join purchase_item pi on p.id = pi.purchase_id""";
	
	private static final String COUNT_QUERY_BASE = """
			select count(distinct(p.id)) 
			from purchase p join account a on p.account_id = a.id 
			join purchase_item pi on p.id = pi.purchase_id""";

	private static final String LIST_QUERY_GROUP_BY = """
			group by p.id, p.purchase_date, p.status, p.account_id, 
			p.remark, p.account_id, a.name, p.address_id""";
	
	public List<OrderListVo> search(LoginUser login, String status, LocalDate dateFrom) {
		
		var sqlByRole = switch (login.getRole()) {
		case "Customer" -> "%s where p.account_id = ?";
		case "Delivery" -> "%s join delivery d on p.id = d.purchase_id where d.account_id = ?";
		default -> "%s where 1 = 1" ;
		};
		
		var sql = new StringBuffer(sqlByRole.formatted(LIST_QUERY_BASE));
		var params = new ArrayList<Object>();
		var list = new ArrayList<OrderListVo>();
		
		if(login.getRole().equals("Customer") || login.getRole().endsWith("Delivery")) {
			params.add(login.getId());
		}
		
		if(Strings.isNotBlanck(status)) {
			sql.append(" and p.status = ?");
			params.add(status);
		}
		
		if(null != dateFrom) {
			sql.append(" and p.purchase_date >= ?");
			params.add(Date.valueOf(dateFrom));
		}
		
		sql.append(" %s".formatted(LIST_QUERY_GROUP_BY));
		
		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql.toString())) {
			
			for(var i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
			}
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(getData(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public PageResult<OrderListVo> search(LoginUser login, String status, LocalDate dateFrom, int page, int size) {
		
		var sqlByRole = switch (login.getRole()) {
		case "Customer" -> "%s where p.account_id = ?";
		case "Delivery" -> "%s join delivery d on p.id = d.purchase_id where d.account_id = ?";
		default -> "%s where 1 = 1" ;
		};
		
		var list = new ArrayList<OrderListVo>();
		var result = new PageResult<OrderListVo>();
		
		result.setList(list);
		result.setCurrentPage(page);
		result.setPageSize(size);

		var sql = new StringBuffer(sqlByRole.formatted(LIST_QUERY_BASE));
		var params = new ArrayList<Object>();
		
		var count = new StringBuffer(sqlByRole.formatted(COUNT_QUERY_BASE));
		var countParams = new ArrayList<Object>();
		
		if(login.getRole().equals("Customer") || login.getRole().endsWith("Delivery")) {
			params.add(login.getId());
			countParams.add(login.getId());
		}
		
		if(Strings.isNotBlanck(status)) {
			sql.append(" and p.status = ?");
			params.add(status);

			count.append(" and p.status = ?");
			countParams.add(status);
		}
		
		if(null != dateFrom) {
			sql.append(" and p.purchase_date >= ?");
			params.add(Date.valueOf(dateFrom));

			count.append(" and p.purchase_date >= ?");
			countParams.add(Date.valueOf(dateFrom));
		}
		
		sql.append(" %s".formatted(LIST_QUERY_GROUP_BY));
		
		if(page > 0 && size > 0) {
			sql.append(" limit ?, ?");
			params.add((page -1) * size);
			params.add(size);
		}

		try (var conn = dataSource.getConnection(); 
				var countStmt = conn.prepareStatement(count.toString());
				var stmt = conn.prepareStatement(sql.toString())) {

			for(var i = 0; i < countParams.size(); i ++) {
				countStmt.setObject(i + 1, countParams.get(i));
			}
			
			var countRs = countStmt.executeQuery();
			
			while(countRs.next()) {
				result.setTotalCount(countRs.getLong(1));
				break;
			}
			
			for(var i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
			}
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(getData(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}	

	public OrderDetailsVo findById(int id) {
		
		var sql = "%s where p.id = ? %s".formatted(LIST_QUERY_BASE, LIST_QUERY_GROUP_BY);

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				var result = new OrderDetailsVo();
				result.setOrder(getData(rs));
				
				var addressId = rs.getInt("address_id");
				result.setAddress(addressService.findAddressById(addressId));
				result.setItems(itemService.findByOrderId(id));
				result.setDelivery(deliveryService.findByOrderId(id));;
				result.setMessages(messageService.findByOrderId(id));
				result.setPaids(paidService.findByOrderId(id));
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private OrderListVo getData(ResultSet rs) throws SQLException {
		var vo = new OrderListVo();
		vo.setId(rs.getInt("id"));
		vo.setCustomerId(rs.getInt("customerId"));
		vo.setCustomerName(rs.getString("customerName"));
		vo.setOrderDate(rs.getTimestamp("orderDate").toLocalDateTime());
		vo.setStatus(rs.getString("status"));
		vo.setTotalAmount(rs.getInt("totalAmount"));
		vo.setRemark(rs.getString("remark"));
		return vo;
	}

	public void updateStatus(int id, String status, String remark) {

		var sql = "update purchase set status = ?, remark = ? where id = ?";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, status);
			stmt.setString(2, status.equals("Cancel") ? remark : "Finished at %s.".formatted(DateTimes.format(LocalDateTime.now())));
			stmt.setInt(3, id);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
