package com.jdc.location.repo.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class StateRepoTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/state_text.txt", delimiter = '\t')
	void test_create(String name, String region, String capital) {

	}
}
