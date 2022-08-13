package com.inventory;

import com.cart.Product;

public interface Inventory {
	public void addProduct(Product product);
	public boolean isProductExist(String productName);
	public boolean isEnoughQuantityAvailable(String productName, Integer quantity);
	public Product getProductByName(String productName);
	

}
