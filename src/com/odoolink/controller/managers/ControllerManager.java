package com.odoolink.controller.managers;
import java.io.PrintWriter;
import java.sql.Connection;

import com.odoolink.dbConnector.ConnectionManagerPostgreSQL;

public class ControllerManager {

	private static Connection conn = ConnectionManagerPostgreSQL.getInstance()
			.getConnection();

	
	public void printXML(PrintWriter writer, String searchField, String searchFieldValue) {
		// TODO Auto-generated method stub
		System.out.println(searchField);
		System.out.println(searchFieldValue);
		System.out.println("name:"+searchField.equals("name"));
		System.out.println("id:"+searchField.equals("id"));
		System.out.println("or:"+(searchField.equals("id")||searchField.equals("name")));
		System.out.println(searchField.equals("id")||searchField.equals("name")&&(!searchField.equals("")));
		if(searchField==""&&searchFieldValue==""){
			printMethodNull(writer);
		}else if(searchField.equals("")){
			printMethodError1(writer);
			
		}else if(searchFieldValue.equals("")){
			PrintMethodEroor2(writer);
		}
		else if((searchField.equals("id")||searchField.equals("name"))&&(!searchField.equals(""))){
			
			
			if(searchField.equals("id")&&(!searchFieldValue.equals(""))){
				printMethodDefault(writer,searchField,searchFieldValue);
			}
			if(searchField.equals("name")&&(!searchFieldValue.equals(""))){
				printMethodName(writer,searchField,searchFieldValue);
			}	
		}
		else if(searchField.equals("all")&searchFieldValue.equals("all")){
			PrintMethodSuper(writer);
		}
		else if(searchField.equals("special")&searchFieldValue.equals("today")){
			PrintMethodSpecial(writer);
		}
		else{
			
			PrintMethodEroor3(writer);
		}//	
		
		
		
		

		
	}


	public void printMethodName(PrintWriter writer, String searchField, String searchFieldValue) {
		// TODO Auto-generated method stub
		writer.println("<query>");
		writer.println("<recordNumber>0</recordNumber>");
		writer.println("</query>");
	}


	public void PrintMethodSuper(PrintWriter writer) {
		// TODO Auto-generated method stub
		writer.println("<query>");
		writer.println("<recordNumber>999</recordNumber>");
		writer.println("</query>");
		
	}
	public void PrintMethodSpecial(PrintWriter writer) {
		// TODO Auto-generated method stub
		writer.println("<query>");
		writer.println("<recordNumber>999</recordNumber>");
		writer.println("</query>");
		
	}
	public void printMethodDefault(PrintWriter writer, String searchField, String searchFieldValue) {
		// TODO Auto-generated method stub
		writer.println("<query>");
		writer.println("<recordNumber>0</recordNumber>");
		writer.println("</query>");
	}


	private void PrintMethodEroor3(PrintWriter writer) {
		// TODO Auto-generated method stub
		writer.println("<query>");
		writer.println("<error>");
		writer.println("<errorCode>003</errorCode>");
		writer.println("<message>");
		writer.println("only Field=id currently permitted");
		writer.println("</message>");
		writer.println("</error>");
		writer.println("</query>");
		
	}


	

	public void PrintMethodEroor2(PrintWriter writer) {
		// TODO Auto-generated method stub
		writer.println("<query>");
		writer.println("<error>");
		writer.println("<errorCode>002</errorCode>");
		writer.println("<message>");
		writer.println("no value entered for value");
		writer.println("</message>");
		writer.println("</error>");
		writer.println("</query>");
		
	}


	public void printMethodError1(PrintWriter writer) {
		// TODO Auto-generated method stub
		writer.println("<query>");
		writer.println("<error>");
		writer.println("<errorCode>001</errorCode>");
		writer.println("<message>");
		writer.println("no value entered for field");
		writer.println("</message>");
		writer.println("</error>");
		writer.println("</query>");
		
	}


	public void printMethodNull(PrintWriter writer) {
		writer.println("<query>");
		writer.println("<error>");
		writer.println("<errorCode>000</errorCode>");
		writer.println("<message>");
		writer.println("no value entered");
		writer.println("</message>");
		writer.println("</error>");
		writer.println("</query>");
		// TODO Auto-generated method stub
		
	}
}
