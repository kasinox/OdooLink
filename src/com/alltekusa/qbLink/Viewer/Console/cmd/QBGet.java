package com.alltekusa.qbLink.Viewer.Console.cmd;

import java.sql.SQLException;
import java.util.ArrayList;

import com.alltekusa.qbLink.Controller.Quickbooks.SalesOrderManager;
import com.alltekusa.qbLink.Odoo.Model.CategoryOdoo;
import com.alltekusa.qbLink.Odoo.Model.CustomerOdoo;
import com.alltekusa.qbLink.Odoo.Model.ProductOdoo;
import com.alltekusa.qbLink.Odoo.Model.VendorOdoo;
import com.alltekusa.qbLink.Quickbooks.Model.SaleOrder;
import com.alltekusa.qbLink.Quickbooks.Process.Helper.ProcessorSaleOrderQbooks;

public class QBGet {
	
	public SaleOrder getSaleOrder(String soNumber ) throws SQLException {
		
//		OdooData salesOrder = new OdooData();
			
//		ProcessorSaleOrderQbooks psq = new ProcessorSaleOrderQbooks();
		
		SalesOrderManager soManager = new SalesOrderManager();
		
		SaleOrder salesOrder = soManager.getSaleOrderByRefNumber(soNumber);
		
		return salesOrder;
	}
	
}
