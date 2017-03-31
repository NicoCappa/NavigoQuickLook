package controllers;
import application.Main;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Pane mainPane;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton register;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField userName;

    @FXML
    void runLogin(MouseEvent event) throws IOException{
    	
    	String username_actual = userName.getText();
    	    	String password_actual = password.getText();
    	    	
    	    	if( username_actual.equals("NicoCappa") && password_actual.equals("APcsa")){
    	    		
    	    		System.out.println("WELCOME NICO");
    	    		
    	    		Stage mainWindow = new Stage();	
    				Parent loginRoot = FXMLLoader.load(getClass().getResource("/views/MainAppView.fxml"));
    				Scene loginScreen = new Scene(loginRoot);
    				mainWindow.setScene(loginScreen);
    				mainWindow.show();
    	    		
    	    		
    	    		((Node)(event.getSource())).getScene().getWindow().hide();
    	    		
    	    	} else {
    	    		
    	    		System.out.println("Wrong Credentials");    		
    	    	}
    

    }

    @FXML
    void runRegister(MouseEvent event) {

    }

    public void hideCurrent(){
    	
    	Scene current = userName.getScene();
    	
    }
    
    
    
    
    
    //stage setter?
    private Stage myStage;
    public void setStage(Stage stage) {
         myStage = stage;
    }
	
    

    
    
    
    
    
    
    
    
    
    
    
//////////////////////////// Examples ////////////////////////////     
    
    
   
 //Example method of how to change stage to MainAppView and hides the log in window
//  public void startMainAppView(ActionEvent event) throws IOException {
//			
//		
//			Stage mainWindow = new Stage();	
//			Parent loginRoot = FXMLLoader.load(getClass().getResource("/views/MainAppView.fxml"));
//			Scene loginScreen = new Scene(loginRoot);
//			mainWindow.setScene(loginScreen);
//			mainWindow.show();
//			
//			
//			//hides current window
//			((Node)(event.getSource())).getScene().getWindow().hide();
//			
//	
//	}

    
    
    
    
    
}
