import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class adds the user goals to the GoalsDB. It also provides the goals of specific user.
 * @author Narges Haeri
 */

public class GoalSetter {
	
	private File userGoalFile;
	private String[] useGoal;
	private String[] categoryList = {"Hydration", "Exercise", "Alcohol intake", "Sleep quality", "Fruit and vegetable intake", "Calorie intake", "Smoking"};
	private String input = "bobo,Hydration,30"; 
	
	/** 
     *  This method adds user input for goals into GoalsDB.txt. 
     */
  
	public void replaceGoalInFile(String username, String category, float number) {
		
		try 
		{
			userGoalFile = new File("GoalsDB.txt");
			Path path = Paths.get("GoalsDB.txt");
			java.util.List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			
			String sNumber = Float.toString(number);
			String[] inputArray = {username, category, sNumber};
			Scanner sc;
				
			sc = new Scanner(userGoalFile);
			String line;
			
			// Processing user data lines in the userHealthFile.txt
			while(sc.hasNextLine())
			{
				// Reads a user data line
				line = sc.nextLine();
				
				// Checks the line is not empty 
				if(!line.trim().isEmpty())
				{
					useGoal = line.split(",");
					
					// Checks the position of user selected category in a line (i.e., hydration is a second value in a line).
					int categoryIndex = Arrays.asList(categoryList).indexOf(inputArray[1])+1;
					
					// Finds the user that wants to set goal.
					if(useGoal[0].equals(inputArray[0]))
					{
						lines.remove(line);		
						String newEntry = inputArray[0];
						
						// Updates goal value for the user selected category.
						for(int i=1; i < useGoal.length; i++) 
						{
							if(i == categoryIndex) 
							{
								newEntry += "," + inputArray[2];
							} else 
							{
								newEntry += "," + useGoal[i];
							}
							
						}
						lines.add(newEntry);
						writeToFile(lines);
						break;
						
					} 
				}
			}
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		
	} // End of replaceGoalInFile method
		
	/**
	 * this helper method overwrites the GoalsDB.txt file with updated data.
	 * @param lines is the updated record lines
	 */
	private void writeToFile(java.util.List<String> lines) {
		
		try 
		{	
			FileWriter userGoalsFile = new FileWriter("GoalsDB.txt");  
			BufferedWriter userGoalsBuffer = new BufferedWriter(userGoalsFile);  
		
			// Adding updated record lines to the GoalsDB.txt file 
		    for(int i = 0; i < lines.size(); i++) 
		    {
		    	userGoalsBuffer.write(lines.get(i) + "\n");
		    }
		    
		    userGoalsBuffer.close();
		 	
		}catch (IOException e)
		{	
			e.printStackTrace();
		}
	} // End of writeToFile method
	
	/**
	 * This method can be called to get the recorded goal for a specific user 
	 * @param userId id that uniquely identifies the user.
	 * @return the recorded goal for target user
	 */
	public String[] getGoals (String userId) {
		
		try {
			userGoalFile = new File("GoalsDB.txt");
			Path path = Paths.get("GoalsDB.txt");
			java.util.List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		
			for (int i = 0; i < lines.size(); i++) 
			{
				String goals = lines.get(i);
				String[] goal = goals.split(",");
			
				if(goal[0].equals(userId)) 
				{
					return goal;
				}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	} // End of getGoals method
} // End of GoalSetter class 


