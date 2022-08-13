package com.cart.service.impl;

import java.util.List;

import com.cart.CartItem;
import com.cart.servic.PaymentService;
import com.enums.CardType;
import com.exception.PaymentException;

public class PaymentServiceImpl implements PaymentService {
	
	private List<CartItem> cartItems;
	private CardType cardType;
	
    
	public PaymentServiceImpl(List<CartItem> cartItems, CardType cardType) {
		super();
		this.cartItems = cartItems;
		this.cardType = cardType;
	}
	
	@Override
	public double calculateDiscount() throws PaymentException {
		return (totalCheckoutAmoutWithoutDiscount()*cardType.getNumVal())/100;
		
	}

	@Override
	public double totalCheckoutAmoutWithoutDiscount() throws PaymentException {
		return cartItems.stream().map(item-> item.getProduct().getUnitPrice()* item.getQuantity()).
				reduce(0.0, (sum,itemPrice) -> sum += itemPrice);
	}

	@Override
	public double checkoutAmount() throws PaymentException {
		return totalCheckoutAmoutWithoutDiscount()-calculateDiscount();
	}

}
