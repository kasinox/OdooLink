package com.alltekusa.qbLink.Viewer.Console.GUI;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application implements EventHandler<ActionEvent>{

	Stage window;
	Button update;
	Button confirm;
	Button exit;
	ArrayList<String> log = new ArrayList<>();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	
	public static void main(String[] args){
		launch(args);
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
		// TODO Auto-generated method stub
		window=primaryStage;
		window.setTitle("Quickbook-Updater");
		window.setScene(new Scene(root));
		window.getIcons().add(new Image("https://cdn3.iconfinder.com/data/icons/illustricon-tech/512/development.desktop-512.png"));
		window.setOnCloseRequest(new EventHandler<WindowEvent>(){
			@Override
			public void handle(WindowEvent e) {
				// TODO Auto-generated method stub
				e.consume();
				closeProgram();
			}	
		});
		window.show();
	}

	
	
	public void closeProgram() {
		
		// TODO Auto-generated method stub
		Boolean answer = ConfirmBox.display("", "DO YOU WANT TO CLOSE THE PROGRAM?");
		if(answer) {
			window.close();
		}
	}

@Override
public void handle(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}
	
	
}
