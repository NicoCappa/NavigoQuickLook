package personalelements;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import personalobjects.StockInfo;

public class StocksPanel extends Region {

	//Fields 
	Label symbol;
	Label change;
	Label changePer;
	Label company;
	Label price;
	double changeNumb; 
	String exchange;
	String previousClose;
	
//// Constructors //// 
	public StocksPanel(StockInfo stockInf, double width){
		
				
	//Giving CSS to the Region
		this.getStyleClass().add("stockspanel");
		
	//Setting property values (Non-viewable)
		exchange = stockInf.getExchange();
		previousClose = stockInf.getPrevClose();
		
	//Sizing the StocksPanel
		this.setMinWidth(width);
		this.setMinHeight(50);
		
	//Symbol Label
		symbol = new Label(stockInf.getSymbol());
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
		price = new Label(stockInf.getPrice());
		price.getStyleClass().add("price");
		
		//positioning
		//price.layoutXProperty().bind(symbol.layoutXProperty().add(this.widthProperty().subtract(150)));
		price.layoutYProperty().bind(symbol.layoutYProperty());
		
	//Change Label
		change = new Label(stockInf.getDollarChange());	
		change.getStyleClass().add("change");
		
		//positioning
		//change.layoutXProperty().bind(price.layoutXProperty());
		change.layoutYProperty().bind(symbol.layoutYProperty());
		change.setWrapText(true);
		change.maxWidthProperty().bind(price.widthProperty().add(150));
		
		//Methods
		

	//ChangePer Label
		changePer = new Label(stockInf.getPercentChange() + "%");
		changePer.getStyleClass().add("change");
		
		//positioning
		changePer.layoutYProperty().bind(symbol.layoutYProperty());
		changePer.setWrapText(true);
		changePer.maxWidthProperty().bind(price.widthProperty().add(150));
		
		colorizeChange();
	//Adding components to the region
		this.getChildren().add(symbol);
		this.getChildren().add(price);
		this.getChildren().add(change);
		this.getChildren().add(changePer);
		this.getChildren().add(company);
		
		
		
		
	}

//// Method Overrides //// 
	
	@Override
	public String getUserAgentStylesheet() {
		return StocksPanel.class.getResource("/stylesheets/stockspanel.css").toExternalForm();
				
		
	}
	
//// Methods //// 
	
	//Changes color of changeLabel based on positive or negative change
	private void colorizeChange(){
		
		String changeString = this.change.getText();
		
		int index = changeString.indexOf("-");
		 
		
		if (index > -1){
			this.change.setStyle("-fx-text-fill: red;");
			this.changePer.setStyle("-fx-text-fill: red;");
			
		}
		else {
			this.change.setStyle("-fx-text-fill: green;");
			this.changePer.setStyle("-fx-text-fill: green;");
		}	
	}
	
	//Layouts the elements of the control
	public void layoutElements(){
		
		Parent one = this.getParent().getParent();
		Parent two = one.getParent().getParent().getParent();
		
		Node symbolLabe = (two.lookup("#symbolLabe"));
		Node priceLabe =  (two.lookup("#priceLabe"));
		Node changeLabe = (two.lookup("#changeLabe"));
		Node changePerLabe = (two.lookup("#changePerLabe"));
		
//		System.out.println("****/////****");
//		System.out.println(one);
//		System.out.println(symbolLabe);
//		System.out.println(changeLabe.layoutXProperty());
//		System.out.println("****/////****");
//		
		
	//Symbol
		symbol.layoutXProperty().bind(symbolLabe.layoutXProperty());
		
	//Company
		
		
	//Price
		price.layoutXProperty().bind(priceLabe.layoutXProperty());
		
		
	//Change
		change.layoutXProperty().bind(changeLabe.layoutXProperty());
		
	//ChangePer 
		changePer.layoutXProperty().bind(changePerLabe.layoutXProperty());
		
	}
	
//// Getters ////
	
	public String getSymbol(){
		return this.symbol.getText();		
	}
	
	//fix this
	public String getExchange(){
		return this.exchange;		
	}
	
	public String getPrice(){
		String priceWithSign = this.price.getText();
		//removes dollar sign
		String price = priceWithSign.replace("$", "");
		return price;
	}
	
	public String getPrevClose(){
		return this.previousClose;
	}
//// Setters //// 
	//sets the text in the price label
	public void setPrice(String price){
		this.price.setText(price);
	}
	
	//sets both the dollar change label and percentage Change Label
	public void setChange(String dolChange, String perChange){
		this.change.setText(dolChange);
		this.changePer.setText(perChange);
		
	}
	
	
	
	
}
