package com.cdl.shopping.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import com.cdl.shopping.model.Price;
import com.cdl.shopping.model.ProductData;


/**
 * it calculates the individual product price based stock rule.
 * 
 * @author mrunalini shinde.
 *
 */
public class CheckOutService {
	static List<ProductData> productList = new ArrayList<ProductData>();
	static HashMap<String, Price> productPriceMap = new HashMap<String, Price>();
	ProductData productData = new ProductData();
	static double totalPrice=0;

	/**
	 * 
	 * @param productMap
	 * 			map having information of stock rules for the product.
	 * 
	 * @param orderProductName
	 * 			product name for which order has been placed by customer.
	 * 			
	 * @param orderQuantity
	 * 			produc quantity for which order has been placed by customer.
	 * 
	 * @return
	 *         haspmap having product name as key with {@Price} as value
	 */
	public HashMap<String, Price> checkoutPrice(final HashMap<String, ProductData> productMap,final String orderProductName,int orderQuantity) {
	    Price price = new Price(orderQuantity, orderQuantity);
		double singleProductPrice =0;
		Price priceInfo;
		if(productMap.containsKey(orderProductName)) {
			productData=productMap.get(orderProductName);

			if(productPriceMap.containsKey(orderProductName)) {	
				priceInfo=productPriceMap.get(orderProductName);
				orderQuantity=orderQuantity+priceInfo.getQuantity();
			}
			if (orderQuantity >= productData.getOfferQuantity() && productData.getOfferQuantity() !=0 && productData.getOfferPrice()!=0) {
				int quotient = orderQuantity / productData.getOfferQuantity();
				int remainder = orderQuantity % productData.getOfferQuantity();
				singleProductPrice = quotient * productData.getOfferPrice();
				if (remainder != 0) {
					singleProductPrice = singleProductPrice + productData.getUnitPrice() * remainder;
				}
			} else {
				singleProductPrice = productData.getUnitPrice() * orderQuantity;
			}
			price.setQuantity(orderQuantity);
			price.setCalculatedPrice(singleProductPrice);
			productPriceMap.put(orderProductName, price);
		}else {
			System.out.println("Product Not Available Please Enter Valid Product");
		}
		return productPriceMap;
	}

	/**
	 * Calculates price for Individual product as well as total price of all products.
	 * 
	 * @param finalPriceMap
	 * 			haspmap having product name as key with {@Price} as value.
	 * 			
	 * @return total bill price.
	 */
	public double calculateFinalPrice(final HashMap<String, Price> finalPriceMap) {
		 
		for (Entry<String, Price> entry : finalPriceMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                             ", quantity = " + entry.getValue().getQuantity() +", product price = " + entry.getValue().getCalculatedPrice());
		
            totalPrice=totalPrice+entry.getValue().getCalculatedPrice();
            
            
    }
		return totalPrice;
		
	}
}
