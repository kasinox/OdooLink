package com.alltekusa.qbLink.Model.Query;

public enum DataType {
	String("String"), Int("Int"), 
	Boolean("Boolean"),Double("Double");

	private String nameAsString;
	
	private DataType(String nameAsString){
		this.nameAsString=nameAsString;
	}
	
	public String toString(){
		return this.nameAsString;
	}
}
