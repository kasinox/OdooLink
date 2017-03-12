package com.aionsoft.qblink.model.quickbooks;

import java.util.ArrayList;
import java.util.Date;

import com.aionsoft.qblink.model.base.Table;
import com.aionsoft.qblink.model.configuration.Entry;

public class SaleOrderLine extends Table{
	static String tableName = "saleOrderLine";
	
	String refNumber;
	String name;
	String id;
	String qty;
	String rate;
	String amount;
	int row;
	boolean ordered;
	String status;
	String txnID;
	String PONumber;
	Date Shipdate;
	
	
	public SaleOrderLine(String refNumber, String name, String id, String qty, String rate,
			String amount, int row, String status, String txnID) {
		
		this.refNumber = refNumber;
		this.name = name;
		this.id = id;
		this.qty = qty;
		this.rate = rate;
		this.amount = amount;
		this.row=row;
		this.ordered=false;
		this.status=status;
		this.txnID=txnID;
	}


	public SaleOrderLine(){
		super(tableName);
		init();
	}
	
	public void init(){
//		ConfigManSalesOrderLine solineconfig= new ConfigManSalesOrderLine();
//		ArrayList<Entry> entrySet = solineconfig.getEntrySet();
//		addColumns(entrySet)	
		addColumn("CustomerRefListID","String","order_partner_id",true,true);//use invoice id to find customer
		addColumn("TemplateRefListID","String","TemplateRefListID",true,true);
		addColumn("RefNumber","String","so_number",true,true);//sales Order number
		addColumn("SalesOrderLineItemRefListID","String","product_id",true,true);//product_id
		addColumn("SalesOrderLineDesc","String","so_desc",true,true);
		addColumn("SalesOrderLineQuantity","double","qty",true,true);
		addColumn("SalesOrderLineRate","double","price",true,true);//rate
		addColumn("CustomFieldSalesOrderLineOther1","String","",true,true);
		
		addColumn("SalesOrderLineSalesTaxCodeRefListID","double","SalesOrderLineSalesTaxCodeRefListID",true,true);
	}
	public static void main(String[] args){
		System.out.println(new Customer());
	}

	public String getRefNumber() {
		return refNumber;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getQty() {
		return qty;
	}

	public String getRate() {
		return rate;
	}

	public String getAmount() {
		return amount;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}


	public int getRow() {
		return row;
	}

	public boolean isOrdered() {
		return ordered;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setOrdered(boolean ordered) {
		this.ordered = ordered;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getTxnID() {
		return txnID;
	}


	public void setTxnID(String txnID) {
		this.txnID = txnID;
	}


	public String getPONumber() {
		return PONumber;
	}


	public void setPONumber(String pONumber) {
		PONumber = pONumber;
	}


	public Date getShipdate() {
		return Shipdate;
	}


	public void setShipdate(Date shipdate) {
		Shipdate = shipdate;
	}
	public String toString(){
		
		return 	refNumber+"\t"+row+"\t"+PONumber+"\t"+status+"\t"+qty+"\t"+rate+"\t"+amount+"\t"+name ;
	}
	public String toJson(){
		String text = "";
		
		text = "{"
				+ "\"SaleorderLine\":{"
				+ "}"
				+ "}";
		
		return text;
		
	}
	
	


	
}
