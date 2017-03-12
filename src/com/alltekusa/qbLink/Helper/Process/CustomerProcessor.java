package com.alltekusa.qbLink.Helper.Process;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.aionsoft.qblink.model.odoo.ContactOdoo;
import com.aionsoft.qblink.model.odoo.CustomerOdoo;

public class CustomerProcessor {
	
	int recordNumberContactPartner_id;
	int recordNumberContactInvoice_id;
	int recordNumberContactShipping_id;
	
//	String contact_id;
//	String partner_name;
//	String partner_street;
//	String partner_street2;
//	String partner_city;
//	String partner_zip;
//	String partner_state_id;
//	
//	String invoice_name;
//	String invoice_street;
//	String invoice_street2;
//	String invoice_city;
//	String invoice_zip;
//	String invoice_state_id;
//
//	String shipping_name;
//	String shipping_street;
//	String shipping_street2;
//	String shipping_city;
//	String shipping_zip;
//	String shipping_state_id;
	
	Document document;
	
	CustomerOdoo customer = new CustomerOdoo();

	public CustomerProcessor(){
		
		
		
	}
	public CustomerProcessor(Document sale_order){
		document=sale_order;
		customer.setContact_id(getItem("customer_id"));
		customer.setPartner_name(getItem("partner_name"));
		customer.setPartner_street(getItem("partner_street"));
		customer.setPartner_street2(getItem("partner_street2"));
		customer.setPartner_city(getItem("partner_city"));
		customer.setPartner_zip(getItem("partner_zip"));
		customer.setPartner_state_id(getItem("partner_state_id"));
		
		
//		
//		String Invoice_name=getItem("invoice_name");
//		String Invoice_street=getItem("invoice_street");
//		String Invoice_street2=getItem("invoice_street2");
//		String Invoice_city=getItem("invoice_city");
//		String Invoice_zip=getItem("invoice_zip");
//
//		
//		if(Invoice_street.equals("null")){
////			System.out.println("street:"+Invoice_street);
//			Invoice_street="";
//		}
//		
//		if(Invoice_street2.equals("null")){
////			System.out.println("street2:"+Invoice_street2);
//			Invoice_street2="";
//		}
//		if(Invoice_city.equals("null")){
////			System.out.println("city:"+Invoice_city);
//			Invoice_city="_";
//		}
//		if(Invoice_zip.equals("null")){
////			System.out.println("zip:"+Invoice_zip);
//			Invoice_zip="_";
//		}
//		
//		
//		String invoiceAdd = Invoice_name+" "+Invoice_street+" "+Invoice_street2+" "+Invoice_city+" "+Invoice_zip;
//		if(invoiceAdd.length()>30){
//			Invoice_name=invoiceAdd.substring(0,30);
//			Invoice_street="_";
//			Invoice_street2="_";
//			Invoice_city="_";
//			Invoice_zip="_";
//			if(invoiceAdd.length()>70){
//				Invoice_street=invoiceAdd.substring(31,70);
//				Invoice_street2=invoiceAdd.substring(71,invoiceAdd.length());
//				
//				if(invoiceAdd.length()>110){
//					Invoice_street2=invoiceAdd.substring(71,110);
//					Invoice_city=invoiceAdd.substring(111,invoiceAdd.length());
//					if(invoiceAdd.length()>150){
//						Invoice_city=invoiceAdd.substring(111,150);
//						Invoice_zip=invoiceAdd.substring(151,invoiceAdd.length());
//						if(invoiceAdd.length()>190){
//							Invoice_zip=invoiceAdd.substring(151,190);
//						}
//					}
//				}
//				
//				
//			}else{
//				Invoice_street=invoiceAdd.substring(31,110);
//			}
//		}
//		

		String Shipping_name=getItem("shipping_name");
		String Shipping_street=getItem("shipping_street");
		String Shipping_street2=getItem("shipping_street2");
		String Shipping_city=getItem("shipping_city");
		String Shipping_zip=getItem("shipping_zip");	
		
		if(Shipping_street.equals("null")){
//			System.out.println("ship1:"+Shipping_street);
			Shipping_street=".";
		}
		if(Shipping_street2.equals("null")){
//			System.out.println("ship2:"+Shipping_street2);
			Shipping_street2=".";
		}
		if(Shipping_city.equals("null")){
//			System.out.println("city:"+Shipping_city);
			Shipping_city=".";
		}
		if(Shipping_zip.equals("null")){
//			System.out.println("zip"+Shipping_zip);
			Shipping_zip=".";
		}
		String shipAdd = Shipping_name+" "+Shipping_street+" "+Shipping_street2+" "+Shipping_city+" "+Shipping_zip;
//		System.out.println(shipAdd.length());
//		System.exit(0);
		
		if(shipAdd.length()>30){
			Shipping_name=shipAdd.substring(0,30);
			Shipping_street=".";
			Shipping_street2=".";
			Shipping_city=".";
			Shipping_zip=".";
			
			if(shipAdd.length()>75){
				Shipping_street=shipAdd.substring(37,75).trim();
				Shipping_street2=shipAdd.substring(75, shipAdd.length()).trim();
//				System.out.println(Shipping_street2);
				if(shipAdd.length()>110){
					Shipping_street2=shipAdd.substring(75,110);
					Shipping_city=shipAdd.substring(111,shipAdd.length()).trim();
//					System.out.println(Shipping_street2);
//					System.out.println(Shipping_city);
					if(shipAdd.length()>149){
						Shipping_city="see contract";
						Shipping_zip="00000";
						if(shipAdd.length()>185){
							Shipping_city=shipAdd.substring(150,185).trim();
						}
					}
					
					
				}else{
					

					Shipping_street2=shipAdd.substring(71,shipAdd.length());
				}
			}else{
				Shipping_street=shipAdd.substring(31,shipAdd.length());
				Shipping_street2="";
			}
		}
		
		
//		System.out.println(Shipping_name);
//		System.out.println(Shipping_street);
//		System.out.println(Shipping_street2);
//		System.exit(0);

//		System.exit(0);
		
//		customer.setInvoice_name(getItem("invoice_name"));
//		customer.setInvoice_street(getItem("invoice_street"));
//		customer.setInvoice_street2(getItem("invoice_street2"));
//		customer.setInvoice_city(getItem("invoice_city"));
//		customer.setInvoice_zip(getItem("invoice_zip"));
//		customer.setShipping_name(getItem("shipping_name"));
//		customer.setShipping_street(getItem("shipping_street"));
//		customer.setShipping_street2(getItem("shipping_street2"));
//		customer.setShipping_zip(getItem("shipping_zip"));
//		
//		customer.setInvoice_name(Invoice_name);
//		customer.setInvoice_street(Invoice_street);
//		customer.setInvoice_street2(Invoice_street2);
//		customer.setInvoice_city(Invoice_city);
//		customer.setInvoice_zip(Invoice_zip);
		customer.setShipping_name(Shipping_name);
		customer.setShipping_street(Shipping_street);
		customer.setShipping_street2(Shipping_street2);
		customer.setShipping_city(Shipping_city);
		customer.setShipping_zip(Shipping_zip);	
		}
	public CustomerOdoo getCustomer(){
//		System.out.println(customer);
		return customer;
	}
	public String getCustomerOdooID(Document doc) {
		System.out.println("2.1.3.1-CustomerProcessor-getCustomerOdooId");
		NodeList nl = doc.getElementsByTagName("customer_id");
		Node node_record = nl.item(0);
		String id = node_record.getTextContent();
		// System.out.println("\tCustomer ID"+id);
		return id;
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
