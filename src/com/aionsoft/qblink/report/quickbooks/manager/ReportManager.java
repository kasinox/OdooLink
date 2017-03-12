package com.aionsoft.qblink.report.quickbooks.manager;

import java.util.ArrayList;

import com.aionsoft.qblink.model.quickbooks.SaleOrder;

public class ReportManager {
	
	
	public ReportManager(){
		
	}
	
	public ArrayList<SaleOrder> getOpenSaleOrder(){
		String sql ="SELECT * FROM salesorder where isManuallyClosed=false or isFullyInvoiced=false";
		
		
		
		return null;
	}
	
	public ArrayList<SaleOrder> getClosedSaleOrder(){
		String sql ="SELECT * FROM salesorder where isManuallyClosed=true or isFullyInvoiced=true";
		
		return null;
	}
	
	public ArrayList<SaleOrder> getOpenSaleorderLine(){
		String sql = "SELECT * FROM salesorderline where isManuallyClosed=false or isFullyInvoiced=false";
		
		return null;
	}
	
	
	//need to separate machine and parts order

	
}
