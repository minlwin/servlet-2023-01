package com.jdc.location.model;

import java.util.List;

import javax.sql.DataSource;

import com.jdc.location.model.form.DivisionForm;

public class DivisionModel {

	private DataSource dataSource;

	public DivisionModel(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public int save(DivisionForm form) {
		return 0;
	}
	
	public List<DivisionForm> findAll() {
		return null;
	}
}
