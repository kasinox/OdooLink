package com.alltekusa.qbLink.Viewer.Console.cmd;

import java.sql.SQLException;
import java.util.ArrayList;

import com.aionsoft.qblink.model.Odoo.CategoryOdoo;
import com.aionsoft.qblink.model.Odoo.CustomerOdoo;
import com.aionsoft.qblink.model.Odoo.ProductOdoo;
import com.aionsoft.qblink.model.Odoo.VendorOdoo;
import com.alltekusa.qbLink.Controller.Quickbooks.SalesOrderManager;
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
