package com.aionsoft.qblink.model.report;

import java.util.ArrayList;

public class TBOReport {
	
	public TBOReport(){
		
		System.out.println("TBO REPORT");
	}

	public ArrayList<String> listOpenSalesOrder(){
		
		return null;
		
	}
	public ArrayList<String> listOpenPurchaseOrder() {
		
		String sql ="select customfieldOther1,refNumber,vendorRefFullName,  expecteddate,shipmethodrefFullName,fob "
				+ "from purchaseorder where isfullyreceived=false and ismanuallyclosed=false";
		return null;
		
	}
	public void CheckingSOvPO(){
		
	}
	
	
}
