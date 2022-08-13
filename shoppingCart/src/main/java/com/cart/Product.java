package com.cart;

public class Product {
private String productCode;
private String productName;
private String discription;
private Double unitPrice;
private Integer quantity;

public Product(String productCode, String productName, String discription, Double unitPrice, Integer quantity) {
	this.productCode = productCode;
	this.productName = productName;
	this.discription = discription;
	this.unitPrice = unitPrice;
	this.quantity = quantity;
}
public Product(String productName, Double unitPrice) {
	this.productName = productName;
	this.unitPrice = unitPrice;
}
public String getProductCode() {
	return productCode;
}
public void setProductCode(String productCode) {
	this.productCode = productCode;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getDiscription() {
	return discription;
}
public void setDiscription(String discription) {
	this.discription = discription;
}
public Double getUnitPrice() {
	return unitPrice;
}
public void setUnitPrice(Double unitPrice) {
	this.unitPrice = unitPrice;
}
public Integer getQuantity() {
	return quantity;
}
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}
}
