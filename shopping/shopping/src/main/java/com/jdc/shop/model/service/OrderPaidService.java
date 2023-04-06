package com.jdc.shop.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.form.PurchasePaidForm;
import com.jdc.shop.model.dto.vo.OrderPaidVo;

public class OrderPaidService {

	private DataSource dataSource;

	public OrderPaidService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public void create(int purchaseId, List<PurchasePaidForm> paidInformations) {

		var sql = "insert into paid (purchase_id, paid_info_id, amount, paid_date, screen_shoot) values (?, ?, ?, ?, ?)";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			for(var form : paidInformations) {
				stmt.setInt(1, purchaseId);
				stmt.setInt(2, form.getPaidInfoId());
				stmt.setInt(3, form.getAmount());
				stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
				stmt.setString(5, form.getScreenShoot());
				
				stmt.addBatch();
			}
			
			stmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<OrderPaidVo> findByOrderId(int id) {
		
		var list = new ArrayList<OrderPaidVo>();
		var sql = """
			select p.id, p.paid_info_id paidInfoId, p.amount, p.screen_shoot screenShoot, 
			pi.payment_type, pi.payment_name, pi.account_number accountNumber, 
			pi.account_name accountName from paid p join paid_info pi on p.paid_info_id = pi.id 
			where p.purchase_id = ?""";

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

	private OrderPaidVo getData(ResultSet rs) throws SQLException {
		var vo = new OrderPaidVo();
		vo.setId(rs.getInt("id"));
		vo.setPaidInfoId(rs.getInt("paidInfoId"));
		vo.setPayment("%s %s".formatted(rs.getString("payment_type"), rs.getString("payment_name")));
		vo.setAmount(rs.getInt("amount"));
		vo.setScreenShoot(rs.getString("screenShoot"));
		vo.setAccountNumber(rs.getString("accountNumber"));
		vo.setAccountName(rs.getString("accountName"));
		return vo;
	}

}
