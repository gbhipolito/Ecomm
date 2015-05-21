package com.lancer.ecomm.domains;

import java.util.ArrayList;

public class ProductSubcategory {

	private String name;
	private String strProductsUrl;
	private ArrayList<Product> alProducts;

	public ProductSubcategory(String name, String productsUrl, ArrayList<Product> products) {
		setName(name);
		setProductsUrl(productsUrl);
		setProducts(products);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getProductsUrl() {
		return strProductsUrl;
	}

	public void setProductsUrl(String productsUrl) {
		this.strProductsUrl = productsUrl;
	}

	public ArrayList<Product> getProducts() {
		return alProducts;
	}
	
	public void setProducts(ArrayList<Product> alProducts) {
		this.alProducts = alProducts;
	}

} // end ProductSubcategory