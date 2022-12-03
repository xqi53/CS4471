// Imports the necessary libraries
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class handles all the data handling involved with login credentials.
 * That includes verifying the credentials are correct, creating new accounts, 
 * checking that an account exists and adding an account's default goals.
 * 
 * @author Justin Weller
 */
public class CredentialsManager {
	
	// The DB files
	File userCredFile = new File("UserCredentialsDB.txt");
	File adminCredFile = new File("AdminCredentialsDB.txt");
	File goalFile = new File("GoalsDB.txt");
	
	// Verifies that the entered user credentials are in the database
	public boolean verifyUser(String enteredUser, String enteredPass)
	{
		try 
		{
			Scanner sc = new Scanner(userCredFile);
			String line;
			String[] credentials;
			
			// For every line in the user credentials file
			while(sc.hasNextLine())
			{
				// Gets a username,password pair and splits it by the comma
				line = sc.nextLine();
				
				// If the line is not empty (has credentials)
				if(!line.trim().isEmpty())
				{
					credentials = line.split(",");
					
					if(credentials[0].equals(enteredUser) && credentials[1].equals(enteredPass))
					{
						return true;
					}
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("User Credentials file not found.");
			e.printStackTrace();
			System.exit(0);
		}
		return false;
	}
	
	// Verifies that the entered admin credentials are in the database
	public boolean verifyAdmin(String enteredUser, String enteredPass)
	{
		try 
		{
			Scanner sc = new Scanner(adminCredFile);
			String line;
			String[] credentials;
			
			// For every line in the user credentials file
			while(sc.hasNextLine())
			{
				// Gets a username,password pair and splits it by the comma
				line = sc.nextLine();
				
				// If the line is not empty (has credentials)
				if(!line.trim().isEmpty()) 
				{
					credentials = line.split(",");
					
					if(credentials[0].equals(enteredUser) && credentials[1].equals(enteredPass))
					{
						return true;
					}
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Admin Credentials file not found.");
			e.printStackTrace();
			System.exit(0);
		}
		return false;
	}
	
	// Creates a new user in the system and adds its credentials to the UserCredentialsDB
	public void createUser(String user, String pass)
	{
		try
		{
			// Writes the new user to the DB
			BufferedWriter br = new BufferedWriter(new FileWriter("UserCredentialsDB.txt", true));
			br.newLine();
			br.append(user + "," + pass);
			br.close();
			System.out.println("Successfully created the new user.");
		}
		catch(IOException e)
		{
			System.out.println("User Credentials file not found.");
			e.printStackTrace();
			System.exit(0);
		}
		
		setDefaultGoals(user);
	}
	
	// Sets the default goals in the GoalsDB for the newly created user
	private void setDefaultGoals(String user)
	{
		try
		{
			// Writes the new default set of goals for the new user to the DB
			BufferedWriter br = new BufferedWriter(new FileWriter("GoalsDB.txt", true));
			br.newLine();
			br.append(user + "," + "8" + "," + "1" + "," + "3" + "," + "8" + "," + "4" + "," + "2500" + "," + "5");
			br.close();
		}
		catch(IOException e)
		{
			System.out.println("Goals file not found.");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	
	// Checks and returns if the user's username already exists in the UserCredentialsDB
	public boolean userExists(String enteredUser)
	{
		try 
		{
			Scanner sc = new Scanner(userCredFile);
			String line;
			String[] credentials;
			
			// For every line in the user credentials file
			while(sc.hasNextLine())
			{
				// Gets a username,password pair and splits it by the comma
				line = sc.nextLine();
				
				// If the line is not empty (has credentials)
				if(!line.trim().isEmpty())
				{
					credentials = line.split(",");
					
					if(credentials[0].equals(enteredUser))
					{
						return true;
					}
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("User Credentials file not found.");
			e.printStackTrace();
			System.exit(0);
		}
		return false;
	}
	
	// Checks and returns if the admin's username already exists in the AdminCredentialsDB
	public boolean adminExists(String enteredUser)
	{
		try 
		{
			Scanner sc = new Scanner(adminCredFile);
			String line;
			String[] credentials;
			
			// For every line in the user credentials file
			while(sc.hasNextLine())
			{
				// Gets a username,password pair and splits it by the comma
				line = sc.nextLine();
				
				// If the line is not empty (has credentials)
				if(!line.trim().isEmpty())
				{
					credentials = line.split(",");
					
					if(credentials[0].equals(enteredUser))
					{
						return true;
					}
				}	
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Admin Credentials file not found.");
			e.printStackTrace();
			System.exit(0);
		}
		return false;
	}
}
