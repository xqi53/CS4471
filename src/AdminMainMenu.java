//this class navigate through the menu for admins who can see other people's recent data
//xuanran qi
import java.util.Scanner;

public class AdminMainMenu {
	String queryUsername;
	String userInput;
	String category;
	int menuStatus = 0;	//counter int manages menu when needed
	String[] categoryConsts = {"hydration", "exercise", "alcohol", "sleep", "fruit&veggie", "calorie", "smoking"};	//universal category string pass parameters, narges check this please
	String adminTitle = "\t\t $$$$$$\\  $$$$$$$\\  $$\\      $$\\ $$$$$$\\ $$\\   $$\\       $$\\      $$\\ $$$$$$$$\\ $$\\   $$\\ $$\\   $$\\ \n"
			+ "\t\t$$  __$$\\ $$  __$$\\ $$$\\    $$$ |\\_$$  _|$$$\\  $$ |      $$$\\    $$$ |$$  _____|$$$\\  $$ |$$ |  $$ |\n"
			+ "\t\t$$ /  $$ |$$ |  $$ |$$$$\\  $$$$ |  $$ |  $$$$\\ $$ |      $$$$\\  $$$$ |$$ |      $$$$\\ $$ |$$ |  $$ |\n"
			+ "\t\t$$$$$$$$ |$$ |  $$ |$$\\$$\\$$ $$ |  $$ |  $$ $$\\$$ |      $$\\$$\\$$ $$ |$$$$$\\    $$ $$\\$$ |$$ |  $$ |\n"
			+ "\t\t$$  __$$ |$$ |  $$ |$$ \\$$$  $$ |  $$ |  $$ \\$$$$ |      $$ \\$$$  $$ |$$  __|   $$ \\$$$$ |$$ |  $$ |\n"
			+ "\t\t$$ |  $$ |$$ |  $$ |$$ |\\$  /$$ |  $$ |  $$ |\\$$$ |      $$ |\\$  /$$ |$$ |      $$ |\\$$$ |$$ |  $$ |\n"
			+ "\t\t$$ |  $$ |$$$$$$$  |$$ | \\_/ $$ |$$$$$$\\ $$ | \\$$ |      $$ | \\_/ $$ |$$$$$$$$\\ $$ | \\$$ |\\$$$$$$  |\n"
			+ "\t\t\\__|  \\__|\\_______/ \\__|     \\__|\\______|\\__|  \\__|      \\__|     \\__|\\________|\\__|  \\__| \\______/\n"	//contains "admin menu" ascii art
			+ "\t\t-------------------------------------------------------------------------------------------------------";
	String dataHistoryTitle = "\t\t ____    ___  ______  ___     __  __ __  __  ______   ___   ____  _  _\n"
			+ "\t\t || \\\\  // \\\\ | || | // \\\\    ||  || || (( \\ | || |  // \\\\  || \\\\ \\\\//\n"
			+ "\t\t ||  )) ||=||   ||   ||=||    ||==|| ||  \\\\    ||   ((   )) ||_//  )/ \n"
			+ "\t\t ||_//  || ||   ||   || ||    ||  || || \\_))   ||    \\\\_//  || \\\\ //\n"
			+ "\t\t-------------------------------------------------------------------------";//contains "data history" ascii art
	
	public AdminMainMenu() {
	}
	
	//this method is initiated first
	public void mainMenu() {
		Scanner scanner = new Scanner(System.in);
		printAdminMenu();
		while(menuStatus != -1) {			//menuStatus of -1 indicating quit, menuStatus is initialized to 0 to ensure loop running
			userInput = scanner.nextLine();
			if(userInput.equals("0")) {
				menuStatus = -1;
				break;
			} else if(userInput.equals("1")) {
				menuStatus = 1;
				dataHistoryMenu(scanner);
			}
			printAdminMenu();
		}
		scanner.close();
		System.exit(0);
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
					int categoryInt = userInputInt-1;
					category = categoryConsts[categoryInt];
					clearScreen();
					System.out.println(dataHistoryTitle);			//for admin, ask for user's name
					System.out.println("\t\tPlease enter the username for query:");
					queryUsername = scanner.nextLine();
					DataHistoryGraph dataHistoryGraph = new DataHistoryGraph(category, queryUsername);
					dataHistoryGraph.printHistoryGraph(scanner);
					break;
				} else if(userInputInt == 0) {
					break;
				}
			} catch (NumberFormatException e) {
			}
		}
	}
	
	private void printAdminMenu() {
		clearScreen();
		System.out.println(adminTitle);
		System.out.println("\t\t1. View a user's recent records");
		System.out.println("\t\t0. Quit");
	}
	
	private void clearScreen() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
}
