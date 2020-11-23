package net.lhc.cakeshop.entitys;

public class Cake {
	private int id;
	private String name;//名称
	private float price;//价格
	private int size;//尺寸
	private String image;//图片
	private String description;//介绍
	private int stock;//库存
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
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
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Cake [id=" + id + ", name=" + name + ", price=" + price + ", size=" + size + ", image=" + image
				+ ", description=" + description + ", stock=" + stock + "]";
	}
	
}
