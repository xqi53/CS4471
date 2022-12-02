// Imports the necessary libraries
import java.util.Scanner;

/**
 * This class handles all the UI elements involved with the login process.
 * This includes selecting if they are an admin or user, creating a new account,
 * and logging in the user or admin.
 * 
 * @author Justin Weller
 */
public class LoginUI {

	private final String DASHES = "\t---------------------------------------";
	
	// Requests the user to select if they are a normal user or an admin
	public void requestUserType()
	{
		// Input variables
		String userInput;
		int optionSelected;
		Scanner sc = new Scanner(System.in);
		
		// Whenever a deeper UI goes back to this UI method using "return", all of this code is rerun 
		while(true)
		{
			optionSelected = 0;
			
			// Print menu options
			System.out.println("\n" + DASHES + "\n\tWELCOME TO THE HEALTH MANAGEMENT SYSTEM\n" + DASHES);
			System.out.println("\t1. User Login" + "\n\t2. Admin Login" + "\n\t3. Exit\n" + DASHES);
			
			while(optionSelected < 1 || optionSelected > 3)
			{
				System.out.println("Enter an option: ");
				// Get user input
				userInput = sc.nextLine();
				
				try 
				{
					optionSelected = Integer.parseInt(userInput);
					
					// If the inputted integer is not a valid option
					if(optionSelected < 1 || optionSelected > 3)
					{
						System.out.println("Please input a valid option number.");
					}
				} 
				// If the inputted value is not an integer
				catch(NumberFormatException e)
				{
					System.out.println("Please input a valid option number.");
				}
			}
			
			// Option handler
			switch(optionSelected)
			{
				case 1:
					displayUserOptions();
					break;
				case 2:
					loginAdmin();
					break;
				case 3:
					System.exit(0);
			}
		}
	}
	
	// The UI for the variety of options a user can do
	private void displayUserOptions()
	{
		// Print menu options
		System.out.println("\n" + DASHES + "\n\tUSER LOGIN MENU\n" + DASHES);
		System.out.println("\t1. Login" + "\n\t2. Create User Account" + "\n\t3. Back\n" + DASHES);
		
		// Input variables
		String userInput;
		int optionSelected = 0;
		Scanner sc = new Scanner(System.in);
		
		while(optionSelected < 1 || optionSelected > 3)
		{
			System.out.println("Enter an option: ");
			// Get user input
			userInput = sc.nextLine();
			
			try 
			{
				optionSelected = Integer.parseInt(userInput);
				
				// If the inputted integer is not a valid option
				if(optionSelected < 1 || optionSelected > 3)
				{
					System.out.println("Please input a valid option number.");
				}
			} 
			// If the inputted value is not an integer
			catch(NumberFormatException e)
			{
				System.out.println("Please input a valid option number.");
			}
		}
		
		// Option handler
		switch(optionSelected)
		{
			case 1:
				loginUser();
				break;
			case 2:
				createUser();
				break;
			case 3:
				return;
		}
	}
	
	// The UI for the create account page
	private void createUser()
	{
		// Input variables
		String username, password;
		Scanner sc = new Scanner(System.in);

		CredentialsManager credMan = new CredentialsManager();
		
		// Loop for selecting a valid username
		while(true)
		{
			System.out.println("Enter a new username: ");
			// Get user input
			username = sc.nextLine();
			
			// The username cannot contain spaces
			if(username.contains(" "))
			{
				System.out.println("The username cannot have spaces, please try again.");
				continue;
			}
			// The username cannot be empty
			else if(username.equals(""))
			{
				System.out.println("The username cannot be empty, please try again.");
				continue;
			}
			// The username cannot already exist, must be unique
			else if(credMan.userExists(username))
			{
				System.out.println("Username already exists, please try again.");
				continue;
			}
			break;
		}
		
		// Loop for selecting a valid password
		while(true)
		{
			System.out.println("Enter a new password: ");
			// Get user input
			password = sc.nextLine();
			
			// The password cannot contain spaces
			if(password.contains(" "))
			{
				System.out.println("The password cannot have spaces, please try again.");
				continue;
			}
			else if(password.equals(""))
			{
				System.out.println("The password cannot be empty, please try again.");
				continue;
			}
			break;
		}
		credMan.createUser(username, password);
		
//		NOTE: UNCOMMENT THESE TWO LINES AND PROPERLY ATTACH THEM TO THE USER MAIN MENU WHEN CREATED
//		UserMainMenu mainUI = new UserMainMenu();
//		mainUI.mainMenu();
	}
	
	// The UI for the user login page where they enter their credentials
	private void loginUser()
	{
		// Input variables
		String username, password;
		Scanner sc = new Scanner(System.in);

		CredentialsManager credMan = new CredentialsManager();
		
		// Loop for selecting a valid username to check
		while(true)
		{
			System.out.println("Enter username: ");
			// Get user input
			username = sc.nextLine();
			
			// The username cannot be empty, contain spaces, and it must exist
			if(username.equals("") || username.contains(" ") || !credMan.userExists(username))
			{
				System.out.println("Username doesn't exist, please try again.");
				continue;
			}
			break;
		}
		
		// Loop for selecting a valid password to check
		while(true)
		{
			System.out.println("Enter password: ");
			// Get user input
			password = sc.nextLine();
			
			// The password cannot be empty or contain spaces
			if(password.equals("") || password.contains(" "))
			{
				System.out.println("Incorrect password, please try again.");
				continue;
			}
			else
			{
				// If the user credentials are valid
				if(credMan.verifyUser(username, password))
				{
					System.out.println("Successful User Login.");
					UserMainMenu mainUI = new UserMainMenu(username);
					mainUI.mainMenu();
				}
				else
				{
					System.out.println("Incorrect password, please try again.");
					continue;
				}
			}
			break;
		}
	}
	
	// The UI for the admin login page where they enter their credentials
	private void loginAdmin()
	{
		// Input variables
		String username, password;
		Scanner sc = new Scanner(System.in);

		CredentialsManager credMan = new CredentialsManager();
		
		// Loop for selecting a valid username to check
		while(true)
		{
			System.out.println("Enter username: ");
			// Get user input
			username = sc.nextLine();
			
			// The username cannot be empty, contain spaces, and it must exist
			if(username.equals("") || username.contains(" ") || !credMan.adminExists(username))
			{
				System.out.println("Username doesn't exist, please try again.");
				continue;
			}
			break;
		}
		
		// Loop for selecting a valid password to check
		while(true)
		{
			System.out.println("Enter password: ");
			// Get user input
			password = sc.nextLine();
			
			// The password cannot be empty or contain spaces
			if(password.equals("") || password.contains(" "))
			{
				System.out.println("Incorrect password, please try again.");
				continue;
			}
			else
			{
				// If the user credentials exist
				if(credMan.verifyAdmin(username, password))
				{
					System.out.println("Successful Admin Login.");
					AdminMainMenu mainUI = new AdminMainMenu();
					mainUI.mainMenu();
				}
				else
				{
					System.out.println("Incorrect password, please try again.");
					continue;
				}
			}
			break;
		}
	}
}