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
		String[] categories = {"Hydration", "Exercise", "Alcohol", "Sleep", "Fruit & Veg", "Calorie", "Smoking"};
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
				
		
	
	public void feedback(String userID) {
		String[] healthInfo = getHealthInfo(userID);
		String[] goals = getGoals(userID);
		
		
		boolean achieveAllGoals = true;
		if(healthInfo.length == 0 ) {
			System.out.println("Please input all health info first");
		}
		else {
			int temp;
			// Hydration
			if(Integer.parseInt(healthInfo[2]) < Integer.parseInt(goals[1])) {
				achieveAllGoals = false;
				temp = Integer.parseInt(goals[1]) - Integer.parseInt(healthInfo[2]);
				System.out.println("You need " + temp + " more cups of water to reach your goal for hydration.");
			}
			
			// Exercise
			if(Integer.parseInt(healthInfo[3]) < Integer.parseInt(goals[2])) {
				achieveAllGoals = false;
				temp = Integer.parseInt(goals[2]) - Integer.parseInt(healthInfo[3]);
				System.out.println("You need " + temp + " more hours exercise to reach your goal for exercise.");
			}
			
			// alcohol
			if(Integer.parseInt(healthInfo[4]) > Integer.parseInt(goals[3])) {
				achieveAllGoals = false;
				temp = Integer.parseInt(healthInfo[4]) - Integer.parseInt(goals[3]);
				System.out.println("You need to drink " + temp + " less cups of alcohol to reach your goal for alcohol.");
			}
			
			// sleep
			if(Integer.parseInt(healthInfo[5]) < Integer.parseInt(goals[4])) {
				achieveAllGoals = false;
				temp = Integer.parseInt(goals[4]) - Integer.parseInt(healthInfo[5]);
				System.out.println("You need " + temp + " more hours of sleep to reach your goal for sleep quality.");
			}
			
			// fruit & veg
			if(Integer.parseInt(healthInfo[6]) < Integer.parseInt(goals[5])) {
				achieveAllGoals = false;
				temp = Integer.parseInt(goals[5]) - Integer.parseInt(healthInfo[6]);
				System.out.println("You need " + temp + " more servings fruit & veg to reach your goal for fruit & veg.");
			}
			
			// calorie
			if(Integer.parseInt(healthInfo[7]) > Integer.parseInt(goals[6])) {
				achieveAllGoals = false;
				temp = Integer.parseInt(healthInfo[7]) - Integer.parseInt(goals[6]);
				System.out.println("You need to take " + temp + " less calories to reach your goal for calorie.");
			}
			
			// smoking
			if(Integer.parseInt(healthInfo[8]) > Integer.parseInt(goals[7])) {
				achieveAllGoals = false;
				temp = Integer.parseInt(healthInfo[8]) - Integer.parseInt(goals[7]);
				System.out.println("You need to smoke " + temp + " less cigarettes to reach your goal for smoking.");
			}
			
			if(achieveAllGoals) {
				System.out.println("Congratulations, you have reached all your daily goals!");
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
