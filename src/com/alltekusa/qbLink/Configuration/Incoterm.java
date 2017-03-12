package com.alltekusa.qbLink.Configuration;

public class Incoterm {
	String Name;
	String id;
	public Incoterm(String name, String id) {
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
		return "Incoterm [Name=" + Name + ", id=" + id + "]";
	}


}
