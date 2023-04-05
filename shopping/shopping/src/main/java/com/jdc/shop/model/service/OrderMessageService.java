package com.jdc.shop.model.service;

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
		var sql = "";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
