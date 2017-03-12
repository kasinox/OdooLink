package com.aionsoft.qblink.model.base;

import java.util.ArrayList;

import com.aionsoft.qblink.model.configuration.Entry;

public class Table {
	
	private String tableName;
	private ArrayList<Column> columns;
	private ArrayList<Row> rows;
	public Table(String tableName) {
//		System.out.println("const 1");
		this.tableName = tableName;
		this.columns= new ArrayList<>();
		this.rows= new ArrayList<>();
	}

	public Table() {
		// TODO Auto-generated constructor stub
//		System.out.println("const 2");
		this.tableName = "";
		this.columns= new ArrayList<>();
		this.rows= new ArrayList<>();
	}

	public Column getColumn(int index){
		return columns.get(index);
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
	public void addColumn(Column column){
		columns.add(column);
	}
	public void addColumns(Column column){
		columns.add(column);
	}
	public void addColumns(ArrayList<Entry> entrySet) {
		for (int i=0;i<entrySet.size();i++){
			Entry entry = entrySet.get(i);
			String name = entry.getName();
			String type = entry.getType();
			Column col = new Column(name,type);
			addColumn(col);
		}
	}
	public void addColumn(String name,String type){
		Column col = new Column(name,type);
		columns.add(col);
	}
	public void addColumn(String name,String type,String field,boolean update,boolean insert){
		Column col = new Column(name,type,field,update,insert);
		columns.add(col);
	}
	public void addColumn(String name,String type,boolean update,boolean insert){
		Column col = new Column(name,type,update,insert);
		columns.add(col);
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
		
		String toString="<tableName>"+tableName+"</tableName>"+"\n";
		for(int i=0;i<columns.size();i++){
			toString=toString+columns.get(i)+"\n";
		}
		
		return toString;
	}
	public String printColumns(){
		String toString="<tableName>"+tableName+"</tableName>"+"\n";
		for(int i=0;i<columns.size();i++){
			toString=toString+columns.get(i)+"\n";
		}
		
		return toString;
	}
	
	

}
