package com.jdc.location.repo.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StateRepoTest {

	@ParameterizedTest
	@CsvSource(value = {
			"Demo Name,Demo Region,Demo Capital"
	})
	void test_create(String name, String region, String capital) {
		System.out.println("Name    : %s".formatted(name));
		System.out.println("Region  : %s".formatted(region));
		System.out.println("Capital : %s".formatted(capital));
	}
}
