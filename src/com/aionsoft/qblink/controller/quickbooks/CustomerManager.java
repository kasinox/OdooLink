package com.aionsoft.qblink.controller.quickbooks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aionsoft.qblink.controller.database.ConnectionManager;
import com.aionsoft.qblink.helper.Processor;
import com.aionsoft.qblink.model.quickbooks.Customer;
import com.alltekusa.qbLink.Model.Base.Row;

public class CustomerManager extends QBManager {
	public final static Logger LOGGER = Logger.getLogger(CustomerManager.class.getName());
	private static Connection conn = ConnectionManager.getInstance().getConnection();	

	public CustomerManager (){
		super();
	}
	
	
	
	public boolean insertCustomer(Customer bean){
//		LOGGER.setLevel(Level.INFO);
//		LOGGER.info("INSERT CUSTOMER\n");
		
		String text="";//sqlfieldName
		String text2="";//values
		int j=0;
		if(bean.getRows().get(0).getCells().size()>0){
//			System.out.println(x);
			Row row =bean.getRows().get(0);
			
			for(j=0;j<row.getCells().size()-1;j++){
				
				//check if the column requires insert
				boolean insert =row.getCells().get(j).getColumn().isInsert();
				
				if(insert){
				
					String name =row.getCells().get(j).getColumnName();
					String type=row.getCells().get(j).getColumn().getDataType();
					String value = row.getCells().get(j).getValue();
					value=value.replace("?", "");
					
					value.trim();
					if(value.equals("null")){
						value=value.replace("null", "");
					}		
					
					
					//character lenght Limitation
					value = trimName(name, value);
					//character length LIMITATION ON 
					value = trimAddress(name, value);
										
//					System.out.println(name+","+value+","+type);
					text = text +name+", ";
					if(type.equals("String")){
						text2 = text2+"'"+value+"',";
					}else{
						text2 = text2+value+",";
					}
				}
				
				
			}
		}
		text = text+bean.getColumn(bean.getColumnSize()-1).getColumnName();
		text2 = text2+"'"+bean.getRows().get(0).getCells().get(j).getValue()+"'";

		String sqltext = "INSERT into customer(" + text + ")"+"VALUES ("+text2+")";

//		System.out.println(sqltext);
		boolean insert = new QBManager().insert(sqltext);

		if(insert){
			LOGGER.info("CUSTOMER INSERT SUCCESSFUL");
			return true;
		}else{
			LOGGER.warning("CUSTOMER INSERT FAILED");
			return false;
		}
		

	}
	
	//trimming address to reduce information
	private String trimAddress(String name, String value) {
		if(name.equals("BillAddressAddr1")||name.equals("BillAddressAddr2")||name.equals("BillAddressAddr3")){
//			LOGGER.info("TRIM NAME:"+name);
			int length = value.length();
			if(length>41){
				value=value.substring(0, 40);
			}
//			LOGGER.info("TRIMMED VALUE:"+value);
		}
		return value;
	}
	private String trimName(String name, String value) {
		if(name.equals("name")||name.equals("companyName")){
//			LOGGER.info("TRIM NAME:"+name);
			int length = value.length();
			if(length>31){
				value=value.substring(0, 30);
			}
//			LOGGER.info("TRIMMED VALUE:"+value);
		}
		return value;
	}
	
	public static Logger getLogger() {
		return LOGGER;
	}
	

	
	public static boolean existById(String id) {
		boolean existence = false;
		String sql1 = "SELECT * FROM customer WHERE AccountNumber='"+id+"'";
		ResultSet rs = null;
		existence = exist(sql1);
		return existence;

	}
	
	public static boolean existByName(String name){
		boolean existence = false;
		String sql2 = "SELECT * FROM customer WHERE name='"+name+"'";
		ResultSet rs = null;
		existence = exist(sql2);
			
		return existence;
	}

	
	public Customer getCustomer(String id){
		Customer customer = new Customer();
		
		
		
		return customer;
	}
public static void main(String args[]){
		
		CustomerManager qb = new CustomerManager();
		
//		Table table = qb.getColumnsByTableName("Customer");
//		System.out.println(table);
//		
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//test with customer name direclty
		String name = "test";
		String sql= "select * from customer where name='"+name+"'";
		System.out.println(qb.exist(sql));
		
//		//test with customer class
//		Customer bean = new Customer();
//		bean.setName("test");
//		bean.setAccountNumber("1112");
//		System.out.println(qb.doubleCheckExist(bean));
//		
		
	}

}
