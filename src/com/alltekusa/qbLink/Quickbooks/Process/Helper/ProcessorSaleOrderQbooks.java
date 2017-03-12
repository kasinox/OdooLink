package com.alltekusa.qbLink.Quickbooks.Process.Helper;

import java.sql.SQLException;

import com.aionsoft.qblink.model.Odoo.ProductOdoo;
import com.aionsoft.qblink.model.Odoo.SaleOrderOdoo;
import com.alltekusa.qbLink.Controller.Quickbooks.ProductManager;
import com.alltekusa.qbLink.Controller.Quickbooks.SalesOrderLineManager;
import com.alltekusa.qbLink.Controller.Quickbooks.SalesOrderManager;
import com.alltekusa.qbLink.Quickbooks.Model.Product;
import com.alltekusa.qbLink.Quickbooks.Model.SaleOrder;

public class ProcessorSaleOrderQbooks {
	
	public ProcessorSaleOrderQbooks(){
		
		System.out.println("ProcessorSaleOrderQbooks");
	}
	
	
	public SaleOrder transformSaleOrder(SaleOrderOdoo so_odoo){
		SaleOrder so= new Transform().getSaleOrder(so_odoo);
		
		return so;
	}

	public SaleOrder getSaleOrder(String soNumber){
		
		
		SaleOrder so = new SalesOrderManager().getSaleOrderByRefNumber(soNumber);
		return  so;
	}

	public boolean insertSaleOrder(SaleOrder so) throws SQLException {
		
//		boolean insert = new SalesOrderManager().insertSaleOrder(so);
		boolean insert = new SalesOrderLineManager().insertSaleOrderLine(so.getSoLines());
		boolean updateSalesRep = new SalesOrderManager().updateSalesRep(so);
		boolean updatePayment = new SalesOrderManager().updatePaymentTerm(so);
		boolean updateIncoterm = new SalesOrderManager().updateIncoterm(so);
		boolean updatePONumber = new SalesOrderManager().updatePONumber(so);
//		boolean updateInvoiceAddr = new SalesOrderManager().updateInvoiceAddress(so);
		boolean updateShipAddr = new SalesOrderManager().updateShipAdress(so);
		if(insert&&updateSalesRep&&updatePayment&updateIncoterm&updatePONumber){
			return true;
		}
		
		return false;

	}
	public boolean existRefNumber(String id){
		
		boolean check = new SalesOrderManager().existRefNumber(id);
		return  check;
	}

}
