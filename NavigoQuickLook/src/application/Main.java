package application;
	
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import controllers.LoginController;


public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//vars
	Stage window;
	
	
	//This launches the login
	public void startLogin(){
		try{
			window = new Stage();
			
			Parent loginRoot = FXMLLoader.load(getClass().getResource("/views/LoginScreen.fxml"));
			Scene loginScreen = new Scene(loginRoot);
			window.setScene(loginScreen);
			window.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	//This is what runs on startup
	@Override
	public void start(Stage primaryStage) {
		try {
			
			window = primaryStage;
			
			window.initStyle(StageStyle.UNDECORATED);
			
			Parent root = FXMLLoader.load(getClass().getResource("/views/SplashScreen.fxml"));
			Scene scene = new Scene(root);			
			window.setScene(scene);
			window.show();
			
			
			//fade in
			FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), root);
			fadeIn.setFromValue(0);
			fadeIn.setToValue(1);
			fadeIn.setCycleCount(1);
			fadeIn.play();
			
			//fade out
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), root);
			fadeOut.setFromValue(1);
			fadeOut.setToValue(0);
			fadeOut.setCycleCount(1);		
			//when fade in ends 
			fadeIn.setOnFinished((e) -> {
				fadeOut.play();
			});
			
			fadeOut.setOnFinished((e) -> {
				window.hide();
				startLogin();
			});
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//change stages 
	public static void changeStage(Stage currentStage, Scene thisOne){
	
	
	}
	
	//change scenes within stage
	public static void changeScene(Stage currentStage, Scene thisOne){		
		Stage window = currentStage;
		window.setScene(thisOne);
			
	}
	

}
