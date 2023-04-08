package com.jdc.shop.model.service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.vo.OrderDeliveryVo;

public class OrderDeliveryService {
	
	private DataSource dataSource;
	
	public OrderDeliveryService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public OrderDeliveryVo findByOrderId(int id) {
		var sql = """
				select d.account_id accountId, a.name delivery, a.phone, 
				d.date_from dateFrom, d.date_to dateTo from delivery d 
				join account a on d.account_id = a.id where d.purchase_id = ?""";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				return getData(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private OrderDeliveryVo getData(ResultSet rs) throws SQLException {
		var vo = new OrderDeliveryVo();
		vo.setAccountId(rs.getInt("accountId"));
		vo.setDateFrom(rs.getDate("dateFrom").toLocalDate());
		vo.setDateTo(rs.getDate("dateTo").toLocalDate());
		vo.setDelivery(rs.getString("delivery"));
		vo.setPhone(rs.getString("phone"));
		return vo;
	}

	public void request(int id, int delivery, LocalDate dateFrom, LocalDate dateTo) {
		
		var orderStatusSql = "update purchase set status = 'Delivered' where id = ?";
		var deliverySql = "insert into delivery(purchase_id, account_id, date_from, date_to) values (?, ?, ?, ?)";

		try (var conn = dataSource.getConnection();
				var orderStatusStmt = conn.prepareStatement(orderStatusSql);
				var deliveryStmt = conn.prepareStatement(deliverySql)) {
			
			deliveryStmt.setInt(1, id);
			deliveryStmt.setInt(2, delivery);
			deliveryStmt.setDate(3, Date.valueOf(dateFrom));
			deliveryStmt.setDate(4, Date.valueOf(dateTo));
			
			deliveryStmt.executeUpdate();
			
			orderStatusStmt.setInt(1, id);
			orderStatusStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
