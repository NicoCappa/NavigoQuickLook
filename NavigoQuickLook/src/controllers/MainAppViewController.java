package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import personalobjects.NewsObject;
import personalobjects.StockInfo;
import datagather.NewsGatherer;
import datagather.StockGatherer;
import personalelements.NewsPanel;
import personalelements.StocksPanel;
import java.util.ArrayList;
import personalobjects.NewsObject;
import application.MainAppScene;

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
    		newsPanel = new NewsPanel(news_list.get(i), newsPane.getWidth());
    		newsPane.getChildren().add(newsPanel);
    		
    		
    	}
    	
    	Platform.runLater(() -> {
    		newsScroll.setMaxWidth(newsScroll.getScene().getWidth()/2);

    	});
    	
    }
	
	//Adds new NewsPanel Objects with updated news into the newsPane vbox
	private void updateNews() throws IOException{
		
		
		Node topNews = newsPane.getChildren().get(0);
		String topNewsHeadline = ((NewsPanel) topNews).getHeadlineText();
		System.out.println(topNewsHeadline);		
		int amountAdded = 0;

		ArrayList<NewsObject> updatedNewsList = nGath.updateNewsData(topNewsHeadline);
		
		 for (NewsObject element : updatedNewsList){
			 
			 String headline = element.getHeadline();
			 
			 if (!headline.equals("End")){				 
				 NewsPanel addMe = new NewsPanel(element, newsPane.getWidth());
				 newsPane.getChildren().add(0, addMe);
				 amountAdded += 1;
			 
			 } else {
				 
				 System.out.println(amountAdded);
				 System.out.println("Nothing left to add");
				 deleteNews();
				 break;
			 }
			 
		 }
		 
	}
	
	//Removes news so news list doesn't get too big, to be run inside of updateNews
	private void deleteNews(){
		
		int amountOfNews = newsPane.getChildren().size();
		if(amountOfNews > 50){	
			newsPane.getChildren().remove(amountOfNews-1, 29);
			System.out.println("20 items deleted");
			
			
		} else {			
			System.out.println("Nothing to delete");
		}
		
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
			StocksPanel sPanel = new StocksPanel(stock, stockBox.widthProperty().get());
			
			stockBox.getChildren().add(sPanel);
			
		}
		
		
		
	}
	
	//Returns an arraylist of the current stocksPanels in the stockBox
	private ArrayList<StocksPanel> getStocksPanels() throws IOException{
		
		ObservableList<Node> holdings = stockBox.getChildren();
		ArrayList<StocksPanel> passMe = new ArrayList<StocksPanel>();
		
		//places all nodes fro the vbox into the stockspanel arraylist
		for (Node el : holdings){
			passMe.add(((StocksPanel) el));			
		}
		//ArrayList<StockInfo> updatedInfo = sGath.updateStockInfo(passMe);
		
		
		return passMe;
				
		
	}
	
	
	
	
	//Add stock on button click
	
	
	
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
		Image init = new Image("/images/spinny.gif");
		ImageView initView = new ImageView(init);
		chartImagePane.setContent(initView);
		
		
	//EventHandlers	
		//lookUpButton for charts
		lookUpButton.setOnMouseClicked(e -> {
			
			String pMe = chartInput.getText();
			//Image chart = new Image("http://http://www.finviz.com/chart.ashx?t=" + pMe + "&ty=c&ta=1&p=d&s=l"); 
			Image chart = new Image("http://www.finviz.com/chart.ashx?t="+pMe+"&ty=c&ta=1&p=d&s=l");
			ImageView chartView = new ImageView(chart);
			
			chartImagePane.setContent(chartView);
			System.out.println("Done I think?");
		});
		
		
		//addStocksButton
		addStockButton.setOnMouseClicked(e -> {
			System.out.print("IN HERE HELP");
			new Thread(new Runnable() {
				
				
				@Override
				public void run() {																	
						System.out.print("IN HERE HELP2");
						String symb = addStockField.getText().toUpperCase();
						
						try {
							StockInfo inf = sGath.gatherStockData(symb);
							StocksPanel newStock = new StocksPanel(inf, stockBox.widthProperty().get());
							
							Platform.runLater(() -> {
								stockBox.getChildren().add(newStock);
								newStock.layoutElements();								
								addStockField.setText("");
							});			
																					
						} catch (IOException e1) {									
							System.out.println(e1);
							System.out.print("IN HERE HELP3");
						}											
				}		
			}).start();
		
		});

		
		//addStocksOnEnter
		addStockField.setOnKeyPressed(new EventHandler<KeyEvent>() {			
			@Override
			public void handle(KeyEvent keyEv) {
				//Making this event be handled inside another thread								
				if (keyEv.getCode().equals(KeyCode.ENTER)){
					new Thread(new Runnable() {						
						@Override
						public void run() {							
							String symb = addStockField.getText().toUpperCase();
							
							try {
								StockInfo inf = sGath.gatherStockData(symb);
								StocksPanel newStock = new StocksPanel(inf, stockBox.widthProperty().get());
								
								Platform.runLater(() -> {
									stockBox.getChildren().add(newStock);
									newStock.layoutElements();								
									addStockField.setText("");
								});			
																						
							} catch (IOException e1) {									
								System.out.println(e1);
								
							}									
						}
					
					}).start();									
				}																	
			}
		});
		
		
		
		
	//Update Things		
		//Update News
		Timer newsTimer = new Timer();
		newsTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				Platform.runLater(() -> {
					try {
						updateNews();
					} catch (IOException e) {
						e.printStackTrace();
					}					
				});
					
			}
			
		}, 300000, 300000 );
		
		
		//Update Stocks
		Timer stocksTimer = new Timer();
		stocksTimer.schedule(new TimerTask() {

			@Override
			public void run()  {
			
				try{
					ArrayList<StocksPanel> passMe = getStocksPanels();
					ArrayList<StockInfo> updatedInfo = sGath.updateStockInfo(passMe);
					
					
					Platform.runLater(() -> {
						//for loop that adds the price to all the holdings
						for(int count = 0; count < passMe.size(); count++){		
							StocksPanel panel = passMe.get(count);
							
//							System.out.println(updatedInfo.get(count).getPercentChange());
//							System.out.println(updatedInfo.get(count).getDollarChange());
							
							panel.setPrice(updatedInfo.get(count).getPrice());
							panel.setChange(updatedInfo.get(count).getDollarChange(), updatedInfo.get(count).getPercentChange());
							
						}
					});
				} catch (Exception e){
					System.out.println(e);
				}
				
			}
			
		
		}, 10000, 10000);
		
		
		
		
		
		
		
	//UNSURE, JUST LEAVE IT HERE
		Platform.runLater(() -> {
			try {
				setNews();
				setStocks();
				stockBox.getChildren().forEach((StocksPanel) -> {
					
					((personalelements.StocksPanel) StocksPanel).layoutElements();
					
				});
				
				
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			});
		
		//When the user presses the X button (window close) this will happen
		Stage thisStage = MainAppScene.getStage();
		
		thisStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {              
	        	  newsTimer.cancel();
	        	  stocksTimer.cancel();
	        	  System.out.println("Stage is closing");
	          }
	    });
		
		
		
		
		
	}


    

}
