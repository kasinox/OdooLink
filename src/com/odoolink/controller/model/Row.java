package com.odoolink.controller.model;

import java.util.ArrayList;

public class Row {
	
	ArrayList<Cell> cells = new ArrayList<>();
	
	public Row(){
		
	}

	public void addCell(Cell cell){
		cells.add(cell);
	}
	public ArrayList<Cell> getCells(){
		return cells;
	}
	public String toString(){
		
		String content="";
		for(int i=0;i<cells.size();i++){
			content = content + cells.get(i).getValue();
		}
		return content;
	}
	

}
