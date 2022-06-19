package com.cdl.shopping.services;

import java.util.ArrayList;
import java.util.List;

import com.cdl.shopping.model.ProductData;

/**
 * it has all the products with its unit price and quantity information.
 * 
 * @author mrunalini shinde.
 *
 */
public class InventoryService {

	public List<ProductData> getInitialProductList() {

		List<ProductData> productList = new ArrayList<ProductData>();

		ProductData productData1 = new ProductData("A", 50, 3, 130);
		ProductData productData2 = new ProductData("B", 30, 2, 45);
		ProductData productData3 = new ProductData("C", 20, 0, 0);
		ProductData productData4 = new ProductData("D", 15, 0, 0);

		productList.add(productData1);
		productList.add(productData2);
		productList.add(productData3);
		productList.add(productData4);

		return productList;
	}

	/**
	 * display all the product details.
	 * 
	 * @param initialProductList
	 *  	  list of initial products
	 */
	public void displayIntialInventory(List<ProductData> initialProductList) {

		for (ProductData product : initialProductList) {
			System.out.println(product.getProductName() + "||" + product.getUnitPrice() + "||"
					+ product.getOfferQuantity() + "||" + product.getOfferPrice());
		}
	}
}
