package com.aionsoft.qblink.view;

import java.sql.SQLException;
import java.util.ArrayList;

import com.aionsoft.qblink.controller.quickbooks.SalesOrderManager;
import com.aionsoft.qblink.model.odoo.CategoryOdoo;
import com.aionsoft.qblink.model.odoo.CustomerOdoo;
import com.aionsoft.qblink.model.odoo.ProductOdoo;
import com.aionsoft.qblink.model.odoo.VendorOdoo;
import com.aionsoft.qblink.model.quickbooks.SaleOrder;
import com.aionsoft.qblink.processor.ProcessorSaleOrderQbooks;

public class QBGet {
	
	public SaleOrder getSaleOrder(String soNumber ) throws SQLException {
		
//		OdooData salesOrder = new OdooData();
			
//		ProcessorSaleOrderQbooks psq = new ProcessorSaleOrderQbooks();
		
		SalesOrderManager soManager = new SalesOrderManager();
		
		SaleOrder salesOrder = soManager.getSaleOrderByRefNumber(soNumber);
		
		return salesOrder;
	}
	
}
