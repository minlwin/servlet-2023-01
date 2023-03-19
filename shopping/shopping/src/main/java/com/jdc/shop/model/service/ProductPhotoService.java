package com.jdc.shop.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.vo.Photo;

public class ProductPhotoService {

	private DataSource dataSource;

	public ProductPhotoService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public List<Photo> findPhotoByProduct(int id) {
		List<Photo> list = new ArrayList<>();
		var sql = "select * from image where product_id = ?";

		try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);

			var rs = stmt.executeQuery();

			while (rs.next()) {
				var photo = new Photo();
				photo.setCover(rs.getBoolean("cover"));
				photo.setPhoto(rs.getString("photo"));
				list.add(photo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
