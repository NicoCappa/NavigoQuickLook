package datagather;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import personalobjects.StockInfo;

import java.io.IOException;
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
		Document document = Jsoup.connect("http://www.msn.com/en-us/money/stockdetails?symbol=US:" + symbol).get();
		String company =  document.select(".live-quote-title").text();
		String price = "$" + document.select(".current-price").text();
		String change = document.select(".chngeamount.increase").text();
		
		try{	
			String[] temp = change.split("%");
			
			String liveHours = temp[0].trim().replace(" ", " (") + "%)";
			
			String afterHours = temp[1].trim().replace(" ", " (") + "%)";
			
			
			change = String.format("Close: %s \n AH: %s", liveHours, afterHours);
			
			System.out.println("--------------");
			System.out.println(liveHours);
			System.out.println(afterHours);
			System.out.println("--------------");
			
			StockInfo info = new StockInfo(symbol, company, price, change);
			return info;
		}
		
		catch(Exception e){
			
			System.out.println(e);
			
			StockInfo info = new StockInfo(symbol, company, price, change); 
			return info;
			
		
		}
		
		
		
		
		
		
	}
	
	
}
