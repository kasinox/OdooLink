package com.odoolink.controller.model;

public class Column {
	String ColumnName;
	String dataType;
		
	public Column(String columnName, String dataType) {
		super();
		this.ColumnName = columnName;
		this.dataType = dataType;
	}
	
	public Column() {
		super();
	}

	public String getColumnName() {
		return ColumnName;
	}
	public void setColumnName(String columnName) {
		ColumnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	@Override
	public String toString() {
		
		String toString = ColumnName+"\t\t"+dataType;
		
		return toString;
	}
	
	

}
