package com.aionsoft.qblink.model.quickbooks;

public class Item extends Product{
	int quantity;
	double averageCost;
	String name;
	String odoo_id;
	String description;
	
	//for inventory count
	
	
	//for inventory value

	public Item(double averageCost, String name) {
		super();
		this.averageCost = averageCost;
		this.name = name;
	}
	
	
	

	public double getAverageCost() {
		return averageCost;
	}
	public String getName() {
		return name;
	}
	public void setAverageCost(double averageCost) {
		this.averageCost = averageCost;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Item [name=" + name + ", averageCost=" + averageCost+"]";
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
