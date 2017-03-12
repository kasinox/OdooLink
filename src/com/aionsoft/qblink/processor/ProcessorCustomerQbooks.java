package com.aionsoft.qblink.processor;

import java.sql.SQLException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.aionsoft.qblink.controller.quickbooks.CustomerManager;
import com.aionsoft.qblink.model.odoo.CustomerOdoo;
import com.aionsoft.qblink.model.quickbooks.Customer;

public class ProcessorCustomerQbooks {

	
	public boolean insertCustomer(Customer customer) throws SQLException {

		boolean insert = new CustomerManager().insertCustomer(customer);
		if (insert) {
			return true;
		}
		System.out.println("insert Customer Failed"+customer.getName());
		System.exit(0);
		return false;

	}
	
	public boolean existCustomer(String id){
		
		boolean check = new CustomerManager().existById(id);
		return  check;
	}
	public boolean existCustomerName(String name){
		boolean check = new CustomerManager().existByName(name);
		return  check;
	}

	public Customer transformCustomer(CustomerOdoo customerOdoo) throws SQLException {

		Customer customer = new Transform().getCustomer(customerOdoo);
		return customer;
	}
}
