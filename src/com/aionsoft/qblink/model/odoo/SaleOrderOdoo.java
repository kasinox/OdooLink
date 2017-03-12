package com.aionsoft.qblink.model.odoo;

import java.util.ArrayList;

public class SaleOrderOdoo {
	
	String so_number;
	String origin;
	String amount_tax;
	String payment_term;
	String state;
	String pricelist_id;
	String write_date;
	String user_id;	
	String incoterm;
	String x_contract;
	String x_contract_signed;
	String x_type;
	int recordNumberSaleOrder;
	int recordNumberSaleOrderLine;
	
	CustomerOdoo customer = new CustomerOdoo();
	ArrayList<SaleOrderLineOdoo> lines = new ArrayList<>();

	public SaleOrderOdoo(String so_number, String origin, String amount_tax, String payment_term, String state,
			String pricelist_id, String write_date, String user_id, String incoterm, int recordNumberSaleOrder,
			int recordNumberSaleOrderLine, CustomerOdoo customer, ArrayList<SaleOrderLineOdoo> lines) {
		super();
		this.so_number = so_number;
		this.origin = origin;
		this.amount_tax = amount_tax;
		this.payment_term = payment_term;
		this.state = state;
		this.pricelist_id = pricelist_id;
		this.write_date = write_date;
		this.user_id = user_id;
		this.incoterm = incoterm;
		this.recordNumberSaleOrder = recordNumberSaleOrder;
		this.recordNumberSaleOrderLine = recordNumberSaleOrderLine;
		this.customer = customer;
		this.lines = lines;
	}
	
	
	public SaleOrderOdoo(){
		
	}
	public String toString(){
		

		
		String string ="so#"+so_number+"\n"
		+"\torigin:"+origin+"\n"
		+"\ttax:"+amount_tax+"\n"
		+"\tpayment:"+payment_term+"\n"
		+"\tstate"+state+"\n"
		+"\tpricelist:"+pricelist_id+"\n"
		+"\tcreate date:"+write_date+"\n"
		+"\tuser id:"+user_id+"\n"
		+"\tincoterm:"+incoterm+"\n"
		+"\tso record #:"+recordNumberSaleOrder+"\n"
		+"\tsoline record #:"+recordNumberSaleOrderLine+"\n"
		+"customer information:\n"+getCustomer().toString()+"\n"
		+"sale order lines:\n"+getLines().toString();
		
		return string;
	}
	
	public String getSo_number() {
		return so_number;
	}
	public String getOrigin() {
		return origin;
	}
	public String getAmount_tax() {
		return amount_tax;
	}
	public String getPayment_term() {
		return payment_term;
	}
	public String getState() {
		return state;
	}
	public String getPricelist_id() {
		return pricelist_id;
	}
	public String getWrite_date() {
		return write_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public String getIncoterm() {
		return incoterm;
	}
	public int getRecordNumberSaleOrder() {
		return recordNumberSaleOrder;
	}
	public int getRecordNumberSaleOrderLine() {
		return recordNumberSaleOrderLine;
	}
	public CustomerOdoo getCustomer() {
		return customer;
	}
	public ArrayList<SaleOrderLineOdoo> getLines() {
		return lines;
	}
	public void setSo_number(String so_number) {
		this.so_number = so_number;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public void setAmount_tax(String amount_tax) {
		this.amount_tax = amount_tax;
	}
	public void setPayment_term(String payment_term) {
		this.payment_term = payment_term;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPricelist_id(String pricelist_id) {
		this.pricelist_id = pricelist_id;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}
	public void setRecordNumberSaleOrder(int recordNumberSaleOrder) {
		this.recordNumberSaleOrder = recordNumberSaleOrder;
	}
	public void setRecordNumberSaleOrderLine(int recordNumberSaleOrderLine) {
		this.recordNumberSaleOrderLine = recordNumberSaleOrderLine;
	}
	public void setCustomer(CustomerOdoo customer) {
		this.customer = customer;
	}
	public void setLines(ArrayList<SaleOrderLineOdoo> lines) {
		this.lines = lines;
	}


	public String getX_contract() {
		return x_contract;
	}


	public String getX_contract_signed() {
		return x_contract_signed;
	}


	public String getX_type() {
		return x_type;
	}


	public void setX_contract(String x_contract) {
		this.x_contract = x_contract;
	}


	public void setX_contract_signed(String x_contract_signed) {
		this.x_contract_signed = x_contract_signed;
	}


	public void setX_type(String x_type) {
		this.x_type = x_type;
	}

 
	

}
