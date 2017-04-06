package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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


public class MainAppScene extends Application{

	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	static Stage window;
	@Override
	public void start(Stage primaryStage) {
		try {
						
			window = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/views/MainAppView.fxml"));
			Scene scene = new Scene(root);
			
			
			window.setScene(scene);
			window.setTitle("Navigo QuickLook");
			
			window.show();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
//Getters and Setters
	public static Stage getStage(){		
		return window; 
		
	}
	
	
}
