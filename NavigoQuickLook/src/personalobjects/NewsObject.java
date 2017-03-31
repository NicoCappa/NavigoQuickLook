package personalobjects;

public class NewsObject {

	//fields
	String url;
	String headline;
	String desc;
	
	
	
	
	//NewsObject Constructor
	public NewsObject(String newHeadline, String newUrl, String newDesc){
		
		url = "http://www.reuters.com" + newUrl;
		headline = newHeadline;
		desc = newDesc;
		
	}

	
	
//////// Methods ////////

	//prints the information of the news object
	public void showInfo(){
		System.out.println("--------------------");
		System.out.println(this.headline);
		System.out.println(this.url);
		System.out.println(this.desc);
		System.out.println("--------------------");

	}
	
	
	
	
	


//////// Getters & Setters ////////
	
	//URL Getter & Setter
	public String getUrl() {
		return (String)url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	//Headline Getter & Setter
	public String getHeadline() {
		return this.headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	//Description Getter & Setter  
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
