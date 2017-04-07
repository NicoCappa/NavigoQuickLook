package personalobjects;

public class StockInfo {

	//Fields
	String symbol;
	String company;
	String price;
	String percentChange;
	String dollarChange;
	String volume;
	String avgVolume;
	String exchange;
	String previousClose; 
	
	
	public StockInfo(String pri, String dolChange, String perChange){
		
		price = pri;
		dollarChange = dolChange;
		percentChange = perChange;
		
	}
	
	
	//Constructor SYMBOL/COMPANY/PRICE/CHANGE
	public StockInfo(String symb, String comp, String pri, String Chan){
	
		symbol = symb;
		company = comp;
		price = pri;	
	
	}
	
	
	//Constructors SYMBOL/COMPANY/PRICE/DOLLAR/PERCENT
	public StockInfo(String symb, String comp, String pri, String dollarChan, String percent){
		
		symbol = symb;
		company = comp;
		price = pri;
		dollarChange = dollarChan;
		percentChange = percent;
				
	}
	
	//Constructor #2 SYMBOL/PRICE/DOLLAR/PERCENT
	public StockInfo(String symb, String pri, String dollarChan, String percent, String XCHAN, String prevC){
		
		symbol = symb;
		price = pri;
		dollarChange = dollarChan;
		percentChange = percent;
		exchange = XCHAN;
		previousClose = prevC;
	
	}
	
	
	
	

///// Methods ///// 
	
	public void getInfo(){
		
		System.out.println("--------");
		System.out.println(symbol);
		System.out.println(company);
		System.out.println(price);

		
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
	
	public String getDollarChange() {
		return dollarChange;
	}

	public void setDollarChange(String dollarChange) {
		this.dollarChange = dollarChange;
	}

	public String getExchange() {
		return this.exchange;
	}
	
	public String getPrevClose(){
		return this.previousClose;
	}
	
	
	
	
	
	
	
	
}
