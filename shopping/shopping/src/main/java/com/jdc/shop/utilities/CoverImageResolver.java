package com.jdc.shop.utilities;

import java.util.List;

import com.jdc.shop.model.dto.vo.Photo;

public class CoverImageResolver {

	public static String resolve(List<Photo> photos) {
		if(null != photos && !photos.isEmpty()) {
			return photos.stream().filter(Photo::isCover).map(Photo::getPhoto)
					.findAny().orElse(photos.get(0).getPhoto());
		}
		
		return "product-image.png";
	}
}
