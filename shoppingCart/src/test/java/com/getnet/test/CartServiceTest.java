package com.getnet.test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import com.cart.Product;
import com.cart.servic.CartService;
import com.cart.service.impl.CartServiceImpl;
import com.enums.CardType;
import com.exception.ServiceException;
import com.inventory.Inventory;

public class CartServiceTest {
private Inventory inventory= mock(Inventory.class);
private CartService cartService = new CartServiceImpl(inventory);

@Test
public void testAddProductToCart() throws ServiceException {
	when(inventory.isProductExist("Apple")).thenReturn(true);
	when(inventory.isEnoughQuantityAvailable("Apple",1)).thenReturn(true);
	when(inventory.getProductByName("Apple")).thenReturn(new Product("Apple", 20.0));
	cartService.addToCart("Apple", 1);
	Assert.assertEquals(1, cartService.getCartItems().size());
	Assert.assertEquals("Apple", cartService.getCartItems().get(0).getProduct().getProductName());
	verify(inventory,times(1)).isProductExist("Apple");
	verify(inventory,times(1)).isEnoughQuantityAvailable("Apple", 1);
	
}

@Test(expected = ServiceException.class)
public void testAddProductToCartWithProductNotExists() throws ServiceException {
	when(inventory.isProductExist("Apple")).thenReturn(false);
	when(inventory.isEnoughQuantityAvailable("Apple",1)).thenReturn(false);
	cartService.addToCart("Apple", 1);
	verify(inventory,times(1)).isProductExist(anyString());
	verify(inventory,times(1)).isEnoughQuantityAvailable(anyString(), anyInt());
	
}
@Test(expected = ServiceException.class)
public void testAddProductToCartWithDesiredQuantityNotAvailable() throws ServiceException {
	when(inventory.isProductExist("Apple")).thenReturn(true);
	when(inventory.isEnoughQuantityAvailable("Apple",1)).thenReturn(false);
	cartService.addToCart("Apple", 1);
	verify(inventory,times(1)).isProductExist("Apple");
	verify(inventory,times(1)).isEnoughQuantityAvailable("Apple", 1);
	
}

@Test
public void testAddProductToCartWithMultipleAddition() throws ServiceException {
	when(inventory.isProductExist("Apple")).thenReturn(true);
	when(inventory.isEnoughQuantityAvailable("Apple",1)).thenReturn(true);
	cartService.addToCart("Apple", 1);
	cartService.addToCart("Apple", 1);
	int quatity = cartService.getCartItems().get(0).getQuantity();
	Assert.assertEquals(2, quatity);
	verify(inventory,times(2)).isProductExist("Apple");
	verify(inventory,times(2)).isEnoughQuantityAvailable("Apple", 1);
}
@Test
public void testProductDeleteFromCart() throws Exception {
	when(inventory.isProductExist("Apple")).thenReturn(true);
	when(inventory.isEnoughQuantityAvailable("Apple",1)).thenReturn(true);
	when(inventory.getProductByName("Apple")).thenReturn(new Product("apl","Apple","good",20.0,0));
	
	cartService.addToCart("Apple", 1);
	Assert.assertTrue(cartService.deleteFromCart("Apple"));
	
}

@Test
public void testMultipleDeleteFromCart() throws Exception {
	when(inventory.isProductExist("Apple")).thenReturn(true);
	when(inventory.isEnoughQuantityAvailable("Apple",5)).thenReturn(true);
	cartService.addToCart("Apple", 5);
	cartService.deleteFromCart("Apple",3);
	int quatity = cartService.getCartItems().get(0).getQuantity();
	Assert.assertEquals(2, quatity);
}
@Test
public void testcheckOutWithGold() throws Exception {
	when(inventory.isProductExist("Apple")).thenReturn(true);
	when(inventory.isEnoughQuantityAvailable("Apple",5)).thenReturn(true);
	when(inventory.getProductByName("Apple")).thenReturn(new Product("Apple", 40.0));
	cartService.addToCart("Apple", 5);
	double totalamount = cartService.checkout(CardType.GOLD);
	Assert.assertEquals(160.0, totalamount,0.1);
	verify(inventory,times(1)).isProductExist("Apple");
	verify(inventory,times(1)).isEnoughQuantityAvailable("Apple", 5);
	verify(inventory,times(1)).getProductByName("Apple");
}
@Test
public void testcheckOutWithSilver() throws Exception {
	when(inventory.isProductExist("Apple")).thenReturn(true);
	when(inventory.isEnoughQuantityAvailable("Apple",5)).thenReturn(true);
	when(inventory.getProductByName("Apple")).thenReturn(new Product("Apple", 40.0));
	cartService.addToCart("Apple", 5);
	double totalamount = cartService.checkout(CardType.SILVER);
	Assert.assertEquals(180.0, totalamount,0.1);
	verify(inventory,times(1)).isProductExist("Apple");
	verify(inventory,times(1)).isEnoughQuantityAvailable("Apple", 5);
	verify(inventory,times(1)).getProductByName("Apple");
}
@Test
public void testcheckOutWithNormal() throws Exception {
	when(inventory.isProductExist("Apple")).thenReturn(true);
	when(inventory.isEnoughQuantityAvailable("Apple",5)).thenReturn(true);
	when(inventory.getProductByName("Apple")).thenReturn(new Product("Apple", 40.0));
	cartService.addToCart("Apple", 5);
	double totalamount = cartService.checkout(CardType.NORMAL);
	Assert.assertEquals(200.0, totalamount,0.1);
	verify(inventory,times(1)).isProductExist("Apple");
	verify(inventory,times(1)).isEnoughQuantityAvailable("Apple", 5);
	verify(inventory,times(1)).getProductByName("Apple");
}
}
