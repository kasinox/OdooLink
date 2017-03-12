package com.aionsoft.qblink.processor;

import java.sql.SQLException;

import com.aionsoft.qblink.controller.quickbooks.CustomerManager;
import com.aionsoft.qblink.controller.quickbooks.VendorManager;
import com.aionsoft.qblink.model.odoo.CustomerOdoo;
import com.aionsoft.qblink.model.odoo.VendorOdoo;
import com.aionsoft.qblink.model.quickbooks.Customer;
import com.aionsoft.qblink.model.quickbooks.Vendor;

public class ProcessorVendorQbooks {

	
	public boolean insertVendor(Vendor vendor) throws SQLException {

		boolean insert = new VendorManager().insertVendor(vendor);
		if (insert) {
			return true;
		}
		System.out.println("Vendor Insert Failed");
		System.exit(0);
		return false;

	}
	
	public boolean existVendor(String id){
		
		boolean check = new VendorManager().existById(id);
		return  check;
	}
	public boolean existVendorName(String name){
		boolean check = new VendorManager().existByName(name);
		return  check;
	}

	public Vendor transformCustomer(VendorOdoo vendorOdoo) throws SQLException {

		Vendor vendor = new Transform().getVendor(vendorOdoo);
		return vendor;
	}
}
