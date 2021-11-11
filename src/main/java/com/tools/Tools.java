package com.tools;

public class Tools {

	public static int random0_2() {
		return (int) (Math.random() * (2 - 0 + 1) + 0);
	}
	
	public static int random0_1() {
		return (int) (Math.random() * (1 - 0 + 1) + 0);
	}
	
	public static Coup generateCoup50_50() {
		switch (Tools.random0_1()) {
		case 0:
			return Coup.TRAHIR;
		default:
			return Coup.COOPERER;
		}
	}

	public static int random1_10000() {
		return (int) (Math.random() * (10000 - 1 + 1) + 1);
	}
}
