/**
 * This class initiates the program by calling the LoginUI class to display the
 * UI and interact with the User
 * 
 * @author Justin Weller
 */
public class Main {


	// This is the main method used as the initializer of the program.
	public static void main(String[] args) {
		
		// Initiates the program by starting the LoginUI
		LoginUI init = new LoginUI();
		init.requestUserType();
	}
}
