package com.alltekusa.qbLink.Viewer.Console.cmd;

import java.util.ArrayList;

//import com.alltekusa.qbLink.Quickbooks.Model.Inventory;
import com.alltekusa.qbLink.Quickbooks.Model.Item;

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
		
		
		
		
		
		return Inventory2016OdooCalculated;
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
