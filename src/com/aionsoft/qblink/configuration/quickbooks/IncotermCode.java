package com.aionsoft.qblink.configuration.quickbooks;

import java.util.ArrayList;

public class IncotermCode {
	ArrayList<Incoterm> list = new ArrayList<>();
	public IncotermCode(){
		
		list.add(new Incoterm("EXW","1"));
		list.add(new Incoterm("FCA","2"));
		list.add(new Incoterm("FAS","3"));
		list.add(new Incoterm("FOB","4"));
		list.add(new Incoterm("CFR","5"));
		list.add(new Incoterm("CIF","6"));
		list.add(new Incoterm("CPT","7"));
		list.add(new Incoterm("CIP","8"));
		list.add(new Incoterm("DAF","9"));
		list.add(new Incoterm("DES","10"));
		list.add(new Incoterm("DEQ","11"));
		list.add(new Incoterm("DDU","12"));
		list.add(new Incoterm("DAT","13"));
		list.add(new Incoterm("DAP","14"));
		list.add(new Incoterm("DDP","15"));


	}
	
	public String getIncoterm(String id){
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
