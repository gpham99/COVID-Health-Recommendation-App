package webScraping;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Class that webscrapes the CC proxy for COVID data
 * Delegates whether the actual web scraper is used or a concrete file
 * @author annikapiccaro
 *
 */
public class WebScrapeProxy implements COVIDData {
	public static final String CCCOVID_FILE = "Community_Counts_Full_Data_data (1)";
	public static final int DATE_RANGE = 0;
	public static final int WEEK_POS = 2;
	public static final int CUM_POS = 3;
	public static final int WEEK_TEST = 4;
	public static final int CUM_TEST = 5;
	public static final int DECADE = 20;
	private String proxyName;
	private ArrayList<String> startDate = new ArrayList<String>();
	private ArrayList<String> endDate = new ArrayList<String>();
	private ArrayList<String> cumulativeTests = new ArrayList<String>();

	private ArrayList<String> cumulativePositives = new ArrayList<String>();
	private ArrayList<String> weeklyTests = new ArrayList<String>();
	private ArrayList<String> weeklyPositives = new ArrayList<String>();
	private ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();

	/**
	 * Creates the web scrape proxy
	 * @param proxyName - the name of the proxy
	 */
	public WebScrapeProxy(String proxyName) {
		this.proxyName = proxyName;
	}
	
	/**
	 * Determines if their is a proxy to scrape from
	 * if not the default file is read
	 */
	@Override
	public void scrapeCCCovidData() {
		if (proxyName == null) {
			readDefaultFile();
		} else {
			WebScraperRealObject webScrape = new WebScraperRealObject(proxyName);
			webScrape.scrapeCCCovidData();
		}

	}
	
	/**
	 * This method returns an arrayList of all the arrayLists
	 * with data
	 * @return allData- the arrayList with all the data
	 */
	public ArrayList<ArrayList<String>> getScrapedData(){
		allData.add(startDate);
		allData.add(endDate);
		allData.add(cumulativeTests);
		allData.add(cumulativePositives);
		allData.add(weeklyTests);
		allData.add(weeklyPositives);
		return allData;
	}
	
	/**
	 * Reads the default file and error handles
	 */
	public void readDefaultFile() {
		System.out.println("Reading " + CCCOVID_FILE);
		try {
			Scanner in = new Scanner(new FileInputStream(CCCOVID_FILE + ".csv"));
			while (in.hasNextLine()) {
				String timedCCData = in.nextLine();

				addToColumns(timedCCData);
			}
		} // catches the file not found exception-shouldnt be one because the file is a
			// constant

		catch (FileNotFoundException e) {
			System.out.println("Please move a default file into project");
		}
	}
	
	/**
	 * Breaks apart the file into 5 forms of data and adds them to the corresponding
	 * arraylists
	 * 
	 * @param timedCCData
	 */
	private void addToColumns(String timedCCData) {
		//Only want Colorado College data- not El Paso
		if (timedCCData.contains("Colorado College")) {
			//Splits based on tab characters
			String[] ccDataSplit = timedCCData.split("\t");
			
			String date1 = ccDataSplit[DATE_RANGE];
			
			//dates that contain this character need an added 0 in front of the month number
			if (date1.contains("_")) {
				date1 = "0" + date1.substring(1);
			}
			//end date is separated from start date
			String date2 = date1.substring(date1.indexOf("-") + 1);// dd/mm/yy
			if(date1.length()>14){//dd/mm/yy-when the year changes
				String tempDate=date1;
				date1 = date1.substring(0, date1.indexOf("-"));
				date1 = date1.substring(0,date1.lastIndexOf("/"))+"/"+DECADE+date1.substring(date1.lastIndexOf("/") + 1);
		
			}else {
				
			date1 = date1.substring(0, date1.indexOf("-")) + "/"+DECADE + date1.substring(date1.lastIndexOf("/") + 1);
			}
			//end date is split to add decade and 0 in front of month number
			String date2Split = date2.substring(0, date2.lastIndexOf("/"));// dd/mm
			if (date2Split.substring(0, date2Split.indexOf("/")).length() == 3) {
				date2Split = "0" + date2Split;
			}
			date2 = date2Split + "/"+DECADE + date2.substring(date2.lastIndexOf("/") + 1);
			// all values added to their corresponding "columns"
			startDate.add(date1);
			endDate.add(date2);
			weeklyPositives.add(ccDataSplit[WEEK_POS].replaceAll(" ", ""));
			cumulativePositives.add(ccDataSplit[CUM_POS].replaceAll(" ", ""));
			weeklyTests.add(ccDataSplit[WEEK_TEST].replaceAll(" ", ""));
			cumulativeTests.add(ccDataSplit[CUM_TEST].replaceAll(" ", ""));
		}
	}
}