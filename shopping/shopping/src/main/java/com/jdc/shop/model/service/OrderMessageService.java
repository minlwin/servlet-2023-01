package com.jdc.shop.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.vo.OrderMessageVo;

public class OrderMessageService {

	private DataSource dataSource;
	
	public OrderMessageService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public List<OrderMessageVo> findByOrderId(int id) {
		
		var list = new ArrayList<OrderMessageVo>();
		var sql = """
			select m.id, m.send_date sendAt, m.message, 
			m.account_id senderId, a.name sender, a.role 
			from message m join account a on m.account_id = a.id 
			where m.purchase_id = ?""";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(getData(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private OrderMessageVo getData(ResultSet rs) throws SQLException {
		var vo = new OrderMessageVo();
		vo.setId(rs.getInt("id"));
		vo.setMessage(rs.getString("message"));
		vo.setRole(rs.getString("role"));
		vo.setSender(rs.getString("sender"));
		vo.setSenderId(rs.getInt("senderId"));
		vo.setSendAt(rs.getTimestamp("sendAt").toLocalDateTime());
		return vo;
	}

}
