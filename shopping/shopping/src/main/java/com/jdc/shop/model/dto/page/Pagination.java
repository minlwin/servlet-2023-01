package com.jdc.shop.model.dto.page;

import java.util.List;

public interface Pagination {

	int getPageSize();
	
	int getCurrentPage();
	int getLastPage();
	Long getTotalCount();
	
	boolean isNeedToShow();
	List<Integer> getPages();

	boolean isNeedToShowFirst();
	boolean isNeedToShowLast();
}
