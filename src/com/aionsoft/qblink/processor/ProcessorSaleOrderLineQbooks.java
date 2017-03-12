package com.aionsoft.qblink.processor;

import java.sql.SQLException;
import java.util.ArrayList;

import com.aionsoft.qblink.controller.quickbooks.ProductManager;
import com.aionsoft.qblink.controller.quickbooks.SalesOrderLineManager;
import com.aionsoft.qblink.controller.quickbooks.SalesOrderManager;
import com.aionsoft.qblink.model.odoo.CategoryOdoo;
import com.aionsoft.qblink.model.odoo.SaleOrderLineOdoo;
import com.aionsoft.qblink.model.quickbooks.ProductCategory;
import com.aionsoft.qblink.model.quickbooks.SaleOrderLine;

public class ProcessorSaleOrderLineQbooks {
	

	
	public boolean insertSaleOrderLine(SaleOrderLine soLine) throws SQLException{
		
		
		boolean insert = new SalesOrderLineManager().insertSaleOrderLine(soLine);
		
		if(insert){
			return true;
		}
		
		return false;
		
	}
	
//	public boolean existSaleOrderLine(String id){
//		
//		boolean check = new ProductManager().existByIdCategory(id);
////		boolean check = new CustomerManager().existById(id);
//		return  check;
//	}
//	public boolean existSaleOrderLineID(String id){
//		
//		boolean check = new ProductManager().existByIdCategoryName(id);
////		boolean check = new CustomerManager().existById(id);
//		return  check;
//	}

	
//	public SaleOrderLine transformSaleOrderLine(SaleOrderLineOdoo soLineOdoo){
//		
//		SaleOrderLine soLine = new Transform().getSaleOrderLine(soLineOdoo);
//		
//		return soLine;
//	}

}
