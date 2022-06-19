package com.cdl.shopping.model;

public class ProductData {

	private String productName;
	private float unitPrice;
	private int offerQuantity;
	private double offerPrice;
	
	public ProductData() {
		
	}

	public ProductData(String productName, float unitPrice, int offerQuantity, double offerPrice) {
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.offerQuantity = offerQuantity;
		this.offerPrice = offerPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getOfferQuantity() {
		return offerQuantity;
	}

	public void setOfferQuantity(int offerQuantity) {
		this.offerQuantity = offerQuantity;
	}

	public double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(double offerPrice) {
		this.offerPrice = offerPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductData other = (ProductData) obj;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}
	
	

}
