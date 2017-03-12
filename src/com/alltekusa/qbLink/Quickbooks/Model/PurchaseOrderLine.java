package com.alltekusa.qbLink.Quickbooks.Model;

import java.util.Date;

public class PurchaseOrderLine {
	String refNumber;
	String customFieldOther1;
	String vendorName;
	String expectedDate;
	String shipmethodRefFullName;
	String name;
	String id;
	int qty;
	String rate;
	String amount;
	int row;
	String status;
	String txnId;
	boolean received;
	int receivedQty;
//	Date shipDate;
	public PurchaseOrderLine(String refNumber, String customFieldOther1, String vendorName, String expectedDate,
			String shipmethodRefFullName, String name, String id, int qty, String rate, 
			String amount,int row, String status,String txnId,boolean received,int receivedQty) {
		super();
		this.refNumber = refNumber;
		this.customFieldOther1 = customFieldOther1;
		this.vendorName = vendorName;
		this.expectedDate = expectedDate;
		this.shipmethodRefFullName = shipmethodRefFullName;
		this.name = name;
		this.id = id;
		this.qty = qty;
		this.rate = rate;
		this.amount = amount;
		this.row=row;
		this.status=status;
		this.txnId=txnId;
		this.received=received;
		this.receivedQty=receivedQty;
	}
	
	public PurchaseOrderLine(){
		
	}
	
	public String getRefNumber() {
		return refNumber;
	}
	public String getCustomFieldOther1() {
		return customFieldOther1;
	}
	public String getVendorName() {
		return vendorName;
	}
	public String getExpectedDate() {
		return expectedDate;
	}
	public String getShipmethodRefFullName() {
		return shipmethodRefFullName;
	}
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
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
	public void setCustomFieldOther1(String customFieldOther1) {
		this.customFieldOther1 = customFieldOther1;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}
	public void setShipmethodRefFullName(String shipmethodRefFullName) {
		this.shipmethodRefFullName = shipmethodRefFullName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(String id) {
		this.id = id;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "" + refNumber 
				+"\t"+row
//				+"\t"+txnId
				+"\t"+expectedDate
				+"\t"+receivedQty
				+"\t"+received	
				+"\t" + qty
				+"\t" + rate
				+ "\t" + amount 
				+ "\t" + name 
				+ "";
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public boolean isReceived() {
		return received;
	}

	public void setReceived(boolean received) {
		this.received = received;
	}

	public int getReceivedQty() {
		return receivedQty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public void setReceivedQty(int receivedQty) {
		this.receivedQty = receivedQty;
	}

	public int getQty() {
		return qty;
	}

			

}
