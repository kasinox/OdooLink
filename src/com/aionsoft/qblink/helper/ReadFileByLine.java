package com.aionsoft.qblink.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.aionsoft.qblink.model.quickbooks.Item;

public class ReadFileByLine {

	String fileName;
	ArrayList<String> list = new ArrayList<String>();

	ArrayList<Item> itemList = new ArrayList<>();
	
	public ReadFileByLine(String fileName1) {
		this.fileName = fileName1;

//		ArrayList<String> rawData = getData();// processing Data
//		processData(rawData);//
		

	}

	public ArrayList<String> getData() {
		try {

			File file = new File(fileName);
			Scanner reader = new Scanner(file);
			reader.useDelimiter("\n|, ");

			while (reader.hasNext()) {
				String s = reader.next();
				
//				 System.out.println("string:"+s);
				s = s.trim();
				s = s.replaceAll(" ", "");
				
				
				// System.out.println("string2:" +s);
				list.add(s);

			}
			reader.close();

		} catch (FileNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
		}

		return list;

	}// end list read
	public ArrayList<Item> getItemData() {
		try {

			File file = new File(fileName);
			Scanner reader = new Scanner(file);
			reader.useDelimiter("\n|, ");

//			reader.useDelimiter(", ");
			while (reader.hasNext()) {
				String s = reader.next();
				
//				 System.out.println("string:"+s);
				s = s.trim();
				s = s.replaceAll(" ", "");
				if(s.contains("\"")){
					System.out.println(s);
//					System.exit(0);
				}else{
				}
				
				List<String> items = Arrays.asList(s.split("\\s*,\\s*"));
				String name = items.get(1);
				String price = items.get(0);
				System.out.println(s);
				System.out.println(name+","+price);
				double cost = Double.parseDouble(price);
				Item item = new Item(cost,name);
				
				
				// System.out.println("string2:" +s);
				itemList.add(item);

			}
			reader.close();

		} catch (FileNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
		}

		return itemList;

	}// end list read

	public void processData(ArrayList rawList) {
//		 System.out.println(rawList.size());
//		 int i=0;
//		 while(i<rawList.size()){
//		 String a = rawList.get(i).toString();
//		 String b = rawList.get(i+1).toString();
//		 String c = rawList.get(i+2).toString();
//		
//		// System.out.println(a+" "+b+" "+c);
//		
//		 Node A = new Node(a);
//		 Node B = new Node(b);
//		 String dist = c;
//		 int distance = Integer.parseInt(dist);
//		
//		 newGraph.addEdge(A, B, distance);
//		 i++;
//		 i++;
//		 i++;
//		 }

	}

	public static void main(String[] args) {
		ReadFileByLine newFile = new ReadFileByLine("inventory.csv");
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
	}

}



