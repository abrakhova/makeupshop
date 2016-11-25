package fi.haagahelia.course.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fi.haagahelia.course.domain.Category;

@Entity
public class Product {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String brand;
	private String color;
	private double price;
	private String sku;
	private String image;
	
	@ManyToOne
    @JsonIgnore
	@JoinColumn(name = "categoryid")
	private Category category;

	public Product() {
		
	}
	public Product(String name, String brand, String color,double price, String sku, String image, Category category) {
		this.setName(name);
		this.setBrand(brand);
		this.setColor(color);
		this.setPrice(price);
		this.setSku(sku);
		this.setImage(image);
		this.setCategory(category);
		
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", brand=" + brand
				+ ", color=" + color + ", price=" + price + ", sku=" + sku+", image=" + image+"]";
	}
}
