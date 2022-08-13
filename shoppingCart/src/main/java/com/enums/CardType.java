package com.enums;

public enum CardType {

	GOLD(20),
	SILVER(10),
	NORMAL(0);

	private int numVal;

	CardType(int numVal) {
		this.numVal = numVal;
	}

	public int getNumVal() {
		return numVal;
	}
}
