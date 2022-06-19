package com.cdl.shopping.model;

public class Price {
	
	public Price(int quantity, double calculatedPrice) {
		super();
		this.quantity = quantity;
		this.calculatedPrice = calculatedPrice;
	}

	int quantity;
	double calculatedPrice;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getCalculatedPrice() {
		return calculatedPrice;
	}

	public void setCalculatedPrice(double calculatedPrice) {
		this.calculatedPrice = calculatedPrice;
	}

}
