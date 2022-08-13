package com.cart.servic;

import java.util.List;

import com.cart.CartItem;
import com.enums.CardType;
import com.exception.PaymentException;
import com.exception.ServiceException;

public interface CartService {

	public void addToCart(String productName,Integer quantity) throws ServiceException;
	public boolean deleteFromCart(String productName) throws Exception;
	public boolean deleteFromCart(String productName,Integer quantity) throws Exception;
	public List<CartItem> getCartItems();
	public double checkout(CardType cardType) throws PaymentException;
}
