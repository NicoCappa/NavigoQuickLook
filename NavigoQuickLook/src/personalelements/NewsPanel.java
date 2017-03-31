package personalelements;



import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class NewsPanel extends Region {
	//Fields of the NewsPanel
	private final Label descriptionLabel;
	private final Hyperlink link;
	private final String URLString;
	//private StringProperty headlineTextProperty = new SimpleStringProperty(this, "text");
	
	//Second Constructor
	public NewsPanel(String headLine, String desc, String URL, double width){	
		
		//tells the NewsPanel what CSS class to get its looks from
		getStyleClass().add("NewsPanel");
		
	//// Sizing the Region //// 
		
		this.setMinWidth(width);	
		this.setMinHeight(100);		
		
		
	//// headline ////
		link = new Hyperlink();
		link.setText(headLine);
		link.getStyleClass().add("Headline");
		link.setWrapText(true);
		link.maxWidthProperty().bind(this.widthProperty());
		link.setTextAlignment(TextAlignment.CENTER);
		
	
		//positioning
		link.layoutXProperty().bind(this.widthProperty().divide(2).subtract(link.widthProperty().divide(2)));
		link.layoutYProperty().bind(this.heightProperty().multiply(.05));

		
	//// Description //// 
		descriptionLabel = new Label(desc);
		descriptionLabel.setTextAlignment(TextAlignment.CENTER);
		descriptionLabel.prefWidth(200);
		descriptionLabel.maxHeight(20);
		descriptionLabel.setWrapText(true);
		descriptionLabel.maxWidthProperty().bind(this.widthProperty());
		
		//Positioning
		descriptionLabel.layoutYProperty().bind(link.layoutYProperty().add(40));
		
		
		
		
		descriptionLabel.getStyleClass().add("Description");


	//// Link ////
		URLString = URL;
		
		
	//Adding Components to the region
		getChildren().add(descriptionLabel);
		getChildren().add(link);
		
		
		
	}
	
	
	
//// Method Overrides ////
	
	@Override
	public String getUserAgentStylesheet() {
		return NewsPanel.class.getResource("/stylesheets/newspanel.css").toExternalForm();
		
	}
	
	
//// Methods //// 
	
	public void alignChildren(){
		//Height and width of the entire NewsPanel
		double w = this.widthProperty().get(); 
		double h = getHeight();
		
		//descriptionLabel.layoutYProperty().bind(link.layoutYProperty().add(40));
//		System.out.println(w);
//		System.out.println(h);
//		System.out.println("**************");
		
		//Relocate Link to x position width divided by two - the width of the 
		//link itself divided by wo (so that it is centered) same for the height.
		link.relocate(w/2 - link.getWidth()/2, h/5 - link.getHeight()/2);
		//Relocates the description label at x <same as above> and y 30 pixels down from the link node
		descriptionLabel.relocate(w/2 - descriptionLabel.getWidth()/2, link.getLayoutY() + 20);

		//descriptionLabel.setWrappingWidth(300);
		
//		System.out.println("%%%%%%%%%%");
//		System.out.println(descriptionLabel.getWidth());
		
	}
	
	
	
}

