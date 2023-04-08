package jumpPlusProject1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jumpPlusProject1.model.Customer;

public class MainController {
	public static final String RESET = "\u001B[0m";
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";

	private static Scanner input = new Scanner(System.in);
	private static List<Customer> customers;
	private static Customer currentUser = null;

	public static void main(String[] args) {
		try {
			FileInputStream fileInput = new FileInputStream(new File("customers.data"));
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);

			customers = (List<Customer>) objectInput.readObject();

		} catch (Exception e) {
			customers = new ArrayList<>();
		}

		currentUser = loginMenu();
		System.out.println(YELLOW + "Thanks for logging in " + currentUser.getName());

		loggedInMenu();
	}

	public static Customer loginMenu() {
		int option = 0;
		Customer customer = currentUser;

		do {
			System.out.println(CYAN);
			System.out.println("+---------------------------+");
			System.out.println("| DOLLARSBANK Welcomes You! |");
			System.out.println("+---------------------------+");
			System.out.println(RESET);

			System.out.println("1. Create New Account");
			System.out.println("2. Login");
			System.out.println("3. Exit\n");

			System.out.print(GREEN + "Enter Choice (1, 2, or 3): " + RESET);
			try {
				option = Integer.parseInt(input.nextLine().trim());
			} catch (Exception e) {
				System.out.println(RED + "Not a valid choice. Try again." + RESET);
				continue;
			}
			if (option < 0 || option > 3)
				System.out.println(RED + "Not a valid choice. Try again." + RESET);

		} while (option <= 0 || option > 3);

		switch (option) {
		case 1:
			customer = createAccount();
			break;
		case 2:
			customer = login();
			break;
		case 3:
			exit();
		default:
			System.out.println("Customer is null.");
		}

		return customer;
	}

	private static void exit() {
		System.out.println(BLUE + "Goodbye!" + RESET);
		try {
			FileOutputStream fileOut = new FileOutputStream(new File("customers.data"));
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(customers);
			objectOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input.close();
		System.exit(0);
	}

	private static Customer login() {
		Customer customer = null;

		System.out.println(CYAN);
		System.out.println("+---------------------+");
		System.out.println("| Enter Login Details |");
		System.out.println("+---------------------+");
		System.out.println(RESET);
		System.out.print("User ID: " + GREEN);
		String id = input.nextLine().trim();
		System.out.print(RESET + "Password: " + GREEN);
		String password = input.nextLine().trim();
		System.out.print(RESET);

		Customer found = customers.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
		if (found != null && found.getPassword().equals(password))
			customer = found;
		else { // either username not found or password incorrect
			System.out.println(RED + "Invalid Credentials." + RESET);
			loginMenu();
		}

		return customer;
	}

	private static Customer createAccount() {
		Customer newCustomer;
		System.out.print("Customer Name: " + YELLOW);
		String name = input.nextLine().trim();
		System.out.print(RESET + "Customer Address: " + YELLOW);
		String address = input.nextLine().trim();
		System.out.print(RESET + "Customer Contact Number: " + YELLOW);
		String phone = input.nextLine().trim();
		System.out.print(RESET + "User ID: " + YELLOW);
		String id = input.nextLine().trim();
		System.out.print(RESET + "Password: " + YELLOW);
		String password = input.nextLine().trim();
		System.out.print(RESET + "Initial Deposit Amount: $" + YELLOW);
		double deposit = Double.parseDouble(input.nextLine().trim());
		System.out.print(RESET);

		newCustomer = new Customer(name, address, phone, id, password, deposit);
		customers.add(newCustomer);

		System.out.println(CYAN + "Account created." + RESET);

		return newCustomer;
	}

	private static void loggedInMenu() {
		while (true) {
			int option = 0;
			do {
				System.out.println(CYAN);
				System.out.println("+---------------------+");
				System.out.println("| WELCOME Customer!!! |");
				System.out.println("+---------------------+");
				System.out.println(RESET);

				System.out.println("1. Deposit Amount");
				System.out.println("2. Withdraw Amount");
				System.out.println("3. Funds Transfer");
				System.out.println("4. View 5 Recent Transactions");
				System.out.println("5. Display Customer Information");
				System.out.println("6. Sign Out");

				System.out.print(GREEN + "Enter Choice (1, 2, 3, 4, 5, or 6): " + RESET);
				try {
					option = Integer.parseInt(input.nextLine().trim());
				} catch (Exception e) {
					System.out.println(RED + "Not a valid choice. Try again." + RESET);
					continue;
				}
				if (option < 0 || option > 6)
					System.out.println(RED + "Not a valid choice. Try again." + RESET);

			} while (option < 0 || option > 6);

			switch (option) {
			case 1:
				double deposit = 0;
				do {
					System.out.print("Amount to deposit: $");
					try {
						deposit = Double.parseDouble(input.nextLine().trim());
					} catch (Exception e) {
						System.out.println(RED + "Not a valid input. Try again." + RESET);
						continue;
					}
					if (deposit <= 0)
						System.out.println(RED + "Please enter a positive number" + RESET);
				} while (deposit <= 0);

				currentUser.addTransaction(deposit);

				break;
			case 2:
				double withdraw = -1;
				do {
					System.out.print("Amount to withdraw: $");
					try {
						withdraw = Double.parseDouble(input.nextLine().trim());
					} catch (Exception e) {
						System.out.println(RED + "Not a valid input. Try again." + RESET);
						continue;
					}
					if (withdraw < 0)
						System.out.println(RED + "Please enter a positive number" + RESET);
					if (currentUser.getBalance() - withdraw < 0)
						System.out.println(RED + "Balance too low." + RESET);
				} while (withdraw < 0 || currentUser.getBalance() - withdraw < 0);

				currentUser.addTransaction(-1 * withdraw);
				break;
			case 3:
				Customer other = null;

				System.out.print("User ID to transfer to: ");
				String id = input.nextLine().trim();

				other = customers.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);

				if (other == null) {
					System.out.println(RED + "User ID Not Found." + RESET);
					break;
				}

				double transfer = 0;
				do {
					System.out.print("Amount to transfer: $");
					try {
						transfer = Double.parseDouble(input.nextLine().trim());
					} catch (Exception e) {
						System.out.println(RED + "Not a valid input. Try again." + RESET);
						continue;
					}
					if (transfer <= 0)
						System.out.println(RED + "Please enter a positive number" + RESET);
				} while (transfer <= 0);

				if (currentUser.getBalance() < transfer) {
					System.out.println(RED + "Balance is too low." + RESET);
					break;
				}

				// found other customer
				// you have enough funds
				// transaction can happen
				currentUser.addTransaction(-1 * transfer, other.getId());
				other.addTransaction(transfer, currentUser.getId());

				break;
			case 4:
				System.out.println(CYAN);
				System.out.println("+------------------------+");
				System.out.println("| 5 Recent Transactions: |");
				System.out.println("+------------------------+");
				System.out.println(RESET);

				System.out.print(YELLOW);
				currentUser.printTransactions();
				System.out.print(RESET);
				
				System.out.println(GREEN + "Balance: " + NumberFormat.getCurrencyInstance().format(currentUser.getBalance()) + RESET);
				break;
			case 5:
				System.out.println(YELLOW + currentUser + RESET);
				break;
			case 6:
				exit();
				break;
			}
		}
	}
}
