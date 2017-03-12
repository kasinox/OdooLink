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

public class ConfirmBox {

	static boolean answer;
	public static boolean display(String title, String message){
		final Stage window = new Stage();
		
		//block user interaction 
		window.initModality(Modality.APPLICATION_MODAL);
		window.getIcons().add(new Image("https://cdn3.iconfinder.com/data/icons/illustricon-tech/512/development.desktop-512.png"));
		window.setTitle(title);
		window.setWidth(300);
		window.setHeight(200);
		
		
		Label alertMessage = new Label();
		alertMessage.setText(message);
		
		Button yesButton = new Button("YES");
		Button noButton =new Button("NO");
		
		yesButton.setOnAction(new EventHandler<ActionEvent>(){

			
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				answer=true;
				System.out.println("CONFIRM WINDOW - yes");
				window.close();
			}
			
		});
		noButton.setOnAction(new EventHandler<ActionEvent>(){

			
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				answer=false;
				System.out.println("CONFIRM WINDOW - no");
				window.close();
			}
			
		});
		VBox layout = new VBox(10);
		layout.getChildren().addAll(alertMessage,yesButton,noButton);
		layout.setAlignment(Pos.CENTER);
		
		
		Scene newScene = new Scene(layout);
		window.setScene(newScene);
		window.showAndWait();
		
		return answer;
	}
	
	public static void main(String args[]){
		ConfirmBox confirm = new ConfirmBox();
		confirm.display("hell","what is goign on?");
		
	}
}
