package com.jdc.shop.model.dto.page;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;

@Data
public class PaginationPageResultAdapter<T> implements Pagination{

	private PageResult<T> result;
	private LinkedList<Integer> pages;
	
	public PaginationPageResultAdapter(PageResult<T> result) {
		super();
		this.result = result;
		
		pages = new LinkedList<>();
		
		if(result.getTotalCount() > 0) {
			var current = result.getCurrentPage();
			pages.add(current);
			
			while(current < getLastPage() && pages.size() <= 3) {
				pages.addLast(++ current);
			}
			
			while(pages.size() <= 5 && pages.get(0) > 1) {
				pages.addFirst(pages.get(0) - 1);
			}
		}
	}
	
	public List<T> getList() {
		return result.getList();
	}

	@Override
	public int getPageSize() {
		return result.getPageSize();
	}

	@Override
	public int getCurrentPage() {
		return result.getCurrentPage();
	}

	@Override
	public Long getTotalCount() {
		return result.getTotalCount();
	}

	@Override
	public int getLastPage() {
		var size = getTotalCount().intValue() / getPageSize();
		var remainder = getTotalCount().intValue() % getPageSize();
		var last = remainder > 0 ? size + 1 : size;
		return last;
	}

	@Override
	public boolean isNeedToShow() {
		return !pages.isEmpty();
	}

	@Override
	public List<Integer> getPages() {
		return pages;
	}

	@Override
	public boolean isNeedToShowFirst() {
		return getCurrentPage() > 1;
	}

	@Override
	public boolean isNeedToShowLast() {
		return getCurrentPage() < getLastPage();
	}

}
