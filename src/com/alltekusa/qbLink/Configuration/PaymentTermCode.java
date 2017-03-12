package com.alltekusa.qbLink.Configuration;

import java.util.ArrayList;

public class PaymentTermCode {

	
	ArrayList<PaymentTerm> list = new ArrayList<>();
	public PaymentTermCode(){
		
		list.add(new PaymentTerm("ADD-ON CONTRACT","13"));
		list.add(new PaymentTerm("EUR PREPAY","16"));
		list.add(new PaymentTerm("IRREV. L/C AT SIGHT","4"));
		list.add(new PaymentTerm("EURO Net 30","15"));
		list.add(new PaymentTerm("NET 10","2"));
//		list.add(new PaymentTerm("NET 15",""));
		list.add(new PaymentTerm("NET 30","3"));
		list.add(new PaymentTerm("NET 45","10"));
//		list.add(new PaymentTerm("NET 60",""));
		list.add(new PaymentTerm("RMB PREPAY","5"));
		list.add(new PaymentTerm("RMB VAT","9"));
		list.add(new PaymentTerm("USD PREPAY","7"));
//		list.add(new PaymentTerm("USD T/T",""));
		list.add(new PaymentTerm("WARRANTY","14"));
	}
	
	public String getPaymentTerm(String id){
		String name ="";
		for (int i=0;i<list.size();i++){
			String id2 = list.get(i).getId();
			
			if(id2.equals(id)){
				name = list.get(i).getName();
			}
		}
		return name;
	}
	
}
