package com.alltekusa.qbLink.Report.Manager;

import java.util.ArrayList;

import com.alltekusa.qbLink.Quickbooks.Model.SaleOrder;

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
