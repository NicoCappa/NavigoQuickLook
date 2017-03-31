package personalelements;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.PseudoClass;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

public class JavaOneButtonRegion extends Region {
	
	//This tells the button what CSS to use depending on if it is being pressed or not
	//You can see how this happens in the even handlers inside the constructor
	private static final PseudoClass ARMED_PSEUDOCLASS_STATE = PseudoClass.getPseudoClass("armed");

	//field for our Label to be instantiated inside the constructor
	private final Label textLabel;
	
	
	
	
	//Constructor for our button
	public JavaOneButtonRegion(String text){
	
	/////// Constructor Main ///////	
	
		
		//telling the button to get it's styling information from the 
		//javaone-button CSS class
		getStyleClass().add("javaone-regionbutton");
		
		//When set to true, it allows the user to use the tab button
		//to change the focus (i.e. jumping from one text field to another using tab)
		setFocusTraversable(true);
		
		
		//instantiates textLabel, making it a Lable Object
		textLabel = new Label();		
		
		//This binds the label to the textProperty, so that once the user sets it
		//it will be shown in the button
		textLabel.textProperty().bind(textProperty);
		
		//Adds the textLabel to the button's children list (will be included)
		getChildren().add(textLabel);
	
		setText(text);
	
		
		
		
		/////// Event Handlers /////// 
		
		//Handles when the mouse is pressed
		addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			
			//telling the program that the state of the button has changed
			pseudoClassStateChanged(ARMED_PSEUDOCLASS_STATE, true);
			
			//Tells the program to focus on the button
			requestFocus();
			
		});
		
		addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
			
			//when the mouse button is released, 
			pseudoClassStateChanged(ARMED_PSEUDOCLASS_STATE, false);
			
		});
	
	
	
	
	}
	
/////Text - these allow the user to change the text inside the button 
	
	//Creates the StringProperty
	private StringProperty textProperty = new SimpleStringProperty(this, "text");
	
	//Getter for StringProperty?
	public final StringProperty textProperty(){
		return textProperty;
	}
	
	//This gets the text inside the button
	public final String getText(){
		return textProperty.get();
	}
	
	//This sets the text inside the button
	public final void setText(String text){
		textProperty.set(text);
	}
	
	
	
/////Minimums width and height- These override the regions minimums for width and height
	//The reason we pass the height for calculating width and the width for calculating
	//height is because they are calculated based off each other. Think of text that looks like this:
	/*
	 * Hi
	 * This is example text
	 * 
	 * */
	//Java needs to know the height so that it can know wether or not to extend the width and
	//make the flow of text flat. Vice Versa for height. I don't fully understand this though
	//but whatever
	
	//Sets the button's minimum width to the width of the text label, so that
	//the text is never shrunk or dissapears
	@Override protected double computeMinWidth(double height){	
		return textLabel.minWidth(height);
	}
	
	//Does the same for the height as the above function Im unsure as to why the w
	@Override protected double computeMinHeight(double width){	
		return textLabel.minHeight(width);
	}
	
	
/////Maximum width and height- These override the regions maximums for height and width
	
	
	//Tells the button not to grow any bigger than the preferred witdth for it 
	@Override protected double computeMaxWidth(double height){	
		return computePrefWidth(height);
	}
	
	//Tells the button not to grow any bigger than the preferred height for it
	@Override protected double computeMaxHeight(double width){	
		return computePrefHeight(width);
	}
	
	
/////Preferred Width of Button- These functions return the preferred height and width for the button  
	
	@Override protected double computePrefWidth(double height){
		return textLabel.prefWidth(height) +
				//adjust padding on the left and right respectively 
				snappedLeftInset() + 
				snappedRightInset();
		
		//These inset things have to do with padding on the left and right side of the text label
		//To remember what this is refer to the Inset graphic located in the reference material
		//Pathway NavigoQuickLook/ReferenceMaterials/Images/Insets.png
		
	}
	
	@Override protected double computePrefHeight(double width){
		return textLabel.prefWidth(width) +
				//adjust padding on the top and bottom respectively
				snappedTopInset() + 
				snappedBottomInset();
	}
	
	
/////Layout of Button- Positions the textLabel in the center, essentially?
	
	@Override protected void layoutChildren(){
		//setting the x value (Horizontal Axis) to the snapped left inset
		final double x = snappedLeftInset();
		//setting the y value (Vertical Axis) to the snapped top inset 
		final double y = snappedTopInset();
		
		//Calculating the width of where the textLabel will be by getting the total width and subtracting it
		//the width of the snapped left and right Insets (padding)
		final double w = getWidth() - (snappedLeftInset() + snappedRightInset());
		
		//Does the same thing as the above line but for height 
		final double h = getHeight() - (snappedTopInset() + snappedBottomInset());
		
		textLabel.resizeRelocate(x, y, w, h);
		
		//Again, for a graphic of this refer to the layoutChildren graphic located in
		//Pathway: NavigoQuickLook/ReferenceMaterials/Images/layoutChildren.png
	}
	
	
/////Tells the control where to get it's CSS from
	@Override
	public String getUserAgentStylesheet() {
		return JavaOneButtonRegion.class.getResource("/stylesheets/javaone-regionbutton.css").toExternalForm();
		
	}
	
	
	
	
	
	
	
	
	
}
