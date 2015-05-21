package com.lancer.ecomm.domains;

public class Product {

	private String name;
	private int price;
	private String image;
	private String description;

	public Product(String name, int price, String image, String description) {
		setName(name);
		setPrice(price);
		setImage(image);
		setDescription(description);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

} // end Product