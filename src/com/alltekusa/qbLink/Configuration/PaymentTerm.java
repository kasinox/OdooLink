package com.alltekusa.qbLink.Configuration;

public class PaymentTerm {
	


	String Name;
	String id;
	public PaymentTerm(String name, String id) {
		super();
		Name = name;
		this.id = id;
	}
	
	public String getName() {
		return Name;
	}
	public String getId() {
		return id;
	}
	public void setName(String name) {
		Name = name;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PaymentTerm [Name=" + Name + ", id=" + id + "]";
	}

}
