package com.odoolink.controller.managers.postgresql.model;

import java.util.ArrayList;

import javax.xml.crypto.Data;

public class Query {
	
	String query;
	ArrayList<Arg> args = new ArrayList<>();
	
	public Query(String sql){
		this.query=sql;
	}
	public Query(String sql,ArrayList<Arg> args2){
		this.query=sql;
		this.args=args2;
	}

	public String getQuery() {
		return query;
	}
	public ArrayList<Arg> getArgs() {
		return args;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}

	public void addArgs(Arg arg){
		args.add(arg);
	}
	public void addArgs(DataType type, Object value){
		Class cl = value.getClass();
		String name = cl.getSimpleName();
		System.out.println(cl.getSimpleName());			
			Arg string = new Arg(type,value.toString());
			args.add(string);
		
////		
//		Arg arg = new Arg(type,value);
//		args.add(arg);
	}
	
	public static void main(String args[]){
		Query q = new Query("");
		q.addArgs(DataType.Boolean,"");
		q.addArgs(DataType.Int,2);
	}

}
