package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import personalelements.JavaOneButtonCSS;
import personalelements.JavaOneButtonRegion;
import personalelements.NewsPanel;; 


public class TestScene extends Application{

	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Button normalButton = new Button("Dissapointing Normal Button");
			JavaOneButtonCSS bestButton = new JavaOneButtonCSS("This is javaone");
			JavaOneButtonRegion regionButton = new JavaOneButtonRegion("This is region");
			//NewsPanel news = new NewsPanel("This is the headline words words words words words words words words ", "www.reuters.com", "This is the description");
			
			

			Stage window = primaryStage;
			AnchorPane root = new AnchorPane();
			root.setStyle("-fx-background-color: darkgrey;");
			
			
			ScrollPane scroll = new ScrollPane();
			scroll.setPrefWidth(400);
			scroll.setStyle("-fx-background-color: green;");
			
			
			VBox vbox = new VBox();
			vbox.setMaxWidth(600);
			vbox.setPrefWidth(300);
			vbox.setStyle("-fx-background-color: blue;");
			
			
			root.setPrefHeight(600);
			root.setMaxWidth(600);
			//root.getChildren().add(normalButton);
			//root.getChildren().add(bestButton);
			//root.getChildren().add(regionButton);
			
			scroll.setContent(vbox);
			root.getChildren().add(scroll);
			root.getChildren().add(vbox);
			
			Button addMore = new Button("Add More");
			root.getChildren().add(addMore);
			addMore.relocate(500, 200);
			addMore.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {

	            	NewsPanel newOne = new NewsPanel("Word", "www.reuters.com", "This is the description", vbox.getWidth());
	            	vbox.getChildren().add(newOne);
	            	newOne.alignChildren();
	            	
	            }
	        });
			
			Button moreWidth = new Button("width");
			root.getChildren().add(moreWidth);
			moreWidth.relocate(550, 300);
			moreWidth.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	vbox.setMinWidth(vbox.getWidth() + 20);
	            	
	            }
	        });
			
			Scene scene = new Scene(root,600,600);
			window.setScene(scene);
			window.show();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
