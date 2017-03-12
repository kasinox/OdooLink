package com.aionsoft.qblink.configuration.quickbooks;

public class SalesRep {
	
	
	String Name;
	String id;

	
	public SalesRep(String name, String id) {
		super();
		Name = name;
		this.id = id;
	}
	
	
	public String getName() {
		return Name;
	}

	
	public void setName(String name) {
		Name = name;
	}



	@Override
	public String toString() {
		return "SalesRep [Name=" + Name + ", id=" + id + "]";
	}


	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}



	

}
