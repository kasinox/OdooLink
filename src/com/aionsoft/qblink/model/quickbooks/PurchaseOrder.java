package com.aionsoft.qblink.model.quickbooks;

import java.util.ArrayList;

public class PurchaseOrder{

	public PurchaseOrder(String poRefNumber, String soRefNumber, String vendorName, String expectedDate,
			String shipmethodRefFullName) {
		super();
		this.poRefNumber = poRefNumber;
		this.soRefNumber = soRefNumber;
		this.vendorName = vendorName;
		this.expectedDate = expectedDate;
		this.shipmethodRefFullName = shipmethodRefFullName;
	}
	public PurchaseOrder(ArrayList<PurchaseOrderLine> poLines, String poRefNumber,
			String soRefNumber) {
		
		this.poLines = poLines;
		this.poRefNumber = poRefNumber;
		this.soRefNumber = soRefNumber;
	}
	
	ArrayList<PurchaseOrderLine> poLines = new ArrayList<PurchaseOrderLine>();
	
	String poRefNumber;
	String soRefNumber;
	String vendorName;
	String expectedDate;
	String shipmethodRefFullName;
	public PurchaseOrder(){

		
	}
	public ArrayList<PurchaseOrderLine> getPoLines() {
		return poLines;
	}
	public String getPoRefNumber() {
		return poRefNumber;
	}
	public String getSoRefNumber() {
		return soRefNumber;
	}
	public void setPoLines(ArrayList<PurchaseOrderLine> poLines) {
		this.poLines = poLines;
	}
	public void setPoRefNumber(String poRefNumber) {
		this.poRefNumber = poRefNumber;
	}
	public void setSoRefNumber(String soRefNumber) {
		this.soRefNumber = soRefNumber;
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
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}
	public void setShipmethodRefFullName(String shipmethodRefFullName) {
		this.shipmethodRefFullName = shipmethodRefFullName;
	}
	@Override
	public String toString() {
		return "PurchaseOrder [" + poRefNumber + "\t" +vendorName + "\t" + expectedDate + "\t" + shipmethodRefFullName
				+ "]";
	}
	
	
	
	

}
