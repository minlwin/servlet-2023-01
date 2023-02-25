package com.jdc.location.repo;

import java.util.List;

import com.jdc.location.dto.State;

public interface StateRepo {

	int create(State state);
	
	void update(State state);
	
	void delete(int id);
	
	List<State> search(String region, String name);
	
	State findById(int id);
	
}
