package com.cart;

public class CartItem {

private Product product;
private Integer quantity;

public CartItem(Product product, Integer quantity) {
	super();
	this.product = product;
	this.quantity = quantity;
}

public Product getProduct() {
	return product;
}

public void setProduct(Product product) {
	this.product = product;
}

public Integer getQuantity() {
	return quantity;
}

public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}





}
