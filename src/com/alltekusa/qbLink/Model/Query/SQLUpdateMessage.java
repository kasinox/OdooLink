package com.alltekusa.qbLink.Model.Query;

import java.util.ArrayList;

public class SQLUpdateMessage {
	

	public SQLUpdateMessage(String prefix) {
		super();
		this.prefix = prefix;
	}
	String prefix;
	ArrayList<String> conditions;
	public String getPrefix() {
		return prefix;
	}
	public ArrayList<String> getConditions() {
		return conditions;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setConditions(ArrayList<String> conditions) {
		this.conditions = conditions;
	}
	public void addCondition(String condition){	
		this.conditions.add(condition);
	}
	@Override
	public String toString() {
		return "SQLUpdateMessage [prefix=" + prefix + ",conditions=" + conditions + "]";
	}
	
	public String formUpdateMessageSaleOrderLineStatus(){
		String sqlMessage="UPDATE salesorderLine set customfieldsalesorderlinestatus='"+getPrefix()+"' "
				+ "WHERE ";
		String cond ="";
		for (int i=0;i<conditions.size();i++){
			String txnID="";
			if(i<conditions.size()-1){
				 txnID = "salesorderlinetxnlineID='"+conditions.get(i)+"'"+" OR ";
			}else {
				 txnID = "salesorderlinetxnlineID='"+conditions.get(i)+"'";
			}
		cond=cond+txnID;	
		}
		
		sqlMessage=sqlMessage+cond;
		
//		System.out.println(sqlMessage);
		return sqlMessage;
	}
	
	

}
