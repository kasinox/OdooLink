package com.alltekusa.qbLink.Odoo.Model;

public class ContactOdoo {

	int recordNumberContactPartner_id;
	int recordNumberContactInvoice_id;
	int recordNumberContactShipping_id;
	
	String contact_id;
	String partner_name;
	String partner_street;
	String partner_street2;
	String partner_city;
	String partner_zip;
	String partner_state_id;
	
	String invoice_name;
	String invoice_street;
	String invoice_street2;
	String invoice_city;
	String invoice_zip;
	String invoice_state_id;

	String shipping_name;
	String shipping_street;
	String shipping_street2;
	String shipping_city;
	String shipping_zip;
	String shipping_state_id;
	
	String phone;
	String fax;
	String email;

	boolean isCustomer =false;
	boolean isVendor =false;
	public ContactOdoo(){
		
	}
	public String toString(){
		String text =
		"\tccontact_id:"+contact_id+"\n"+
		"\tpartner_name:"+partner_name+"\n"+ 
		 "\tpartner_street:"+ partner_street+"\n"+ 
		 "\tpartner_street2:"+ partner_street2+"\n"+
		 "\tpartner_city:"+ partner_city+"\n"+
		 "\tpartner_zip:"+ partner_zip+"\n"+
		 "\tpartner_state_id:"+ partner_state_id+"\n\n"+
		
		 "\tinvoice_name:"+  invoice_name+"\n"+ 
		 "\tinvoice_street:"+ invoice_street+"\n"+ 
		 "\tinvoice_street2:"+ invoice_street2+"\n"+ 
		 "\tinvoice_city:"+ invoice_city+"\n"+ 
		 "\tinvoice_zip:"+ invoice_zip+"\n"+ 
		 "\tinvoice_state_id:"+ invoice_state_id+"\n\n"+

		 "\tshipping_name:"+ shipping_name+"\n"+
		 "\tshipping_street:"+ shipping_street+"\n"+
		 "\tshipping_street2:"+ shipping_street2+"\n"+
		 "\tshipping_city:"+ shipping_city+"\n"+ 
		 "\tshipping_zip:"+ shipping_zip+"\n"+ 
		 "\tshipping_state_id:"+shipping_state_id+
		 "\tphone:"+phone+"\n"+
		 "\tfax:"+fax+"\n"+
		 "\temail"+email;
				
				
				return text;
	}

	public int getRecordNumberContactPartner_id() {
		return recordNumberContactPartner_id;
	}

	public int getRecordNumberContactInvoice_id() {
		return recordNumberContactInvoice_id;
	}

	public int getRecordNumberContactShipping_id() {
		return recordNumberContactShipping_id;
	}



	public String getPartner_name() {
		return partner_name;
	}

	public String getPartner_street() {
		return partner_street;
	}

	public String getPartner_street2() {
		return partner_street2;
	}

	public String getPartner_city() {
		return partner_city;
	}

	public String getPartner_zip() {
		return partner_zip;
	}

	public String getPartner_state_id() {
		return partner_state_id;
	}

	public String getInvoice_name() {
		return invoice_name;
	}

	public String getInvoice_street() {
		return invoice_street;
	}

	public String getInvoice_street2() {
		return invoice_street2;
	}

	public String getInvoice_city() {
		return invoice_city;
	}

	public String getInvoice_zip() {
		return invoice_zip;
	}

	public String getInvoice_state_id() {
		return invoice_state_id;
	}

	public String getShipping_name() {
		return shipping_name;
	}

	public String getShipping_street() {
		return shipping_street;
	}

	public String getShipping_street2() {
		return shipping_street2;
	}

	public String getShipping_city() {
		return shipping_city;
	}

	public String getShipping_zip() {
		return shipping_zip;
	}

	public String getShipping_state_id() {
		return shipping_state_id;
	}

	public void setRecordNumberContactPartner_id(int recordNumberContactPartner_id) {
		this.recordNumberContactPartner_id = recordNumberContactPartner_id;
	}

	public void setRecordNumberContactInvoice_id(int recordNumberContactInvoice_id) {
		this.recordNumberContactInvoice_id = recordNumberContactInvoice_id;
	}

	public void setRecordNumberContactShipping_id(int recordNumberContactShipping_id) {
		this.recordNumberContactShipping_id = recordNumberContactShipping_id;
	}


	public void setPartner_name(String partner_name) {
		this.partner_name = partner_name;
	}

	public void setPartner_street(String partner_street) {
		this.partner_street = partner_street;
	}

	public void setPartner_street2(String partner_street2) {
		this.partner_street2 = partner_street2;
	}

	public void setPartner_city(String partner_city) {
		this.partner_city = partner_city;
	}

	public void setPartner_zip(String partner_zip) {
		this.partner_zip = partner_zip;
	}

	public void setPartner_state_id(String partner_state_id) {
		this.partner_state_id = partner_state_id;
	}

	public void setInvoice_name(String invoice_name) {
		this.invoice_name = invoice_name;
	}

	public void setInvoice_street(String invoice_street) {
		this.invoice_street = invoice_street;
	}

	public void setInvoice_street2(String invoice_street2) {
		this.invoice_street2 = invoice_street2;
	}

	public void setInvoice_city(String invoice_city) {
		this.invoice_city = invoice_city;
	}

	public void setInvoice_zip(String invoice_zip) {
		this.invoice_zip = invoice_zip;
	}

	public void setInvoice_state_id(String invoice_state_id) {
		this.invoice_state_id = invoice_state_id;
	}

	public void setShipping_name(String shipping_name) {
		this.shipping_name = shipping_name;
	}

	public void setShipping_street(String shipping_street) {
		this.shipping_street = shipping_street;
	}

	public void setShipping_street2(String shipping_street2) {
		this.shipping_street2 = shipping_street2;
	}

	public void setShipping_city(String shipping_city) {
		this.shipping_city = shipping_city;
	}

	public void setShipping_zip(String shipping_zip) {
		this.shipping_zip = shipping_zip;
	}

	public void setShipping_state_id(String shipping_state_id) {
		this.shipping_state_id = shipping_state_id;
	}
	public String getContact_id() {
		return contact_id;
	}
	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}
	public boolean isCustomer() {
		return isCustomer;
	}
	public boolean isVendor() {
		return isVendor;
	}
	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}
	public void setVendor(boolean isVendor) {
		this.isVendor = isVendor;
	}
	public String getPhone() {
		return phone;
	}
	public String getFax() {
		return fax;
	}
	public String getEmail() {
		return email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
