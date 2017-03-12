package com.alltekusa.qbLink.Quickbooks.Process.Helper;

import java.sql.SQLException;

import com.aionsoft.qblink.model.odoo.CategoryOdoo;
import com.aionsoft.qblink.model.odoo.ProductOdoo;
import com.aionsoft.qblink.model.quickbooks.Product;
import com.aionsoft.qblink.model.quickbooks.ProductCategory;
import com.alltekusa.qbLink.Controller.Quickbooks.ProductManager;

public class ProcessorProductQbooks {

	
	public boolean insertProduct(Product product) throws SQLException {


//		System.out.println(customer.printCustomerValue());
		// Customer customer = new XMLReader().parseNodeContact(nl.item(0));
//		System.out.println("insertProduct");
		boolean insert = new ProductManager().insertProduct(product);
		if (insert) {
			return true;
		}

		return false;

	}
	
	public boolean existProduct(String id){
		
		boolean check = new ProductManager().existById(id);;
		return  check;
	}
	public boolean existProductName(String id){
		
		boolean check = new ProductManager().existByProductName(id);
		return  check;
	}

	public Product transformProduct(ProductOdoo productOdoo) {
		Product product = new Transform().getProduct(productOdoo);
		return product;
	}
}
