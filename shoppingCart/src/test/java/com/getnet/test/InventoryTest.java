package com.getnet.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.cart.Product;
import com.exception.ProductNotFoundException;
import com.inventory.Inventory;
import com.inventory.InventoryImpl;

public class InventoryTest {
	
	private Inventory inventory;
	
	@Before
	public void setup() throws Exception {
		inventory = new InventoryImpl();
		Product product = new Product("APLE","Apple","Kashmiri Apple", 40.0,4);
		inventory.addProduct(product);
	}
	
	@Test
	public void testProductExists() {
		boolean exists= inventory.isProductExist("Apple");
		assertTrue(exists);
	}
	
	@Test
	public void testProductNotExists() {
		boolean exists= inventory.isProductExist("APL");
		assertFalse(exists);
	}
	
    @Test
    public void testPriceOfProduct() {
    	double price = inventory.getProductByName("Apple").getUnitPrice();
    	assertEquals(40.0,price,0.1);
    }
    
    @Test(expected = ProductNotFoundException.class)
    public void testProductNotFound() {
    	 inventory.getProductByName("Apple1");
    }
    
    @Test
    public void testEnoughQuantity() {
    	boolean quantity = inventory.isEnoughQuantityAvailable("Apple", 5);
    	assertFalse(quantity);
    }
}
