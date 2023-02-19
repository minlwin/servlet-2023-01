package com.jdc.demo;

import java.io.Serializable;

public class Counter implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int count;
	
	public int getValue() {
		return count;
	}
	
	public void countUp() {
		count ++;
	}
}
