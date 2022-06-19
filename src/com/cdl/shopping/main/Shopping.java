package com.cdl.shopping.main;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.cdl.shopping.model.Price;
import com.cdl.shopping.model.ProductData;
import com.cdl.shopping.services.CheckOutService;
import com.cdl.shopping.services.InventoryService;
import com.cdl.shopping.services.StockRuleGeneratorService;

/**
 * @author Mrunalini Shinde
 * 
 * to call and show existing Inventory.
 * based on users choice it either Mocify or add StockRule or place the order.
 *  
 * 
 */
public class Shopping {

	private static final Object ANSWER = "YES";
	private static double totalPrice = 0;
	static List<ProductData> initialProductList;

	/**
	 * to take he input from user and call corresponding methods.
	 * @param args
	 */
	public static void main(String args[]) {

		InventoryService inventoryService = new InventoryService();
		StockRuleGeneratorService stockRuleGenerator = new StockRuleGeneratorService();
		initialProductList = inventoryService.getInitialProductList();
		inventoryService.displayIntialInventory(initialProductList);
		System.out.print("Do you want to add/update StockRule? Please type Yes or No: ");
		Scanner input = new Scanner(System.in);
		String ans = input.next().toUpperCase();
		if (ans.equals(ANSWER)) {
			createModify(stockRuleGenerator);
		} else {
			placeOrder(stockRuleGenerator);
		}

	}

	/**
	 * takes Input from user and create/modify stock rule.
	 * 
	 * @param stockRuleGeneratorService
	 *        service to create or modify stock price rule based on offers.
	 */
	private static void createModify(final StockRuleGeneratorService stockRuleGeneratorService) {
		stockRuleGeneratorService.addProductRule();
		System.out.print("Do you want to add/update New ProductRule? Please type Yes or No: ");
		Scanner input2 = new Scanner(System.in);
		String ans2 = input2.next().toUpperCase();
		if (ans2.equals(ANSWER)) {
			createModify(stockRuleGeneratorService);
		} else {
			placeOrder(stockRuleGeneratorService);

		}

	}

	/**
	 * takes input of product and quantity to place the order.
	 * if user wants to check out then it displays the total bill;
	 * 
	 * @param stockRuleGeneratorService
	 * 		  service to create or modify stock price rule based on offers.
	 */
	private static void placeOrder(StockRuleGeneratorService stockRuleGeneratorService) {
		HashMap<String, ProductData> productMap;
		CheckOutService checkOutService = new CheckOutService();
		productMap = stockRuleGeneratorService.getStockRuleData();
		HashMap<String, Price> finalPriceMap = null;
		System.out.print("Please Enter Product Name you want to order:  ");
		Scanner cart = new Scanner(System.in);

		String orderProductName = cart.next().toUpperCase();

		if (stockRuleGeneratorService.getStockRuleData().get(orderProductName) != null) {

			System.out.print("Please Enter Quantity you want to order: ");
			int orderQuantity = 0;
			while (!cart.hasNextInt())
				cart.next();
			orderQuantity = cart.nextInt();
			finalPriceMap = checkOutService.checkoutPrice(productMap, orderProductName, orderQuantity);
			Price price = finalPriceMap.get(orderProductName);
			System.out.println("Price for this product:" + price.getCalculatedPrice());

		} else {
			System.out.println("Invalid Product");
			placeOrder(stockRuleGeneratorService);

		}

		System.out.print("Do you want to Checkout:Type Yes or No ");
		Scanner checkout = new Scanner(System.in);
		String ans = checkout.next().toUpperCase();

		if (ans.equals(ANSWER)) {
			totalPrice = checkOutService.calculateFinalPrice(finalPriceMap);
			System.out.println("Thanks for shopping, Please Pay =" + totalPrice);

			checkout.close();
		} else {
			placeOrder(stockRuleGeneratorService);
		}
	}

}
