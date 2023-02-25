package com.jdc.location.repo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.jdc.location.dto.State;
import com.jdc.location.repo.DbUtils;
import com.jdc.location.repo.StateRepo;
import com.jdc.location.repo.impl.StateRepoImpl;

public class StateRepoTest {
	
	private StateRepo repo;
	
	@BeforeEach
	void init() {
		repo = new StateRepoImpl();
		DbUtils.truncate("state");
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/state_text.txt", delimiter = '\t')
	void test_create(String name, String region, String capital) {
		
		// Prepare Input Data
		State state = new State(name, capital, region);
		
		// Execute Target Method
		var result = repo.create(state);
		
		// Check Result
		assertEquals(1, result);
	}
}
