package personalobjects;

public class StockInfo {

	//Fields
	String symbol;
	String company;
	String price;
	String percentChange;
	String percentChangeString;
	
	public StockInfo(String symb, String comp, String pri, String percent){
		
		symbol = symb;
		company = comp;
		price = pri;
		percentChange = percent;
		percentChangeString = percent;
				
	}

///// Methods ///// 
	
	public void getInfo(){
		
		System.out.println("--------");
		System.out.println(symbol);
		System.out.println(company);
		System.out.println(price);
		System.out.println(percentChangeString);
		
	}
	
	
	
//// Getters and Setters //// 	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}
	
	
	
	
	
	
	
	
}
