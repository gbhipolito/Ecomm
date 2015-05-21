package com.lancer.ecomm.domains;

import java.util.ArrayList;

public class ProductCategory {

	private String name;
	private String strSubcategoriesUrl;
	private ArrayList<ProductSubcategory> alSubcategories;

	public ProductCategory(String name, String subcategoriesUrl, ArrayList<ProductSubcategory> subcategories) {
		setName(name);
		setSubcategoriesUrl(subcategoriesUrl);
		setSubcategories(subcategories);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSubcategoriesUrl() {
		return strSubcategoriesUrl;
	}

	public void setSubcategoriesUrl(String subcategoriesUrl) {
		this.strSubcategoriesUrl = subcategoriesUrl;
	}

	public ArrayList<ProductSubcategory> getSubcategories() {
		return alSubcategories;
	}
	
	public void setSubcategories(ArrayList<ProductSubcategory> alSubcategories) {
		this.alSubcategories = alSubcategories;
	}

} // end ProductSubcategory