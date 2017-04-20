package com.aionsoft.qblink.helper.inventory;

public class InventorySalesOrder {
	

	String location;
	String original_id;
	String duplicate_id;
	
	public InventorySalesOrder(String location, String original_id, String duplicate_id) {
		super();
		this.location = location;
		this.original_id = original_id;
		this.duplicate_id = duplicate_id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getOriginal_id() {
		return original_id;
	}
	public void setOriginal_id(String original_id) {
		this.original_id = original_id;
	}
	public String getDuplicate_id() {
		return duplicate_id;
	}
	public void setDuplicate_id(String duplicate_id) {
		this.duplicate_id = duplicate_id;
	}
	
	public String toString(){
		String text=location+","+original_id+","+duplicate_id;
		return text;
		
	}
	
	

}
