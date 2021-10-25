package dataStorage;

	import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;  

/*
 * @author Nick Bohm
 * This class is responsible for reading in a healthrec csv file and adding specific activities
 */

public class HealthRecommendations {
	private ArrayList<String> activites ;
	
	
	public ArrayList<String> getSpecificRecList(String riskType) throws FileNotFoundException{
		
	this.activites = new ArrayList<String>();
	Scanner sc = new Scanner(new File("HealthRecommendations.csv"));  
	sc.useDelimiter(",");   //sets the delimiter pattern 
	String activity = null;
	
	
	
	while (sc.hasNextLine())  //returns a boolean value  
	{  
	
		activity = sc.next();
		
		if (sc.nextLine().contains(riskType) ) {
			this.activites.add(activity);
		}
			
	
	}   
	

	sc.close();  //closes the scanner  
	return this.activites;
	}
	
	public String toString() {
		String allActivity ="";
		for (String activity: activites) {
			allActivity +=activity;
			allActivity += "\n";
		}
	
		return allActivity;
	
	}
	
	
	
}
