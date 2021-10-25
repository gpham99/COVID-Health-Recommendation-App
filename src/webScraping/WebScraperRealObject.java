package webScraping;

/**
 * Defines the Real WebScraper object if the 
 * @author annikapiccaro
 *
 */
public class WebScraperRealObject implements COVIDData{
	private String proxyName;
	
	/**
	 * Creates the real webSrcaper Object
	 * @param proxyName - the name of the proxy
	 */
	public WebScraperRealObject(String proxyName) {
		this.proxyName=proxyName;
	}
	/**
	 * WebScrape Stub that will be implemented if the real object is called
	 * -Currently its a stub, but if we have time will have true functionality-
	 */
	@Override
	public void scrapeCCCovidData() {
		//WEBSCRAPE STUB
		System.out.println("Scraping " + proxyName);
		
	}
	
}
