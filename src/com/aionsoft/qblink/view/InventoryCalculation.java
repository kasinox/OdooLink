package com.aionsoft.qblink.view;

import java.util.ArrayList;

import com.aionsoft.qblink.helper.ReadFileByLine;
import com.aionsoft.qblink.model.quickbooks.Item;
import com.odoolink.utility.LineParser;

public class InventoryCalculation {
	
	ArrayList<Item> Inventory2016OdooList; //A
	ArrayList<Item> Inventory2017OdooList; //B
	ArrayList<Item> Inventory2017QuickbooksList; //C
	
	ArrayList<Item> Inventory2016OdooCalculated; //D
	ArrayList<Item> Inventory2016OdooMissing; //E
	
	/**
	 * C-B=D
	 * D-A=E
	 * 
	 * E=items need to add to sales order list
	 * @return
	 */
	
	
	public ArrayList<Item> calculateInventory2016(){
		
		ReadFileByLine newFile = new ReadFileByLine("C:\\Users\\b8456\\Documents\\Work Space\\OdooLink\\inventory.csv");
//		ArrayList<String> test = newFile.getData();
//		
//		for(int i=0;i<test.size();i++){
//			System.out.println(i+","+test.get(i));
//
//		}
		ArrayList<Item> items = newFile.getItemData();
		
		for(int i=0;i<items.size();i++){
		System.out.println(i+","+items.get(i));

		}
		return items;
	}
	
	
	
	
	public ArrayList<Item> getInventory2016OdooList() {
		return Inventory2016OdooList;
	}
	public ArrayList<Item> getInventory2017OdooList() {
		return Inventory2017OdooList;
	}
	public ArrayList<Item> getInventory2017QuickbooksList() {
		return Inventory2017QuickbooksList;
	}
	public void setInventory2016OdooList(ArrayList<Item> inventory2016OdooList) {
		Inventory2016OdooList = inventory2016OdooList;
	}
	public void setInventory2017OdooList(ArrayList<Item> inventory2017OdooList) {
		Inventory2017OdooList = inventory2017OdooList;
	}
	public void setInventory2017QuickbooksList(ArrayList<Item> inventory2017QuickbooksList) {
		Inventory2017QuickbooksList = inventory2017QuickbooksList;
	}
	
	
	
	

}
