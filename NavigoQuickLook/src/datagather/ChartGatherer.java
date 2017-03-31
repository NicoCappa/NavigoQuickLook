package datagather;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javafx.scene.image.Image;
import personalobjects.StockInfo;

import java.io.IOException;
import java.util.ArrayList;



public class ChartGatherer {

//// Methods ////	
	
	//http://finviz.com/chart.ashx?t=MSFT&ty=c&ta=1&p=d&s=l
	public Response getChart(){
		
		try {
			Response resultImageResponse = Jsoup.connect("www.finviz.com/chart.ashx?t=MSFT&ty=c&ta=1&p=d&s=l").ignoreContentType(true).execute();
			
			return resultImageResponse;
		}
		
		catch(Exception e) {
			
			
		}
		
		
		
		return null;
		
	}
	
	
}
