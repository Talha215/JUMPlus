package jumpPlusProject3.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jumpPlusProject3.model.Teacher;
import jumpPlusProject3.util.Colors;

public class GradeBookController {
	private static List<Teacher> teachers;
	private static Teacher currentUser;
	private static Scanner input = new Scanner(System.in);
	
	public static void run() {
		currentUser = null;
		teachers = new ArrayList<Teacher>();
		loginMenu();
		mainMenu();
	}

	private static void loginMenu() {
		int option = 0;
		
		do {
			System.out.println(Colors.PURPLE);
			System.out.println("+---------------+");
			System.out.println("| Teacher Login |");
			System.out.println("+---------------+");
			System.out.println(Colors.RESET);
			
			System.out.println("1. Create New Account");
			System.out.println("2. Login");
			System.out.println("3. Exit\n");

			System.out.print(Colors.GREEN + "Enter Choice (1, 2, or 3): " + Colors.YELLOW);
			try {
				option = Integer.parseInt(input.nextLine().trim());
			} catch (Exception e) {
				System.out.println(Colors.RED + "Not a valid choice. Try again." + Colors.RESET);
				continue;
			}
			if (option < 0 || option > 3)
				System.out.println(Colors.RED + "Not a valid choice. Try again." + Colors.RESET);

		} while (option <= 0 || option > 3);

		switch (option) {
		case 1:
			createAccount();
			break;
		case 2:
			login();
			break;
		case 3:
			exit();
		default:
			System.out.println("User is null.");
		}
	}
	
	private static void exit() {
		input.close();
		System.exit(0);
	}

	private static void login() {
		System.out.println(Colors.PURPLE);
		System.out.println("+---------------------+");
		System.out.println("| Enter Login Details |");
		System.out.println("+---------------------+");
		System.out.println(Colors.RESET);
		System.out.print("Username: " + Colors.GREEN);
		String username = input.nextLine().trim();
		System.out.print(Colors.RESET + "Password: " + Colors.GREEN);
		String password = input.nextLine().trim();
		System.out.print(Colors.RESET);

		Teacher found = teachers.stream().filter(e -> e.getUsername().equals(username)).findFirst().orElse(null);
		if (found != null && found.getPassword().equals(password)) {
			currentUser = found;
			System.out.println(Colors.GREEN + "Thanks for logging in!\n" + Colors.RESET);
		}
		else { // either username not found or password incorrect
			System.out.println(Colors.RED + "Invalid Credentials." + Colors.RESET);
			loginMenu();
		}
	}

	private static void createAccount() {
		System.out.println(Colors.PURPLE);
		System.out.println("+-----------------------+");
		System.out.println("| Enter Account Details |");
		System.out.println("+-----------------------+");
		System.out.println(Colors.RESET);
		
		System.out.print("Username: " + Colors.YELLOW);
		String username = input.nextLine().trim();
		
		String password, password2;
		do {
			System.out.print(Colors.RESET + "Password: " + Colors.YELLOW);
			password = input.nextLine().trim();
			
			System.out.print(Colors.RESET + "Password Confirmation: " + Colors.YELLOW);
			password2 = input.nextLine().trim();
			
			if(!password.equals(password2)) {
				System.out.println(Colors.RED + "Passwords do not match! Try again." + Colors.RESET);
			}
		} while(!password.equals(password2));

		currentUser = new Teacher(username, password);
		teachers.add(currentUser);

		System.out.println(Colors.CYAN + "Account created.\n" + Colors.RESET);
	}

	private static void mainMenu() {
		// TODO Auto-generated method stub
		
	}

}
