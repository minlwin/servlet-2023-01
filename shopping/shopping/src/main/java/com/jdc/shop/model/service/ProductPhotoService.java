package com.jdc.shop.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.vo.Photo;

import jakarta.servlet.http.Part;

public class ProductPhotoService {

	private DataSource dataSource;

	public ProductPhotoService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public void uploadPhotos(int id, List<Part> parts, String imageFoloder) {
		
		var sql = "insert into image(product_id, photo, cover) values (?, ?, ?)";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			for(var i = 0; i < parts.size(); i ++) {
				
				var fileName = saveImage(id, parts.get(i), imageFoloder, i + 1);
				
				if(null == fileName) {
					continue;
				}
				
				stmt.setInt(1, id);
				stmt.setString(2, fileName);
				stmt.setBoolean(3, i == 0);
				
				stmt.addBatch();
			}
			
			stmt.executeBatch();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setCoverImage(int id, String photo) {

		var removeCoverSql = "update image set cover = false where product_id = ?";
		var setCoverSql = "update image set cover = true where product_id = ? and photo = ?";

		try (var conn = dataSource.getConnection(); 
				var removeCover = conn.prepareStatement(removeCoverSql);
				var setCover = conn.prepareStatement(setCoverSql)) {

			removeCover.setInt(1, id);
			removeCover.executeUpdate();
			
			setCover.setInt(1, id);
			setCover.setString(2, photo);
			
			setCover.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}		

	private String saveImage(int id, Part part, String imageFoloder, int index) throws IOException {
		var originalName = part.getSubmittedFileName();
		
		if(null == originalName) {
			return null;
		}
		
		var fileName = "p-%d-%d-%s".formatted(id, index, originalName);
		
		var target = Path.of(imageFoloder, fileName);
		Files.copy(part.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
		
		return fileName;
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

	public void deleteImage(int id, String imageFoloder, String photo) {
		
		var sql = "delete from image where product_id = ? and photo = ?";

		try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.setString(2, photo);
			
			stmt.executeUpdate();
			
			// Delete Photo File
			Files.delete(Path.of(imageFoloder, photo));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
