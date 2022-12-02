//this class navigate through the menu for normal users
//Xuanran Qi
import java.util.Scanner;

public class UserMainMenu {
	String username;	//contains user name passed from the login process
	String userInput;
	String category;
	int menuStatus = 0;	//counter int manages menu when needed
	final String[] categoryConsts = {"hydration", "exercise", "alcohol", "sleep", "fruit&veggie", "calorie", "smoking"};	//universal category string pass parameters, 
	final String[] categoryUnitConsts = {"cups", "hours", "drinks", "hours", "pounds", "calories", "number of cigarettes"};	//for display only do not touch
	String menuTitle = "\t\t ___  ___  ___  __ __  __    ___  ___  ____ __  __ __ __\n"
			+ "\t\t ||\\\\//|| // \\\\ || ||\\ ||    ||\\\\//|| ||    ||\\ || || ||\n"
			+ "\t\t || \\/ || ||=|| || ||\\\\||    || \\/ || ||==  ||\\\\|| || ||\n"
			+ "\t\t ||    || || || || || \\||    ||    || ||___ || \\|| \\\\_//\n"	//contains "main menu" ascii art
			+ "\t\t------------------------------------------------------------";
	String dataRecordTitle = "\t\t ____   ____   ___   ___   ____  ____      ____    ___  ______  ___ \n"
			+ "\t\t || \\\\ ||     //    // \\\\  || \\\\ || \\\\     || \\\\  // \\\\ | || | // \\\\\n"
			+ "\t\t ||_// ||==  ((    ((   )) ||_// ||  ))    ||  )) ||=||   ||   ||=||\n"
			+ "\t\t || \\\\ ||___  \\\\__  \\\\_//  || \\\\ ||_//     ||_//  || ||   ||   || ||\n"
			+ "\t\t----------------------------------------------------------------------------";//contains "record data" ascii art
	String setGoalTitle = "\t\t  __   ____ ______      ___    ___    ___  __   \n"
			+ "\t\t (( \\ ||    | || |     // \\\\  // \\\\  // \\\\ ||   \n"
			+ "\t\t  \\\\  ||==    ||      (( ___ ((   )) ||=|| ||   \n"
			+ "\t\t \\_)) ||___   ||       \\\\_||  \\\\_//  || || ||__|\n"
			+ "\t\t-----------------------------------------------------";
	String dataHistoryTitle = "\t\t ____    ___  ______  ___     __  __ __  __  ______   ___   ____  _  _\n"
			+ "\t\t || \\\\  // \\\\ | || | // \\\\    ||  || || (( \\ | || |  // \\\\  || \\\\ \\\\//\n"
			+ "\t\t ||  )) ||=||   ||   ||=||    ||==|| ||  \\\\    ||   ((   )) ||_//  )/ \n"
			+ "\t\t ||_//  || ||   ||   || ||    ||  || || \\_))   ||    \\\\_//  || \\\\ //\n"
			+ "\t\t-------------------------------------------------------------------------";//contains "data history" ascii art
	
	public UserMainMenu(String inputUsername) {
		username = inputUsername;
	}
	
	//this method is initiated first
	public void mainMenu() {
		Scanner scanner = new Scanner(System.in);
		printUserMenu();
		FeedbackManager feedbackManager = new FeedbackManager();
		feedbackManager.reminder(username);
		while(menuStatus != -1) {			//menuStatus of -1 indicating quit, menuStatus is initialized to 0 to ensure loop running
			userInput = scanner.nextLine();
			if(userInput.equals("0")) {
				menuStatus = -1;
				break;
			} else if(userInput.equals("1")) {	//1 is for data record option
				menuStatus = 1;
				dataRecorderMenu(scanner);
			} else if(userInput.equals("2")) {	//2 is for setting goals
				menuStatus = 2;
				setGoalMenu(scanner);
			} else if(userInput.equals("3")) {
				menuStatus = 3;
				dataHistoryMenu(scanner);
			}
			printUserMenu();
		}
		scanner.close();
		System.exit(0);
	}
	
	private void dataRecorderMenu(Scanner scanner) {	//this method navigate for record data, and call DataRecorder class
		while(true) {
			clearScreen();
			System.out.println(setGoalTitle);
			System.out.println("\t\tPlease select category:");
			System.out.println("\t\t1. Hydration");
			System.out.println("\t\t2. Exercise");
			System.out.println("\t\t3. Alcohol Intake");
			System.out.println("\t\t4. Sleep Quality");
			System.out.println("\t\t5. Fruit and vegetable Intake");
			System.out.println("\t\t6. Calories Intake");
			System.out.println("\t\t7. Smoking");
			System.out.println("\t\t0. Previous Menu");
			userInput = scanner.nextLine();
			try {
				int userInputInt = Integer.parseInt(userInput);		//selection parsed to int for categoryConst to use for better robustness
				if(userInputInt > 0 && userInputInt <= 7) {
					clearScreen();
					int categoryInt = userInputInt-1;
					category = categoryConsts[categoryInt];
					System.out.println(setGoalTitle);
					System.out.println("\t\tPlease enter the amount in " + categoryUnitConsts[categoryInt]);
					userInput = scanner.nextLine();
					try {
						float userInputFloat = Float.parseFloat(userInput);
						DataRecorder dataRecorder = new DataRecorder();
						dataRecorder.addNewEntry(username, category, userInputFloat); 
						break;
					} catch (NumberFormatException e) {
						break;
					}
				} else if(userInputInt == 0) {
					break;
				}
			} catch (NumberFormatException e) {
			}
		}
	}
	
	private void setGoalMenu(Scanner scanner) {
		while(true) {
			clearScreen();
			System.out.println(dataRecordTitle);
			System.out.println("\t\tPlease select category:");
			System.out.println("\t\t1. Hydration");
			System.out.println("\t\t2. Exercise");
			System.out.println("\t\t3. Alcohol Intake");
			System.out.println("\t\t4. Sleep Quality");
			System.out.println("\t\t5. Fruit and vegetable Intake");
			System.out.println("\t\t6. Calories Intake");
			System.out.println("\t\t7. Smoking");
			System.out.println("\t\t0. Previous Menu");
			userInput = scanner.nextLine();
			try {
				int userInputInt = Integer.parseInt(userInput);		//selection parsed to int for categoryConst to use for better robustness
				if(userInputInt > 0 && userInputInt <= 7) {
					int categoryInt = userInputInt-1;
					category = categoryConsts[categoryInt];
					clearScreen();
					System.out.println(dataRecordTitle);
					System.out.println("\t\tPlease enter the amount in " + categoryUnitConsts[categoryInt]);
					userInput = scanner.nextLine();
					try {
						float userInputFloat = Float.parseFloat(userInput);
						GoalSetter goalSetter = new GoalSetter();
						goalSetter.replaceGoalInFile(username, category, userInputFloat);  
						break;
					} catch (NumberFormatException e) {
						break;
					}
				} else if(userInputInt == 0) {
					break;
				}
			} catch (NumberFormatException e) {
			}
		}
	}
	
	private void dataHistoryMenu(Scanner scanner) {
		while(true) {
			clearScreen();
			System.out.println(dataHistoryTitle);
			System.out.println("\t\tPlease select category:");
			System.out.println("\t\t1. Hydration");
			System.out.println("\t\t2. Exercise");
			System.out.println("\t\t3. Alcohol Intake");
			System.out.println("\t\t4. Sleep Quality");
			System.out.println("\t\t5. Fruit and vegetable Intake");
			System.out.println("\t\t6. Calories Intake");
			System.out.println("\t\t7. Smoking");
			System.out.println("\t\t0. Previous Menu");
			userInput = scanner.nextLine();
			try {
				int userInputInt = Integer.parseInt(userInput);		//selection parsed to int for categoryConst to use for better robustness
				if(userInputInt > 0 && userInputInt <= 7) {
					clearScreen();
					int categoryInt = userInputInt-1;
					category = categoryConsts[categoryInt];
					DataHistoryGraph dataHistoryGraph = new DataHistoryGraph(category, username);
					dataHistoryGraph.printHistoryGraph(scanner);
					break;
				} else if(userInputInt == 0) {
					break;
				}
			} catch (NumberFormatException e) {
			}
		}
	}
	
	private void printUserMenu() {
		clearScreen();
		System.out.println(menuTitle);
		System.out.println("\t\t1. New Entry");
		System.out.println("\t\t2. Set goals");
		System.out.println("\t\t3. View Your Recent Records");
		System.out.println("\t\t0. Quit");
	}
	
	private void clearScreen() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
}