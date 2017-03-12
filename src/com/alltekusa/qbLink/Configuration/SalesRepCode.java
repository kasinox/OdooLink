package com.alltekusa.qbLink.Configuration;

import java.util.ArrayList;

public class SalesRepCode {
	
	ArrayList<SalesRep> list = new ArrayList<>();
	public SalesRepCode(){
		
		list.add(new SalesRep("Andy","14"));
		list.add(new SalesRep("Bob","34"));
		list.add(new SalesRep("Cathy","21"));
		list.add(new SalesRep("Eric","64"));
		list.add(new SalesRep("Eva","24"));
		list.add(new SalesRep("Fred","16"));
		list.add(new SalesRep("Gary","59"));
		list.add(new SalesRep("Jacki","15"));
		list.add(new SalesRep("Julia","27"));
		list.add(new SalesRep("Kris","57"));
		list.add(new SalesRep("Champ","7"));	
		list.add(new SalesRep("Leo Z","55"));
		list.add(new SalesRep("Peter","22"));
		list.add(new SalesRep("Ryan","58"));
		list.add(new SalesRep("Sherr","28"));
		list.add(new SalesRep("Soma","70"));
		list.add(new SalesRep("Susan","29"));
		list.add(new SalesRep("Cherr","23"));
		list.add(new SalesRep("Tony","67"));	
		list.add(new SalesRep("Vivia","30"));
		list.add(new SalesRep("Wade","32"));
		list.add(new SalesRep("Wayne","8"));
		list.add(new SalesRep("AnWel","46"));
		list.add(new SalesRep("Weny","63"));		
		list.add(new SalesRep("Rock","73"));
		list.add(new SalesRep("David","72"));
		list.add(new SalesRep("Seven","74"));
		list.add(new SalesRep("Kelly","75"));
	
		
	}
	
	public String getRepName(String id){
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
