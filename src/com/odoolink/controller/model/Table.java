package com.odoolink.controller.model;

import java.util.ArrayList;

public class Table {
	
	private String tableName;
	private ArrayList<Column> columns = new ArrayList<>();
	private ArrayList<Row> rows = new ArrayList<>();
	public Table(String tableName) {
		super();
		this.tableName = tableName;
	}

	public Table() {
		// TODO Auto-generated constructor stub
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public ArrayList<Column> getColumns() {
		return columns;
	}
	public void addColumns(Column column){
		columns.add(column);
	}
	
	public int getColumnSize(){
		return columns.size();
	}
	public void addRows(Row row){
		rows.add(row);
	}
	public int getRowSize(){
		return rows.size();
	}
	public ArrayList<Row> getRows(){
		return rows;
	}
	public void printRows(){
		
		for(int i=0;i<rows.size();i++){
			System.out.println(rows.get(i));
		}
		
	}

	public String toString() {
		
		String toString=tableName+"\n";
		for(int i=0;i<columns.size();i++){
			toString=toString+columns.get(i)+"\n";
		}
		
		return toString;
	}
	
	

}
