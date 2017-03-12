package com.alltekusa.qbLink.Quickbooks.Model;

public class Item extends Product{
	
	public Item(double averageCost, String name) {
		super();
		this.averageCost = averageCost;
		this.name = name;
	}
	double averageCost;
	String name;

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
	
	

}
