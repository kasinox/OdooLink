package com.aionsoft.qblink.configuration.quickbooks;

public class Currency {
	String Name;
	String id;
	double rate;
	
	public Currency(String name, String id, double rate) {
		super();
		Name = name;
		this.id = id;
		this.rate = rate;
	}

	public String getName() {
		return Name;
	}
	public String getId() {
		return id;
	}
	public double getRate() {
		return rate;
	}
	public void setName(String name) {
		Name = name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}


}
