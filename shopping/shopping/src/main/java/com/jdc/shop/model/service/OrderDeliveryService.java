package com.jdc.shop.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;

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
				select d.account_id, a.name delivery, a.phone, d.delivery_date date, 
				d.time_from timeFrom, d.time_to timeTo from delivery d 
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
		vo.setDate(rs.getDate("date").toLocalDate());
		vo.setTimeFrom(rs.getString("timeFrom"));
		vo.setTimeTo(rs.getString("timeTo"));
		vo.setDelivery(rs.getString("delivery"));
		vo.setPhone(rs.getString("phone"));
		return vo;
	}

}
