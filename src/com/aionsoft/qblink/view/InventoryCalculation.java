package com.aionsoft.qblink.view;

import java.util.ArrayList;

import com.aionsoft.qblink.helper.ReadFileByLine;
import com.aionsoft.qblink.helper.inventory.InventorySalesOrder;
import com.aionsoft.qblink.model.quickbooks.Item;
import com.odoolink.utility.LineParser;

public class InventoryCalculation {
	
	ArrayList<InventorySalesOrder> soList;
	ArrayList<Item> Inventory2016OdooList; //A
	ArrayList<Item> Inventory2017OdooList; //B
	ArrayList<Item> Inventory2017QuickbooksList; //C
	
	ArrayList<Item> Inventory2016OdooCalculated; //D
	ArrayList<Item> Inventory2016OdooMissing; //E
	
	ArrayList<String> so2016 = new ArrayList<>();
	ArrayList<String> so2017 = new ArrayList<>();
	/**
	 * C-B=D
	 * D-A=E
	 * 
	 * E=items need to add to sales order list
	 * @return
	 */
	
	public InventoryCalculation(){
		//Constructor
		System.out.println("Constructor");
		getSaleOrderList();
		sortInventoryList();
	}
	
	
	public ArrayList<Item> calculateInventory2016(){
		
		ReadFileByLine newFile = new ReadFileByLine("D:\\Users\\b8456\\Documents\\WorkSpace\\OdooLink\\inventory.csv");
		ArrayList<Item> items = newFile.getItemData();
		
		for(int i=0;i<items.size();i++){
		System.out.println(i+","+items.get(i));

		}
		
		getInventory2016OdooList();
		return items;
	}
	
	
	public ArrayList<InventorySalesOrder> getSaleOrderList(){
		ReadFileByLine newFile = new ReadFileByLine("C:\\OdooLink\\Inventory\\inventory_slot.txt");
		soList = newFile.getInventoryData();
		return soList;
	}
//	public ArrayList<Item> 
	
	
	public void sortInventoryList(){
		

		
		for(int i=0;i<soList.size();i++){
			System.out.println(soList.get(i));
			InventorySalesOrder temp = soList.get(i);
			String slot = temp.getLocation();
			String origin = temp.getOriginal_id();
			String dupl = temp.getDuplicate_id();
			if(!slot.equals("Slot")){
				so2017.add(dupl);
			}
			
			if((slot.equals("A")||slot.equals("B")||
					slot.equals("C")||slot.equals("G")||
					slot.equals("H")||slot.equals("E")||
					slot.equals("F"))){
				so2016.add(origin);
			}
		}
		System.out.println(so2017);
		System.out.println("------------");
		System.out.println(so2016);
		
	}
	
	public ArrayList<Item> getItemsFromSalesOrder(){
		ArrayList<Item> items = new ArrayList<>();
		
		
		
		
		return items;
		
	}
	public ArrayList<Item> getInventory2016OdooList() {

		//TODO
		


			
		
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
