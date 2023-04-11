package com.jdc.shop.model.dto.page;

import java.util.List;

import lombok.Data;

@Data
public class PageResult<T> {

	private List<T> list;
	private Integer currentPage;
	private Integer pageSize;
	private Long totalCount;
}
