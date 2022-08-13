package com.cart.servic;

public interface PaymentService {
	double calculateDiscount();
	double totalCheckoutAmoutWithoutDiscount();
	double checkoutAmount();
	
}
