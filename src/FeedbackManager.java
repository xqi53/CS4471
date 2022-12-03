import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import static java.util.Map.entry;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

/**
 * This class handles the reminder and feedback.
 * This includes that checking the exiting health info and compare it with goal.
 * 
 * @author Kun Wang
 *
 */

public class FeedbackManager {
	File goalFile = new File("GoalsDB.txt");
	File healthinfoFile = new File("HealthInfoDB.txt");
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	LocalDateTime now = LocalDateTime.now();  
	String date = dtf.format(now);


	
	public void reminder(String userID) {
//		System.out.println(date);
		List<String> emptyCategory = new ArrayList<>();
		String[] categories = {"hydration", "exercise", "alcohol", "sleep", "fruit&veggie", "calorie", "smoking"};
		String[] healthInfo = getHealthInfo(userID);
		
		if(healthInfo.length == 0) {
			System.out.println("Reminder: please input your today's health date.");
		}
		
		for(int i = 2; i < healthInfo.length; i++) {
			if(healthInfo[i].equals("-1")) {
				emptyCategory.add(categories[i-2]);
			}
		}
		if(emptyCategory.size() > 0 ) {
			String cate = String.join(", ", emptyCategory);
			System.out.println("Please your input health data for the following category: " + cate);
		}
					
	}
				
		
	
	public void feedback(String userID, String category) {
		String[] healthInfo = getHealthInfo(userID);
		String[] goals = getGoals(userID);
		
		
		boolean achieveAllGoals = true;
		if(healthInfo.length == 0 ) {
			System.out.println("Please input all health info first");
		}
		else {
			float temp;
			switch(category) {
				case "hydration":
					
					// Hydration
					if(Float.parseFloat(healthInfo[2]) < Float.parseFloat(goals[1])) {
						achieveAllGoals = false;
						temp = Float.parseFloat(goals[1]) - Float.parseFloat(healthInfo[2]);
						System.out.println("You need " + temp + " more cups of water to reach your goal for hydration.");
					}
					else {
						System.out.println("Congratulations, you have reached your daily on hydration!");
					}
					break;
				case "exercise":
					// Exercise
					if(Float.parseFloat(healthInfo[3]) < Float.parseFloat(goals[2])) {
						achieveAllGoals = false;
						temp = Float.parseFloat(goals[2]) - Float.parseFloat(healthInfo[3]);
						System.out.println("You need " + temp + " more hours exercise to reach your goal for exercise.");
					}
					else {
						System.out.println("Congratulations, you have reached your daily on exercise!");
					}
					break;
				case "alcohol":
					// alcohol
					if(Float.parseFloat(healthInfo[4]) > Float.parseFloat(goals[3])) {
						achieveAllGoals = false;
						temp = Float.parseFloat(healthInfo[4]) - Float.parseFloat(goals[3]);
						System.out.println("You need to drink " + temp + " less cups of alcohol to reach your goal for alcohol.");
					}
					else {
						System.out.println("Congratulations, you have reached your daily on alcohol!");
					}
					break;
				case "sleep":
					// sleep
					if(Float.parseFloat(healthInfo[5]) < Float.parseFloat(goals[4])) {
						achieveAllGoals = false;
						temp = Float.parseFloat(goals[4]) - Float.parseFloat(healthInfo[5]);
						System.out.println("You need " + temp + " more hours of sleep to reach your goal for sleep quality.");
					}
					else {
						System.out.println("Congratulations, you have reached your daily on sleep!");
					}
					break;
				case "fruit&veggie":
					// fruit & veg
					if(Float.parseFloat(healthInfo[6]) < Float.parseFloat(goals[5])) {
						achieveAllGoals = false;
						temp = Float.parseFloat(goals[5]) - Float.parseFloat(healthInfo[6]);
						System.out.println("You need " + temp + " more servings fruit & veg to reach your goal for fruit & veg.");
					}
					else {
						System.out.println("Congratulations, you have reached your daily on fruit&veggie!");
					}
					break;
				case "calorie":
					// calorie
					if(Float.parseFloat(healthInfo[7]) > Float.parseFloat(goals[6])) {
						achieveAllGoals = false;
						temp = Float.parseFloat(healthInfo[7]) - Float.parseFloat(goals[6]);
						System.out.println("You need to take " + temp + " less calories to reach your goal for calorie.");
					}
					else {
						System.out.println("Congratulations, you have reached your daily on calorie!");
					}
					break;
				case "smoking":
					// smoking
					if(Float.parseFloat(healthInfo[8]) > Float.parseFloat(goals[7])) {
						achieveAllGoals = false;
						temp = Float.parseFloat(healthInfo[8]) - Float.parseFloat(goals[7]);
						System.out.println("You need to smoke " + temp + " less cigarettes to reach your goal for smoking.");
					}
					else {
						System.out.println("Congratulations, you have reached your daily on smoking!");
					}
					break;
			}
			
			
		}
		
		
	}
	
	private String[] getHealthInfo(String userID) {
		try {
			Scanner sc = new Scanner(healthinfoFile);
			String curr_line;
			String[] healthInfo;
			while(sc.hasNext()) {
				curr_line = sc.nextLine();
				healthInfo = curr_line.split(",");
				
				if(healthInfo[0].equals(date) && healthInfo[1].equals(userID)) {
					return healthInfo;
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("HealthInfo file not found.");
			e.printStackTrace();
			System.exit(0);
		}
		return new String [] {};
	}
	
	private String[] getGoals(String userID) {
		try {
			Scanner sc = new Scanner(goalFile);
			String curr_line;
			String[] goals;
			while(sc.hasNext()) {
				curr_line = sc.nextLine();
				goals = curr_line.split(",");
				if(goals[0].equals(userID)) {
					return goals;
				}
			}
		}
		
		catch(FileNotFoundException e)
		{
			System.out.println("HealthInfo file not found.");
			e.printStackTrace();
			System.exit(0);
		}
		return new String [] {};
	}
		
	
}
