package com.aionsoft.qblink.model.odoo;

public class SaleOrderLineOdoo {

	String so_id;
	String qty;
	String sequence;
	String order_id;
	String price;
	String discount;
	String salesman_id;
	String product_id;
	String so_desc;
	String state;
	String order_partner_id;
	String TemplateRefListID;
	String SaleOrderLineSalesTaxCodeRefListID;
	
	boolean ordered;
	
	public SaleOrderLineOdoo(){
		
	}
	
	public String toString(){
		
		String text = 
				"\tso_id:"+so_id+"\n"
				+"\tqty:"+qty			+"\n"
				+"\tsequence:"+sequence+"\n"
				+"\torder_id:"+order_id+"\n"
				+"\tprice:"+price+"\n"
				+"\tdiscount:"+discount+"\n"
				+"\tsalesman_id:"+salesman_id+"\n"
				+"\tproduct_id:"+product_id+"\n"
				+"\tso_desc:"+so_desc+"\n"
				+"\tstate:"+state+"\n"
				+"\torder_partner_id:"+order_partner_id+"\n"
				+"\tTemplateRefListID:"+TemplateRefListID+"\n"
				+"\tSaleOrderLineSalesTaxCodeRefListID:"+SaleOrderLineSalesTaxCodeRefListID+"\n";
				
		
		
		return text;
	}

	public String getSo_id() {
		return so_id;
	}

	public String getQty() {
		return qty;
	}

	public String getSequence() {
		return sequence;
	}

	public String getOrder_id() {
		return order_id;
	}

	public String getPrice() {
		return price;
	}

	public String getDiscount() {
		return discount;
	}

	public String getSalesman_id() {
		return salesman_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public String getSo_desc() {
		return so_desc;
	}

	public String getState() {
		return state;
	}

	public String getOrder_partner_id() {
		return order_partner_id;
	}

	public String getTemplateRefListID() {
		return TemplateRefListID;
	}

	public String getSaleOrderLineSalesTaxCodeRefListID() {
		return SaleOrderLineSalesTaxCodeRefListID;
	}

	public void setSo_id(String so_id) {
		this.so_id = so_id;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public void setSalesman_id(String salesman_id) {
		this.salesman_id = salesman_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public void setSo_desc(String so_desc) {
		this.so_desc = so_desc;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setOrder_partner_id(String order_partner_id) {
		this.order_partner_id = order_partner_id;
	}

	public void setTemplateRefListID(String templateRefListID) {
		TemplateRefListID = templateRefListID;
	}

	public void setSaleOrderLineSalesTaxCodeRefListID(String saleOrderLineSalesTaxCodeRefListID) {
		SaleOrderLineSalesTaxCodeRefListID = saleOrderLineSalesTaxCodeRefListID;
	}

	public boolean isOrdered() {
		return ordered;
	}

	public void setOrdered(boolean ordered) {
		this.ordered = ordered;
	}
	
	
}
