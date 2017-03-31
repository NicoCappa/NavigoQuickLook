package personalelements;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class StocksPanel extends Region {

	//Fields 
	Label symbol;
	Label change;
	Label company;
	Label price;
	double changeNumb; 
	
//// Constructors //// 
	public StocksPanel(String symb, String pri, String chan, String comp, double width){
		
	//Giving CSS to the Region
		this.getStyleClass().add("stockspanel");
		
	//Sizing the StocksPanel
		this.setMinWidth(width);
		this.setMinHeight(50);
		
		
	//Symbol Label
		symbol = new Label(symb);
		symbol.getStyleClass().add("symbol");
		
		//positioning
		//symbol.layoutXProperty().bind(this.widthProperty().divide(14));
		symbol.layoutYProperty().bind(this.heightProperty().divide(2).subtract(symbol.widthProperty().divide(2)));
		
	//Company Label
		
		//add below back in to re add company name
		//company = new Label(comp);
		company = new Label();
		company.getStyleClass().add("company");
				
		//positioning
		company.layoutXProperty().bind(symbol.layoutXProperty());
		company.layoutYProperty().bind(symbol.layoutYProperty().add(15));
		
		company.maxWidthProperty().bind(symbol.widthProperty());
		company.setWrapText(true);
		
	//Price Label
		price = new Label(pri);
		price.getStyleClass().add("price");
		
		//positioning
		//price.layoutXProperty().bind(symbol.layoutXProperty().add(this.widthProperty().subtract(150)));
		price.layoutYProperty().bind(symbol.layoutYProperty().subtract(5));
		
	//Change Label
		change = new Label(chan);	
		change.getStyleClass().add("change");
		
		//positioning
		//change.layoutXProperty().bind(price.layoutXProperty());
		change.layoutYProperty().bind(symbol.layoutYProperty());
		change.setWrapText(true);
		change.maxWidthProperty().bind(price.widthProperty().add(150));
		
		//Methods
		colorizeChange();
		
	//Adding components to the region
		this.getChildren().add(symbol);
		this.getChildren().add(price);
		this.getChildren().add(change);
		this.getChildren().add(company);
		
		
		
		
	}

//// Method Overrides //// 
	
	@Override
	public String getUserAgentStylesheet() {
		return StocksPanel.class.getResource("/stylesheets/stockspanel.css").toExternalForm();
				
		
	}
	
//// Methods //// 
	private void colorizeChange(){
		
		String changeString = this.change.getText();
		
		int index = changeString.indexOf("+");
		 
		System.out.println("*****");
		System.out.println(index);
		System.out.println("*****");
		
		if (index > -1){
			
			this.change.setStyle("-fx-text-fill: green;");
		}
		else {
			this.change.setStyle("-fx-text-fill: red;");
			
		}	
	}
	
	public void layoutElements(){
		
		Parent one = this.getParent().getParent();
		Parent two = one.getParent().getParent().getParent();
		
		Node symbolLabe = (two.lookup("#symbolLabe"));
		
		Node priceLabe =  (two.lookup("#priceLabe"));
		Node changeLabe = (two.lookup("#changeLabe"));
		
		System.out.println("****/////****");
		System.out.println(one);
		System.out.println(symbolLabe);
		System.out.println(symbolLabe.layoutXProperty());
		System.out.println("****/////****");
		
		
	//Symbol
		symbol.layoutXProperty().bind(symbolLabe.layoutXProperty());
		
	//Company
		
		
	//Price
		price.layoutXProperty().bind(priceLabe.layoutXProperty());
		
		
	//Change
		change.layoutXProperty().bind(changeLabe.layoutXProperty());
		
	}
}
