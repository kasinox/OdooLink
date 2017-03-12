package com.alltekusa.qbLink.Viewer.Console.GUI;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

	public static void display(String title, String message){
		final Stage window = new Stage();
		
		//block user interaction 
		window.initModality(Modality.APPLICATION_MODAL);
		window.getIcons().add(new Image("https://cdn3.iconfinder.com/data/icons/illustricon-tech/512/development.desktop-512.png"));
		window.setTitle(title);
		window.setWidth(300);
		window.setHeight(250);
		
		Label alertMessage = new Label();
		alertMessage.setText(message);
		
		Button close = new Button("Close");

		close.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				window.close();
			}
			
		});
		
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(alertMessage,close);
		layout.setAlignment(Pos.CENTER);
		
		
		Scene newScene = new Scene(layout);
		window.setScene(newScene);
		window.showAndWait();
		
		
	}
}
