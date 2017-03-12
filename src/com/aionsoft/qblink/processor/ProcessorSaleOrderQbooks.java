package com.aionsoft.qblink.processor;

import java.sql.SQLException;

import com.aionsoft.qblink.controller.quickbooks.ProductManager;
import com.aionsoft.qblink.controller.quickbooks.SalesOrderLineManager;
import com.aionsoft.qblink.controller.quickbooks.SalesOrderManager;
import com.aionsoft.qblink.model.odoo.ProductOdoo;
import com.aionsoft.qblink.model.odoo.SaleOrderOdoo;
import com.aionsoft.qblink.model.quickbooks.Product;
import com.aionsoft.qblink.model.quickbooks.SaleOrder;

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
