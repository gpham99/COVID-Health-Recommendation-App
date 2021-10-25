package webScraping;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import dataStorage.DataForWeek;

import java.sql.Connection;
import java.text.DateFormat;

/**
 * This class handles the database creation for the program
 * It creates the database and the table and fills them with data
 * @author elle
 *
 */
public class DatabaseHandler {
	public static final String PORT_NUMBER = "3306";

	private ArrayList<String> startDate = new ArrayList<String>();
	private ArrayList<String> endDate = new ArrayList<String>();
	private ArrayList<String> cumulativeTests = new ArrayList<String>();
	private ArrayList<String> cumulativePositives = new ArrayList<String>();
	private ArrayList<String> weeklyTests = new ArrayList<String>();
	private ArrayList<String> weeklyPositives = new ArrayList<String>();
	private ArrayList<ArrayList<String>> allData = new ArrayList<>();

	private DataForWeek mostRecentWeek;

	/**
	 * This method creates the database and the table within it that hold
	 * information on past covid cases at Colorado College
	 */
	public void createDatabase() {
		//create database
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/?serverTimezone=UTC", 
				"root", "root"); // MySQL
				Statement stmt = conn.createStatement();
				) {
			String sql = "create database if not exists CovidData";
			stmt.execute(sql);
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		//create table
		try (
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/CovidData?user=root&password=root&serverTimezone=UTC"); // MySQL
				Statement stmt = conn.createStatement();
				) {
			String sql = "create table if not exists ColoradoCollegeData ( " +
					"startDay date not null, " +
					"endDay date, "+
					"cumulativeTestsAdministered  int, " + 
					"cumulitivePositiveTests int, " +
					"weeklyTestsAdministered int, "+
					"weeklyPositiveTests int, "+
					"primary key (endDay));";

			stmt.execute(sql);
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This method fills the database with the relevant data by 
	 * extracting it from the web scrape proxy and adding it to the database
	 */
	
	public void fillDatabase(WebScrapeProxy proxy) {
		try (
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/CovidData?user=root&password=root&serverTimezone=UTC"); // MySQL

				Statement stmt = conn.createStatement();
				) {
			String sqlClear = "delete from ColoradoCollegeData";
			stmt.executeUpdate(sqlClear);
			allData = proxy.getScrapedData();
			initializeArrays(allData);
			int numAdded = 0;
			for(int i = 0; i< startDate.size(); i++) {
				String sqlInsert = "insert into ColoradoCollegeData values "
						+ "( str_to_date('" + startDate.get(i) + "','%m/%d/%Y'), "
						+ "str_to_date('" + endDate.get(i) + "', '%m/%d/%Y'), "
						+ cumulativeTests.get(i) + ", " + cumulativePositives.get(i) + ", "
						+ weeklyTests.get(i) + ", " + weeklyPositives.get(i)+ ");";
				stmt.executeUpdate(sqlInsert);
				numAdded++;	
			}
			System.out.println("\n"+ numAdded + " records were added");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This method initializes the arrayList for each column of the table
	 * @param allData - the arrayList of all the needed arraylists
	 */
	private void initializeArrays(ArrayList<ArrayList<String>> allData) {
		startDate = allData.get(0);
		endDate = allData.get(1);
		cumulativeTests = allData.get(2);
		cumulativePositives = allData.get(3);
		weeklyTests = allData.get(4);
		weeklyPositives = allData.get(5);
	}
	/**
	 * This method will extracts the necessary data from the database
	 * (the most recent dates, number of tests in the most recent week,
	 * and positive tests in the most recent week) and saves the data
	 */
	public void collectData() {
		try (
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/CovidData?user=root&password=root&serverTimezone=UTC"); // MySQL
				Statement stmt = conn.createStatement();
				) {

			String strSelect = "select * from ColoradoCollegeData order by startDay desc";
			ResultSet rset = stmt.executeQuery(strSelect);
			rset.next();
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

			String startOfWeek = dateFormat.format( rset.getDate("startDay"));
			String endOfWeek = dateFormat.format(rset.getDate("endDay"));
			int testsThisWeek = rset.getInt("weeklyTestsAdministered");
			int positivesThisWeek = rset.getInt("weeklyPositiveTests");	
			mostRecentWeek = new DataForWeek(startOfWeek, endOfWeek, testsThisWeek, positivesThisWeek);
			System.out.println("\nData for the most recent week: ");
			System.out.println(mostRecentWeek.toString());
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public DataForWeek getMostRecentWeek() {
		return mostRecentWeek;
	}
}
