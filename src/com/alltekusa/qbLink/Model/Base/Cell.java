package com.alltekusa.qbLink.Model.Base;

public class Cell {
	
	String value="";
	Column column;

	public Cell(String value, Column column) {
		super();
		this.value = value;
		this.column = column;
	}
	public Cell(){
		
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Column getColumn() {
		return column;
	}
	public void setColumn(Column column) {
		this.column = column;
	}
	public String getColumnName(){
		return column.getColumnName();
	}
	public String toString(){
		return value;
	}

	
	
}
