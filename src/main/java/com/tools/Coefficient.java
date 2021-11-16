package com.tools;

public enum Coefficient {

	T (5),
	D (0),
	C (3),
	P (1);
	
	private int val;
	
	Coefficient(int nb) {
		val = nb;
		
	}

	public int getPoint() {
		return val;
	}
}
