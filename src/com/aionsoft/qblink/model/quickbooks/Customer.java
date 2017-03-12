package com.aionsoft.qblink.model.quickbooks;

import java.util.ArrayList;

import com.aionsoft.qblink.model.base.Cell;
import com.aionsoft.qblink.model.base.Row;
import com.aionsoft.qblink.model.base.Table;
import com.aionsoft.qblink.model.configuration.Entry;

public class Customer extends Table {
	
	
	static String tableName = "Customer";
	public Customer(){
		super(tableName);
		init();
	}
	
	//load the configuration for customer
	public void init(){

		addColumn("name","String","name",true,true);
		addColumn("companyName","String","name",true,true);
		addColumn("BillAddressAddr1","String","name",true,true);
		addColumn("BillAddressAddr2","String","street",true,true);
		addColumn("BillAddressAddr3","String","street2",true,true);
		addColumn("BillAddressCity","String","city",true,true);
		addColumn("Phone","String","phone",true,true);
		addColumn("Fax","String","fax",true,true);
		addColumn("Email","String","email",true,true);
		addColumn("accountNumber","String","id",true,true);	
		
//		addColumn("contact","String","",true,true);
//		addColumn("firstname","String","",true,true);
//		addColumn("lastname","String","",true,true);
//		addColumn("BillAddressState","String","state",true,true);
//		addColumn("BillAddressPostalCode","String","zip",true,true);
//		addColumn("TermsRefListID","String","",true,true);
//		addColumn("SalesTaxCodeRefListID","String","",true,true);
//		addColumn("PreferredPaymentMethodRefFullName","String","",true,true);
//		addColumn("CreditCardInfoCreditCardNumber","String","",true,true);
//		addColumn("CreditCardInfoExpirationMonth","String","",true,true);
//		addColumn("CreditCardInfoExpirationYear","String","",true,true);
//		addColumn("CreditCardInfoNameOnCard","String","",true,true);
//		addColumn("CreditCardInfoCreditCardAddress","String","",true,true);
//		addColumn("CreditCardInfoCreditCardPostalCode","String","",true,true);
//		addColumn("JobStatus","String","",true,true);
//		addColumn("JobStartDate","String","",true,true);
//		addColumn("JobEndDate","String","",true,true);
//		addColumn("JobDesc","String","",true,true);
//		ConfigManCustomer custConf= new ConfigManCustomer();
//		ArrayList<Entry> entrySet = custConf.getEntrySet();
//		addColumns(entrySet);
	
	}
	public String printCustomerValue(){
		String text ="";

		text = getRows().get(0).getCells().toString();
		
		int size = getRows().get(0).getCells().size();
		System.out.println(size);
		for(int i=0;i<size;i++){
	//			System.out.println(i);
			Cell cel = 	getRows().get(0).getCells().get(i);
			String name = cel.getColumnName();
	
			String value = cel.getValue();
			
			
			text = text + name+"|\t"+value+"\n";
		
				
		}
		return text;
	}
	public String getName(){
		String name1=null;
		int j=0;
		//
		if(getRows().get(0).getCells().size()>0){
			Row row =getRows().get(0);		
			for(j=0;j<row.getCells().size()-1;j++){
				
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
		
			}
		}else{
			return null;
		}
		
		return name1;
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


	public static void main(String[] args){
		System.out.println(new Customer());
	}
	
	
}
