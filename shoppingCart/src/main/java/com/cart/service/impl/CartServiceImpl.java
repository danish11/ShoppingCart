package com.cart.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cart.CartItem;
import com.cart.servic.CartService;
import com.enums.CardType;
import com.exception.EmptyShoppingCartException;
import com.exception.PaymentException;
import com.exception.ServiceException;
import com.inventory.Inventory;

public class CartServiceImpl implements CartService{
	
	 Map<String,Integer> cartItemMap = new HashMap<>();
	
	private final Inventory inventoryService;
	
	public CartServiceImpl(Inventory inventoryService) {
		this.inventoryService = inventoryService;
	}

	@Override
	public void addToCart(String productName, Integer quantity) throws ServiceException {
		if(!inventoryService.isProductExist(productName))
			throw new ServiceException("Product does not exists");
		if(!inventoryService.isEnoughQuantityAvailable(productName,quantity))
			throw new ServiceException("Product not in stock");
		cartItemMap.put(productName, cartItemMap.compute(productName, (k, v) -> (k == null) ? quantity : (v == null ? 0 : v) + quantity));
	}
	

	@Override
	public boolean deleteFromCart(String productName) throws ServiceException {
		return cartItemMap.remove(productName) != null;
	}

	public boolean deleteFromCart(String productName,Integer quantity) throws ServiceException {
		
		Integer availableQuantity= cartItemMap.get(productName);
		if(availableQuantity<quantity)
			throw new ServiceException("Quantity exceeds available quantity");
		else {
			cartItemMap.put(productName, availableQuantity-quantity);
		}
		return true;
	}

	@Override
	public List<CartItem> getCartItems() {
		List<CartItem> items = cartItemMap.entrySet().stream().
				map(entry-> new CartItem(inventoryService.getProductByName(entry.getKey()), entry.getValue())).
				collect(Collectors.toList());
		return items;
	}

	@Override
	public double checkout(CardType cardType) throws PaymentException,EmptyShoppingCartException {
		if (cartItemMap.isEmpty()) {
			throw new EmptyShoppingCartException();
		}
		return new PaymentServiceImpl(getCartItems(), cardType).checkoutAmount();
		
	}
	 
	
	
	

}
