package com.aionsoft.qblink.model.odoo;

public class CustomerOdoo extends ContactOdoo {
	
//	String cu0stomer_id;
	
	public CustomerOdoo(){
		
	}

	public String getCustomer_id() {
		return contact_id;
	}

	public void setCustomer_id(String customer_id) {
		this.contact_id = customer_id;
		isCustomer=true;
	}

	
}
