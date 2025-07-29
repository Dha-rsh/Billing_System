package com.billing.repository.dto;

public class Product {
	private int productId;
	private String name;
	private double pricePerUnit;
	private int stock;
	public Product(int productId, String name, double pricePerUnit,int stock) {
		super();
		this.productId = productId;
		this.name = name;
		this.pricePerUnit = pricePerUnit;
		this.stock=stock;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
}
