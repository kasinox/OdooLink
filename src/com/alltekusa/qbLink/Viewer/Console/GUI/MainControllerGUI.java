package com.alltekusa.qbLink.Viewer.Console.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;

import org.w3c.dom.Document;

import com.alltekusa.qbLink.Controller.Database.ConnectionManager;
import com.alltekusa.qbLink.Controller.Quickbooks.SalesOrderManager;
import com.alltekusa.qbLink.Helper.*;
import com.alltekusa.qbLink.Viewer.Console.cmd.MainCommand;

public class MainControllerGUI {
	
	/**
	 * functions:
	 * search
	 * saleOrderProcess
	 * purchaseOrderProcess
	 */
	
	private static Connection conn;
	ObservableList<String> choiceProduct = 
			FXCollections.observableArrayList("Add Sale Order","Add Batch SO","UpdateSOStatus","Add SO Items","Add Vendor","Add Product","List POs");
	
	@FXML
	MenuItem closeButton;
	@FXML
	TextField searchTextField;
	@FXML
	Button connectButton; 
	@FXML
	Button disconnectButton;
	@FXML
	Label statusbar;
	@FXML
	Button updateButton;
	@FXML
	ChoiceBox<String> searchChoiceBox;
//	@FXML
//	ProgressBar progressBar;
	
	@FXML
	private void initialize(){
		searchChoiceBox.setValue("Add Sale Order");		
		searchChoiceBox.setItems(choiceProduct);	
	}
	
	public void closeProgram2(){
		System.out.println("closing Program");
		
	}
	public void search(ActionEvent event) throws SQLException{
		
		//if connection is not established or closed, prompt user to connect to quickbooks
		if(conn==null||conn.isClosed()){
			
			boolean connect =  new ConfirmBox().display("QUICKBOOK NOT CONNECTED",
					"YOU ARE NOT CONNECTED TO QUICKBOOKS FOR SOME REASON,"
					+ "\nDO YOU WANT TO CONNECT FIRST?");
			if (connect){
				QBConnection();
			}
		}else{
//			progressBar = new ProgressBar(0);
//	        progressBar.setProgress(0);
			String id = searchTextField.getText();
			String choice = searchChoiceBox.getValue();			
			id.trim();		
			
			//CHECKS IF SEARCH BOX IS EMPTY
			if(id==null||id.equals("")){
				new AlertBox().display("No Value Entered","Search box cannot be empty");
			}else{
				if(choice.equals("Add Sale Order")){
					boolean checkExist =new SalesOrderManager().existRefNumber(id);
					if(!checkExist){
						new MainCommand().addSalesOrder(id);;

					}else{
						new AlertBox().display("Warning","SO# "+id+" Already Exist");

					}
				}
				else if(choice.equals("Add Batch SO")){
					//some tasks
					long lStartTime = System.currentTimeMillis();
			
					new MainCommand().addBatchSalesOrder(id);;
					long lEndTime = System.currentTimeMillis();
					
					long difference = lEndTime - lStartTime;
			
					System.out.println("Elapsed seconds: " + difference/1000);
					System.out.println("Elapsed Minutes: " + difference/1000/60);

				}else if(choice.equals("Add SO Items")){
					
					
				}else if(choice.equals("List POs")){
					
				}else if(choice.equals("Add Vendor")){
					
				}

				searchTextField.clear();
			}
		}
		
		
	}

	


	public void QBConnection(){
		
		conn= ConnectionManager.getInstance().getConnection();	
		if(conn==null){
			statusbar.setText("CHECK QUICKBOOK LOGIN, ALWAYS LOGON TO QUICKBOOKS FIRST");	
		}else{
			connectButton.setDisable(true);
			disconnectButton.setDisable(false);
			statusbar.setText("Quickbook Connection Opened");
			

		}
			}
	public void QBDisconnect(){
		ConnectionManager.getInstance().close();
		connectButton.setDisable(false);
		disconnectButton.setDisable(true);
		statusbar.setText("Quickbook Connection Closed");	
	}

}
