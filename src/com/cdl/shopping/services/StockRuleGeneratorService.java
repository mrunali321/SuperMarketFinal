package com.cdl.shopping.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author mrunalini shinde
 * 
 * Service to create the stock rules . * 
 */

import com.cdl.shopping.model.ProductData;

public class StockRuleGeneratorService {
	
	static InventoryService inventoryService = new InventoryService();
	static List<ProductData> productList = inventoryService.getInitialProductList();
	static CheckOutService checkOutService = new CheckOutService();
	static HashMap<String, ProductData> productMap = new HashMap<String, ProductData>();

	public void addProductRule() {

		try {
			createorUpdateProductRuleData();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * it  takes the user input to add rule for the new product data or modify the existing product information.
	 * 
	 */
	private void createorUpdateProductRuleData() {

		for (ProductData product : productList) {

			productMap.put(product.getProductName(), product);
		}

		Scanner input = new Scanner(System.in);

		input.nextLine();
		System.out.print("Enter Product name: ");
		String productName = input.next().toUpperCase();

		System.out.print("Enter singleProductPrice : ");
		while (!input.hasNextInt())
			input.next();
		float unitProductPrice = input.nextInt();

		input.nextLine();
		System.out.print("Enter productQuantity : ");
		while (!input.hasNextInt())
			input.next();
		int offerQuantity = input.nextInt();

		input.nextLine();
		System.out.print("Enter groupProductPrice : ");
		while (!input.hasNextInt())
			input.next();
		double offerPrice = input.nextDouble();

		if (productMap.containsKey(productName)) {
			ProductData product = productMap.get(productName);
			product.setProductName(productName);
			product.setUnitPrice(unitProductPrice);
			product.setOfferQuantity(offerQuantity);
			product.setOfferPrice(offerPrice);
			productMap.put(productName, product);

		} else {
			ProductData productData = new ProductData(productName, unitProductPrice, offerQuantity, offerPrice);
			productList.add(productData);
			productMap.put(productName, productData);

		}

		inventoryService.displayIntialInventory((ArrayList<ProductData>) productList);

	}
	/**
	 * 
	 * to get the products which are already present in inventory.
	 * @return  collection of product name along with its other information.
	 */
	public HashMap<String, ProductData> getStockRuleData() {

		if (productMap.isEmpty()) {

			for (ProductData product : productList) {

				productMap.put(product.getProductName(), product);
			}
		}

		return productMap;
	}

}
