import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class adds the user data to the HealthInfoDB. It also provides the health info of specific user. 
 * @author Narges Haeri
 */

public class DataRecorder{

	public String date = java.time.LocalDate.now().toString();
	private File userHealthFile;
	private String[] userHealthData;
	private String[] categoryList = {"hydration", "exercise", "alcohol", "sleep", "fruit&veggie", "calorie", "smoking"};
	private String lastInput = null;           //to check if user entered data in the past
	private String currentInput = null;        //to check if user entered data today
	private FeedbackManager fd = new FeedbackManager();
	
	
	/**
	 * This method adds user input for specific health category into HealthInfoDB. 
	 */
	public void addNewEntry(String username, String category, float number) {	
		try 
		{
			userHealthFile = new File("HealthInfoDB.txt");
			String sNumber = Float.toString(number);
			String[] inputArray = {username, category, sNumber};	
			
			Scanner sc;
			
			// Reading the whole file content and save it into lines
			Path path = Paths.get("HealthInfoDB.txt");
			java.util.List<String> lines;
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			
			sc = new Scanner(userHealthFile);
			String line;
			
			// Checks the position of user selected category in a line (i.e., hydration is a third value in a line).
			int categoryIndex = Arrays.asList(categoryList).indexOf(inputArray[1])+2;
			
			// Processing user data lines in the HealthInfoDB.txt
			while(sc.hasNextLine())
			{
				// Reads a data line
				line = sc.nextLine();
				
				// Checks if the line is empty 
				if(!line.trim().isEmpty())
				{
					
					userHealthData = line.split(",");

					if(userHealthData[1].equals(inputArray[0]))
					{
						// Finding the first data that user entered in the past
						lastInput = line;
						
						// Finding the data that user entered today
						if(date.equals(userHealthData[0])) {
							currentInput = line;
						}
					}
				} // End of if
			} // End of while loop
			
			// Updating or adding new values to the file based on whether the user entered data today or in the past.
			if(lastInput == null && currentInput == null) 
			{ // User does not have any data (not today and not in the past)
				
				// Adds value to related category and initializes rest of the entries to -1.
				String newEntry = date + "," + inputArray[0];
				
				for(int j=2; j<userHealthData.length; j++) 
				{	
					if(j == categoryIndex) 
					{
						newEntry += "," + inputArray[2];
						
					} else 
					{	
						newEntry += "," + -1;
					} 
				} // End of for loop
				
			    lines.add(0, newEntry);
			    
			    // Overwriting HealthInfoDB.txt with updated content 
			    writeToFile(lines);
			    
			}else if(lastInput == null && currentInput != null)    
			{ // User have a record only for today (has no data in the past)
					
				String[] c = currentInput.split(",");
				
				// Updates value of related category in today's user record.
				String newEntry = date;
					
				for(int j=1; j<userHealthData.length; j++) 
				{
					if(j == categoryIndex) 
					{					
						newEntry += "," + inputArray[2];
					} else 
					{		
						newEntry += "," + c[j];
					} 
				} // End of for loop
				
				// Removing the line that needs to be updated and adding the updated version 
				lines.remove(currentInput);	
				lines.add(0, newEntry);
				
				// Overwriting HealthInfoDB.txt with updated content 
				writeToFile(lines);

			}else if(currentInput != null && lastInput != null) 
			{   // User recorded data previously and has record for today as well.      
				
				String[] c = currentInput.split(",");
					
				// Updates value of related category in today's user record.		
				String newEntry = date;
					
				for(int j=1; j<userHealthData.length; j++) 
				{
					if(j == categoryIndex) 
					{				
						newEntry += "," + inputArray[2];
					} else 
					{			
						newEntry += "," + c[j];
					}
				} // End of for loop
						
				// Removing the line that needs to be updated and adding the updated version
				lines.remove(currentInput);	
				lines.add(0, newEntry);
						
				// Overwriting HealthInfoDB.txt with updated content 
				writeToFile(lines);
						
				} else if(lastInput != null && currentInput == null)
				{  // User does not have any record for today, but recorded data previously
					
					// Adds value to related category and initializes rest of the entries to -1.
					String newEntry = date + "," + inputArray[0];
					
					for(int j=2; j<userHealthData.length; j++) 
					{	
						if(j == categoryIndex) 
						{
							newEntry += "," + inputArray[2];					
						} else 
						{	
							newEntry += "," + -1;
						}
					}
						
				    lines.add(0, newEntry);
				    
				    // Overwriting HealthInfoDB.txt with updated content 
				    writeToFile(lines);
				} // End of if-else statements
			
			// Reports how much the user is behind his/her goals based on the input data
			fd.feedback(inputArray[0], inputArray[1]);
			
			
			
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		
	} // End of addNewEntry method
		
	private void writeToFile (java.util.List<String> lines) {
		
		try 
		{	
			FileWriter userHealthFile = new FileWriter("HealthInfoDB.txt");  
			BufferedWriter userHealthBuffer = new BufferedWriter(userHealthFile);  
			
		    for(int i = 0; i < lines.size(); i++) 
		    {
		    	userHealthBuffer.write(lines.get(i) + "\n");
		    }
		    
			userHealthBuffer.close();
		 	
		}catch (IOException e)
		{	
			e.printStackTrace();
		}
	} // End of writeToFile method
	
	/**
	 * This method can be called to get the recorded health data for a specific user 
	 * @param userId id that uniquely identifies the user.
	 * @return the recorded data for target user
	 */
	public String[] getHealthInfo (String userId) {
		try {
			
		// Reading the whole file content and save it into lines
		Path path = Paths.get("HealthInfoDB.txt");
		java.util.List<String> lines;
		
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		
					
		for (int i = 0; i < lines.size(); i++) 
		{
			String goals = lines.get(i);
			String[] goal = goals.split(",");
			
			if(goal[1].equals(userId)) 
			{
				return goal;
			}
		}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	} // End of getHealthInfo method

} // End of DataRecorder class

