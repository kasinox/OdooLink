package com.alltekusa.qbLink.Quickbooks.Process.Helper;

import java.sql.SQLException;

import com.aionsoft.qblink.model.Odoo.CategoryOdoo;
import com.aionsoft.qblink.model.Odoo.CustomerOdoo;
import com.alltekusa.qbLink.Controller.Quickbooks.CustomerManager;
import com.alltekusa.qbLink.Controller.Quickbooks.ProductManager;
import com.alltekusa.qbLink.Quickbooks.Model.Customer;
import com.alltekusa.qbLink.Quickbooks.Model.ProductCategory;

public class ProcessorCategoryQbooks {

	public boolean insertCategory(ProductCategory category) throws SQLException {


//		System.out.println(customer.printCustomerValue());
		// Customer customer = new XMLReader().parseNodeContact(nl.item(0));
		boolean insert = new ProductManager().insertProductCategory(category);
		if (insert) {
			return true;
		}

		
		
		return false;

	}
	
	public boolean existCategory(String id){
		
		boolean check = new ProductManager().existByIdCategory(id);
//		boolean check = new CustomerManager().existById(id);
		return  check;
	}
	public boolean existCategoryName(String id){
		
		boolean check = new ProductManager().existByIdCategoryName(id);
//		boolean check = new CustomerManager().existById(id);
		return  check;
	}

	
	public ProductCategory transformCategory(CategoryOdoo categoryOdoo){
		
		ProductCategory category = new Transform().getCategory(categoryOdoo);
		
		return category;
	}

}
