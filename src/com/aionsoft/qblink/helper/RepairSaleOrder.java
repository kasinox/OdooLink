package com.aionsoft.qblink.helper;

import com.aionsoft.qblink.controller.qodbc.SQLManager;
import com.aionsoft.qblink.model.base.Table;
import com.aionsoft.qblink.model.query.Query;

public class RepairSaleOrder {
	
	public RepairSaleOrder(){
		
		addMissingOdooIds();
		addMissingContractNumbers();
		addMissingAddress();
	
		getMissingOdooIds();
		getMissingAddress();
		getMissingContractNumber();
	}
	
	
	
	private void getMissingContractNumber() {
		// TODO Auto-generated method stub
		//		SELECT * FROM salesorder where PONumber IS Null
		
		
		
	}



	private void getMissingAddress() {
		// TODO Auto-generated method stub
		
	}



	private void getMissingOdooIds() {
		// TODO Auto-generated method stub
		
	}



	private void addMissingAddress() {
		// TODO Auto-generated method stub
		
	}

	private void addMissingContractNumbers() {
		// TODO Auto-generated method stub
		
	}

	private void addMissingOdooIds() {
		// TODO Auto-generated method stub
		//issue with manually adding sales order items to quickbooks
//		SELECT refNumber,salesorderlineItemRefFullName,customFieldSalesorderlineodoo_id FROM salesorderline where customFieldSalesorderlineodoo_id is null

		
		SQLManager sqlManager = new SQLManager();
		
		String sql = "SELECT refNumber,salesorderlineItemRefFullName,customFieldSalesorderlineodoo_id FROM salesorderline where customFieldSalesorderlineodoo_id is null";
		Query query = new Query(sql);
		
		Table newTable = sqlManager.getRowsBySQL(query);
		
		System.out.println(newTable);
		
		
	}
	
	public static void main(String ars[]){
		
	}
	



}
