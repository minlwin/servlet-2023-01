package com.jdc.shop.model.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.vo.SaleSummary;
import com.jdc.shop.model.dto.vo.SaleSummaryItem;

public class SaleSummaryService {

	private DataSource dataSource;

	public SaleSummaryService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public SaleSummary getSaleSummary() {
		var summary = new SaleSummary();
		summary.setOrdered(getSummary("Ordered"));
		summary.setCancel(getSummary("Cancel"));
		summary.setDelivered(getSummary("Delivered"));
		summary.setFinished(getSummary("Finished"));
		return summary;
	}

	private SaleSummaryItem getSummary(String status) {
		var item = new SaleSummaryItem();
		item.setStatus(status);
		item.setCurrentMonth(findSummary(status, LocalDate.now().withDayOfMonth(1)));
		item.setTotal(findSummary(status, null));
		return item;
	}

	private long findSummary(String status, LocalDate date) {
		var sql = new StringBuffer("select count(id) from purchase where status = ?");
		
		if(null != date) {
			sql.append(" and purchase_date >= ?");
		}

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql.toString())) {
			stmt.setString(1, status);
			
			if(null != date) {
				stmt.setTimestamp(2, Timestamp.valueOf(date.atStartOfDay()));
			}
			
			var rs = stmt.executeQuery();
			while(rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
