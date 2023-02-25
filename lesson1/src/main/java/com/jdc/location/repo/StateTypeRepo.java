package com.jdc.location.repo;

import java.util.List;

import com.jdc.location.dto.StateType;

public interface StateTypeRepo {

	void create(StateType data);
	
	List<StateType> findAll();
	
	StateType findById(String name);
	
	void update(StateType data);
	
	void delete(String name);
}
