package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import personalobjects.NewsObject;
import personalobjects.StockInfo;
import datagather.NewsGatherer;
import datagather.StockGatherer;
import personalelements.NewsPanel;
import personalelements.StocksPanel;

import java.util.ArrayList;
import personalobjects.NewsObject;

public class MainAppViewController implements Initializable {

   
	//Data Gatherers 
	NewsGatherer nGath = new NewsGatherer();
	StockGatherer sGath = new StockGatherer();
	
	
	
	
	
//// News Pane ////    
	@FXML
	private AnchorPane newsAnchor;

    @FXML
    private Pane newsHeader;

    @FXML
    private Label newsTitle;

    @FXML
    private Button searchNewsButton;

    @FXML
    private TextField searchNewsField;
	@FXML
	private ScrollPane newsScroll;
	
	@FXML
    private VBox newsPane;	
    
	
	@FXML
    //Sets all the news 
    private void setNews() throws IOException{
    	
		
		newsPane.setMaxWidth(newsScroll.getWidth());
		
    	//Vars
    	ArrayList<NewsObject> news_list = nGath.gatherNewsData();
    	NewsPanel newsPanel;
    	
    	//Adds the NewsPanel to the newsPane
    	for(int i = 0; i < news_list.size(); i++){
    		
//    		System.out.println(newsPane.getWidth());
//    		System.out.println("&&&&&&&&");
    		
    		newsPanel = new NewsPanel(news_list.get(i).getHeadline(), news_list.get(i).getDesc(), news_list.get(i).getUrl(), newsPane.getWidth());
    		//System.out.println(news_list.get(i).getHeadline());
    		
    	
    	
    	
    		
    		newsPane.getChildren().add(newsPanel);
    		
    		
    	}
    	
    	Platform.runLater(() -> {
    		newsScroll.setMaxWidth(newsScroll.getScene().getWidth()/2);
    		//System.out.println("**********");
    		//System.out.println(newsScroll.getScene().getWidth());
    	});
    	
    }

	
	
//// Stocks Panel ////
	@FXML
    private ScrollPane stockScroll;
	
    @FXML
    private VBox stockBox;
    
    @FXML
    private AnchorPane holdingsAnchor;
    
    @FXML
    private Pane holdingsHeader;

    @FXML
    private Label holdingsTitle;

    @FXML
    private Button addStockButton;

    @FXML
    private TextField addStockField;
    
    @FXML
    private Pane dividersPane;

    @FXML
    private Label symbolLabe;

    @FXML
    private Label priceLabe;

    @FXML
    private Label changeLabe;

    @FXML
    private Label changePerLabe;

    @FXML
    private Label volumeLabe;

    @FXML
    private Label rangeLabe;


	
	//Initialize stocks panel with SPY, DJIA, WYNN, and NVDA. 
	private void setStocks() throws IOException{
		
		stockBox.maxWidthProperty().bind(stockScroll.widthProperty());
		String[] initialEquities = new String[]{"MSFT", "GOOG", "WYNN", "NVDA"};
		StocksPanel stocksPanel;
		
		for(String s : initialEquities){
			StockInfo stock  = sGath.gatherStockData(s);
			StocksPanel sPanel = new StocksPanel(stock.getSymbol(), stock.getPrice(), stock.getPercentChange(), stock.getCompany(), stockBox.widthProperty().get());
			
			stockBox.getChildren().add(sPanel);
			
		}
		
		
		
	}
	
	
//// Chart Section //// 
    @FXML
    private AnchorPane chartPane;

    @FXML
    private TextField chartInput;

    @FXML
    private Button lookUpButton;

    @FXML
    private ScrollPane chartImagePane;
    
	
	
//// Initialize //// 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		newsScroll.setContent(newsPane);
		stockScroll.setContent(stockBox);

		lookUpButton.setOnMouseClicked(e -> {
			
			String pMe = chartInput.getText();
			
			//Image chart = new Image("http://http://www.finviz.com/chart.ashx?t=" + pMe + "&ty=c&ta=1&p=d&s=l"); 
			Image chart = new Image("http://www.finviz.com/chart.ashx?t="+pMe+"&ty=c&ta=1&p=d&s=l");
			ImageView chartView = new ImageView(chart);
			
			chartImagePane.setContent(chartView);
			System.out.println("Done I think?");
			
			
			
			
		});
		
		
		Platform.runLater(() -> {
			try {
				setNews();
				setStocks();
				stockBox.getChildren().forEach((StocksPanel) -> {
					
					((personalelements.StocksPanel) StocksPanel).layoutElements();
					
				});
				
				
//				StocksPanel t = (StocksPanel) stockBox.getChildren().get(1);
//				t.testMethod();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			});
		
	}


    

}
