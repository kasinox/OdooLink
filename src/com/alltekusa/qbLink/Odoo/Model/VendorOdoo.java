package com.alltekusa.qbLink.Odoo.Model;

public class VendorOdoo extends ContactOdoo {
	
	public VendorOdoo(){
		
	}
	
	public String getVendor_id(){
		return contact_id;
	}
	public void setVendor_id(String vendor_id){
		this.contact_id = vendor_id;
		isVendor=true;
	}

}
