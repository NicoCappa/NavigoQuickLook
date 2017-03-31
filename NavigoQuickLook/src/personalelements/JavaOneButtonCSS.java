package personalelements;

import javafx.scene.control.Button;

public class JavaOneButtonCSS  extends Button{

	//this is a method of creating your own UI element by using an existing UI element
	//In this case Button. We add a CSS class which can be found in the stylesheets package
	//and then reference it here
	public JavaOneButtonCSS(String text){
		
		super(text);
		//telling the button to get it's styling information from the 
		//javaone-button class
		getStyleClass().add("javaone-button");
		
	}
	
	//here we are encapsulating the actual stylesheet where the javaone-button class is
	//so that there is no need to call it everytime we make a new scene
	@Override
	public String getUserAgentStylesheet() {
		
		return JavaOneButtonCSS.class.getResource("/stylesheets/javaone-button.css").toExternalForm();
		
	}
	
	
}
