package com.ekart.entities;

public class Product {
	private String name;
	private int id;
	private String imgUrl;
	private double rating;	
	private double price;
	private Category category;
	
	public Product() {
		
	}
	
	public Product(String name, int id, String imgUrl, double rating, double price) {
		super();
		this.name = name;
		this.id = id;
		this.imgUrl = imgUrl;
		this.rating = rating;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", id=" + id + ", imgUrl=" + imgUrl + ", rating=" + rating + ", price=" + price
				+ "]";
	}
	
}
