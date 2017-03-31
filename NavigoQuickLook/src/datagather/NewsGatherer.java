package datagather;

import java.io.IOException;
import personalobjects.NewsObject;
import java.util.ArrayList;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class NewsGatherer {
	
//	public static void main(String[] args) throws IOException{
//		
//		//Creates a new ArrayList with all of the news needed 
//		NewsGatherer nGatherer = new NewsGatherer();
//		
//		ArrayList<NewsObject> heads = nGatherer.gatherNewsData();
//		
//		//Prints one of the NewsObjects in the array for testing
//		//heads.get(0).showInfo();
//		System.out.println(heads.get(0).getDesc());
//		System.out.println(heads.get(0).getHeadline());
//	}
	
	
	//Method that gathers the news data
	public ArrayList<NewsObject> gatherNewsData() throws IOException{
		
		//Creates ArrayList of NewsObjects to be returned by the method
		ArrayList<NewsObject> news_list = new ArrayList<NewsObject>();
		
		//Gets the source html and puts it into the variable document
		Document document = Jsoup.connect("http://www.reuters.com/finance/markets").get();
		
		//Loops through the html source finding the title, url, and description of each news
		//article. The creates a NewsObject and stores it in the NewsObject ArrayList, news_list
		for(Element article : document.select(".story")){		
			//gets each specific portion of the data and puts it into a var
			String newsTitle = article.select(".story-title").text();
			String newsDescription = article.select("p").text();
			String newsUrl = article.select("a").attr("href"); 
			
			//create the NewsObject and adds it to the ArrayList
			news_list.add(new NewsObject(newsTitle, newsUrl, newsDescription));
			
		}
	
		return news_list;
	}
	
	
}
