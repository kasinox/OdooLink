package com.aionsoft.qblink.model.query;

public class Arg {
	
	String columnName;
	String value;
	DataType dataType;

	public Arg(){
		
	}
	public Arg(DataType type,String ColumnName, String value){
		
		this.dataType=type;
		this.columnName=ColumnName;
		this.value=value;
	}
	public Arg(DataType type, String value){
		
		this.dataType=type;
		
		this.value=value;
	}
	public Arg(DataType type,String ColumnName,boolean value){
		this.dataType=type;
		this.value=Boolean.toString(value);
	}
	public Arg(DataType type,String ColumnName,int value){
		this.dataType=type;
		this.value=Integer.toString(value);
	}
	public Arg(String ColumnName,String Value){
		this.columnName=ColumnName;
		this.value=value;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public String getValue() {
		return value;
	}

	public void setName(String name){
		this.columnName=name;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String toString(){
		return value;
	}
	
}
