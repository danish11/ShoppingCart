package com.inventory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.cart.Product;
import com.exception.ProductNotFoundException;


public class InventoryImpl implements Inventory {
	
	Map<String,Product> productAvailableAtInventory= new ConcurrentHashMap<>();

	@Override
	public void addProduct(Product product) {
		productAvailableAtInventory.put(product.getProductName(), product);

	}

	@Override
	public boolean isProductExist(String productName) {
		return productAvailableAtInventory.containsKey(productName);
	}

	@Override
	public boolean isEnoughQuantityAvailable(String productName,Integer quantity) {
		return productAvailableAtInventory.get(productName).getQuantity()>= quantity;
	}

	@Override
	public Product getProductByName(String productName) {
		if(!isProductExist(productName))
			throw new ProductNotFoundException();
		else
		return productAvailableAtInventory.get(productName);
	}
	
	

}
