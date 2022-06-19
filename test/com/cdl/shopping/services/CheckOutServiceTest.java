package com.cdl.shopping.services;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.cdl.shopping.model.Price;
import com.cdl.shopping.model.ProductData;

public class CheckOutServiceTest {
	CheckOutService checkOutService = new CheckOutService();

	@Test
	public void  checkoutPriceForSingleProduct(){  
		HashMap<String, Price> productPriceMap = new HashMap<String, Price>();
		Price Price1 = new Price(1,50);

		HashMap<String, ProductData> productMap = createTestDataForSingle();
		
		productPriceMap=checkOutService.checkoutPrice(productMap, "A", 1);
		Price1=productPriceMap.get("A");
	    assertEquals("Price for product1 quantity 1.", 50.0, Price1.getCalculatedPrice(), 0);
	    
	    checkOutService.calculateFinalPrice(productPriceMap);
	    assertEquals("Total Price:", 50.0, Price1.getCalculatedPrice(), 0);
	
	}
	
	
	@Test
	public void  checkoutPriceForMultipleProducts(){  
		HashMap<String, Price> productPriceMap = new HashMap<String, Price>();
		Price Price1 = new Price(1,50);
		Price Price2 = new Price(2,45);

		HashMap<String, ProductData> productMap = createTestDataforMultiple();
		
		productPriceMap=checkOutService.checkoutPrice(productMap, "C", 1);
		Price1=productPriceMap.get("C");
		productPriceMap=checkOutService.checkoutPrice(productMap, "B", 2);
		Price2=productPriceMap.get("B");

	    assertEquals("Price for product C quantity 1.", 50.0, Price1.getCalculatedPrice(), 0);
	    assertEquals("Price for product B quantity 2.", 45.0, Price2.getCalculatedPrice(), 0);
	    
	    checkOutService.calculateFinalPrice(productPriceMap);
	    assertEquals("Total Price of Product B and C is =", 95.0, Price1.getCalculatedPrice()+ Price2.getCalculatedPrice(), 0);

	    

	
	}

	@Test
	public void  checkoutPriceForRepeatingSameproductMultipleTime(){  
		HashMap<String, Price> productPriceMap = new HashMap<String, Price>();
		Price Price1 = new Price(1,50);
		Price Price2 = new Price(2,45);

		HashMap<String, ProductData> productMap = createTestDataforRepeatingSameproductMultipleTime();
		
		productPriceMap=checkOutService.checkoutPrice(productMap, "D", 2);
		Price1=productPriceMap.get("D");
		
		productPriceMap=checkOutService.checkoutPrice(productMap, "E", 2);
		Price2=productPriceMap.get("E");
		
		productPriceMap=checkOutService.checkoutPrice(productMap, "D", 1);
		Price1=productPriceMap.get("D");

	    assertEquals("Price for product D quantity 1.", 130.0, Price1.getCalculatedPrice(), 0);
	    assertEquals("Price for product E quantity 2.", 45.0, Price2.getCalculatedPrice(), 0);
	    
	    checkOutService.calculateFinalPrice(productPriceMap);
	    assertEquals("Total Price of Product D and E is =", 175.0, Price1.getCalculatedPrice()+ Price2.getCalculatedPrice(), 0);


	
	}

	
	private HashMap<String, ProductData> createTestDataForSingle() {
		List<ProductData> productList = new ArrayList<ProductData>();
		HashMap<String, ProductData> productMap = new HashMap<String, ProductData>();

		ProductData productData1 = new ProductData("A", 50, 3, 130);
		productList.add(productData1);
		productMap.put("A", productData1);
		return productMap;
	}
	
	private HashMap<String, ProductData> createTestDataforMultiple() {
		List<ProductData> productList = new ArrayList<ProductData>();
		HashMap<String, ProductData> productMap = new HashMap<String, ProductData>();

		
		ProductData productData2 = new ProductData("B", 30, 2, 45);	
		ProductData productData1 = new ProductData("C", 50, 3, 130);

		
		productList.add(productData1);
		productList.add(productData2);
				
		productMap.put("B",productData2);
		productMap.put("C",productData1);

		return productMap;
	}
	
	private HashMap<String, ProductData> createTestDataforRepeatingSameproductMultipleTime() {
		List<ProductData> productList = new ArrayList<ProductData>();
		HashMap<String, ProductData> productMap = new HashMap<String, ProductData>();

		ProductData productData1 = new ProductData("D", 50, 3, 130);

		ProductData productData2 = new ProductData("E", 30, 2, 45);	

		
		productList.add(productData1);
		productList.add(productData2);
		
		productMap.put("D",productData1);
		productMap.put("E",productData2);

		return productMap;
	}

}
