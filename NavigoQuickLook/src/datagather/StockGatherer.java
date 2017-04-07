package datagather;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import personalelements.StocksPanel;
import personalobjects.StockInfo;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class StockGatherer {

//// Testing ////

//	public static void main(String[] args) throws IOException{
//		
//		
//		StockGatherer gather = new StockGatherer();
//		
//		StockInfo stock = gather.gatherStockData("NVDA");		
//		stock.getInfo();
//		
//	}
	
	
	
		
//// Methods //// 
	
	public StockInfo gatherStockData(String symbol) throws IOException{		
		Document doc = Jsoup.connect("http://www.msn.com/en-us/money/stockdetails?symbol=US:" + symbol).get();
		Document markWat = Jsoup.connect("http://www.marketwatch.com/investing/stock/" + symbol).get();
		String preCl = markWat.select(".table__cell.u-semi").text();
		
		String[] extTract = preCl.split(" ");
		String previousClose = extTract[0].replace("$", "");
		
		String exchange = doc.select(".live-quote-subtitle").text();
		exchange.trim();
		String[] exDet = exchange.split(":");
		System.out.println(exDet[0]);
		
		
		
		if (exDet[0].equals("NYSE")){
			
			Document document = Jsoup.connect("http://quotes.morningstar.com/stockq/c-header?&t=XNYS:"+symbol).get();
			
			String company = "test";//document.select(".r_title.h1").text();
			String price = "$" + document.select("#last-price-value").text();
			String change = document.select(".gr_text_bigprice").text();
			
			try{	
				
				String[] temp = change.split("[ |]");
				
				//Dollar
				String dollar = temp[1].trim();
				
				//Percent
				String percent = temp[4].trim();
				
				
				System.out.println("--------------");
				System.out.println(dollar);
				System.out.println(percent);
				System.out.println("--------------");
				
				StockInfo info = new StockInfo(symbol, price, dollar, percent, exDet[0], previousClose);
				return info;
			}
			
			catch(Exception e){
				
				
			
				
				StockInfo info = new StockInfo(symbol, company, price, change); 
				return info;
				
			}
			
		}
		
		else {
			
			Document document = Jsoup.connect("http://quotes.morningstar.com/stockq/c-header?&t=XNAS:"+symbol).get();
			
			String company =  "test";//document.select(".r_title.h1").text();
			String price = "$" + document.select("#last-price-value").text();
			String change = document.select(".gr_text_bigprice").text();
			
			try{	
				String[] temp = change.split("[ |]");
				
				//Dollar
				String dollar = temp[1].trim();
				
				//Percent
				String percent = temp[4].trim();
				
				
				StockInfo info = new StockInfo(symbol, price, dollar, percent, exDet[0], previousClose);
				return info;
			}
			
			catch(Exception e){
				
				System.out.println(e);
				
				StockInfo info = new StockInfo(symbol, company, price, change); 
				return info;
			
			}
				
		}
	
	}
	
	//Returns updated stock info 
	public ArrayList<StockInfo> updateStockInfo(ArrayList<StocksPanel> panelList) throws IOException{
		
			System.out.println("Updating...");
		
			DecimalFormat dollarForm = new DecimalFormat("#.##");
			DecimalFormat percentForm = new DecimalFormat("#.##");
		
			ArrayList<StockInfo> updatedInfo = new ArrayList<StockInfo>();
			Document doc;
			//begin for loop
			for(StocksPanel stock : panelList){
				
				if(stock.getExchange().equals("NYSE")){
					doc = Jsoup.connect("http://quotes.morningstar.com/stockq/c-header?&t=XNYS:"+ stock.getSymbol()).get();
				} else {
					doc = Jsoup.connect("http://quotes.morningstar.com/stockq/c-header?&t=XNAS:"+ stock.getSymbol()).get();
				}
					
				String pri = doc.select("#last-price-value").text();
				String prevClose = stock.getPrevClose();
				
				
				//CHANGE THIS YOU MORON
				//PERCENT CHANGE IS BASED ON OPENING PRICE NOT 
				//LAST PRICE TRADED DIMWIT STILL NEEDS FIXING
				
				double previousClose = Double.parseDouble(prevClose);				
				double newPrice = Double.parseDouble(pri);
				
				
				double dolChange = newPrice - previousClose;
				double perChange = (dolChange/previousClose) * 100;
				//formatting for upcoming method
				
				
				
				String dollarChange = percentForm.format(dolChange); //Double.toString(dolChange);
				String percentageChange = percentForm.format(perChange); //Double.toString(perChange);

				String price = "$"  + pri;
				StockInfo inform = new StockInfo(price, dollarChange, percentageChange);
				updatedInfo.add(inform);
			}
		
		
		
		return updatedInfo;
	}
	
	
	
	
	
}
