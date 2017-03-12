package com.alltekusa.qbLink.Model.Base;

/*
 * 
 * columns are the attribute for each of the objects
 */
public class Column {
	String ColumnName;
	String dataType;
	boolean update;
	boolean insert;
	String field;
		
	public Column(String columnName, String dataType) {
		super();
		this.ColumnName = columnName;
		this.dataType = dataType;
	}
	public Column(String columnName, String dataType,boolean update,boolean insert) {
		super();
		this.ColumnName = columnName;
		this.dataType = dataType;
		this.insert = insert;
		this.update = update;
	}
	public Column(String columnName, String dataType,String field,boolean update,boolean insert) {
		super();
		this.ColumnName = columnName;
		this.dataType = dataType;
		this.insert = insert;
		this.update = update;
		this.field=field;
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
		
		String toString = "<entry name='"+ColumnName+"' type='"+dataType+"' create='false' update='false'></entry>";
		
		return toString;
	}

	public boolean isUpdate() {
		return update;
	}

	public boolean isInsert() {
		return insert;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public void setInsert(boolean insert) {
		this.insert = insert;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	

}
