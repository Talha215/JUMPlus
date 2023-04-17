package jumpPlusProject3.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jumpPlusProject3.model.Teacher;
import jumpPlusProject3.model.Class;
import jumpPlusProject3.util.Colors;

public class GradeBookController {
	private static Scanner input = new Scanner(System.in);
	private static List<Teacher> teachers = new ArrayList<Teacher>();
	private static Teacher currentUser;
	private static Class currentClass;
	
	public static void run() {
		currentUser = null;
		currentClass = null;
		loginMenu();
		chooseClass();
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

	private static void chooseClass() {
		printClasses();
		
		int option = 0;
		do {
			System.out.println("1. Add a Class");
			System.out.println("2. Select a Class");
			System.out.println("3. Log out\n");

			System.out.print(Colors.GREEN + "Enter Choice (1, 2, or 3): " + Colors.YELLOW);
			try {
				option = Integer.parseInt(input.nextLine().trim());
			} catch (Exception e) {
				System.out.println(Colors.RED + "Not a valid choice. Try again." + Colors.RESET);
				continue;
			}
			if (option < 0 || option > 3)
				System.out.println(Colors.RED + "Not a valid choice. Try again." + Colors.RESET);

		} while(option <= 0 || option > 3);
		
		switch(option) {
		case 1:
			addClass();
			chooseClass();
			break;
		case 2:
			if(currentUser.getClasses().isEmpty()) {
				System.out.println(Colors.RED + "There are no classes to choose from." + Colors.RESET);
				chooseClass();
			}
			else {
				selectClass();
				chooseClass();
			}
			break;
		case 3:
			run();
			break;
		}
	}

	private static void selectClass() {
		printClasses();
		
		Class found = null;
		do {
			System.out.print(Colors.GREEN + "Choose a class: " + Colors.YELLOW);
			String classChosen = input.nextLine();
			
			found = currentUser.getClasses().stream().filter(e -> e.getName().equals(classChosen)).findFirst().orElse(null);
			
			System.out.println(Colors.RESET);
			
			if(found != null)
				currentClass = found;
			else
				System.out.println(Colors.RED + "Not a valid class. Try again." + Colors.RESET);

		} while(found == null);
		
		System.out.println(Colors.GREEN + "You've selected " + currentClass.getName() + Colors.RESET);
		
		classActions();
		
		currentClass = null;
	}

	private static void classActions() {
		currentClass.printClass();
	}

	private static void addClass() {
		System.out.print(Colors.RESET + "Name of class to add:" + Colors.YELLOW);
		
		currentUser.addClass(input.nextLine().trim());
		
		System.out.print(Colors.RESET);
		
		chooseClass();
	}

	private static void printClasses() {
		System.out.println(Colors.PURPLE);
		System.out.println("+-----------------+");
		System.out.println("| List of Classes |");
		System.out.println("+-----------------+");
		System.out.println(Colors.CYAN);
		
		if(currentUser.getClasses().isEmpty())
			System.out.println(Colors.RED + "There are no classes to choose from." + Colors.RESET);
		
		currentUser.getClasses().stream().forEach((c) -> {
			System.out.println(c.getName());
		});
		System.out.println(Colors.RESET);
	}

}






















































