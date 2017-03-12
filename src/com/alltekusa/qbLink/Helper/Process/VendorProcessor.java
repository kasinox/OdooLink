package com.alltekusa.qbLink.Helper.Process;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.aionsoft.qblink.model.Odoo.VendorOdoo;

public class VendorProcessor extends CustomerProcessor{
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
	
	Document document;
	
	VendorOdoo vendor = new VendorOdoo();
	public VendorProcessor(Document doc) {
		document=doc;
		vendor.setVendor_id(getItem("id"));
		vendor.setPartner_name(getItem("name"));
		
		vendor.setPartner_street(getItem("street"));
		vendor.setPartner_street2(getItem("street2"));
		vendor.setPartner_city(getItem("city"));
		vendor.setPartner_zip(getItem("zip"));
		vendor.setPartner_state_id(getItem("state_id"));
		vendor.setPhone(getItem("phone"));
		vendor.setFax(getItem("fax"));
		vendor.setEmail(getItem("email"));
		
	}

	public VendorOdoo getVendor() {
		// TODO Auto-generated method stub
		return vendor;
	}
	
	public String getItem(String text){
		NodeList nl = document.getElementsByTagName(text);
		Node node_record = nl.item(0);
		String id = node_record.getTextContent();
		return id;
	}
	public int getItemInt(String text) {
		NodeList nl = document.getElementsByTagName(text);
		Node node_record = nl.item(0);
		String id = node_record.getTextContent();
		int value = Integer.parseInt(id);
		return value;
	}
	
	

}
