package jumpPlusProject2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import jumpPlusProject2.model.Movie;
import jumpPlusProject2.model.User;
import jumpPlusProject2.utility.Colors;

public class MovieRatingController {
	private static Scanner input = new Scanner(System.in);
	private static User currentUser;
	private static Map<String, Movie> movies;
	private static List<User> allUsers;
	
	
	private static void initMovies() {
		movies = new HashMap<String, Movie>();
		movies.put("Jaws", new Movie("Jaws"));
		movies.put("The Force Awakens", new Movie("The Force Awakens"));
		movies.put("Black Panther", new Movie("Black Panther"));
		movies.put("Mario", new Movie("Mario"));
		movies.put("The Shawshank Redemption", new Movie("The Shawshank Redemption"));
		movies.put("The Godfather", new Movie("The Godfather"));
		movies.put("The Dark Knight", new Movie("The Dark Knight"));
		movies.put("12 Angry Men", new Movie("12 Angry Men"));
		movies.put("Pulp Fiction", new Movie("Pulp Fiction"));
	}
	
	public static void run() {
		initMovies();
		allUsers = new ArrayList<User>();
		
		loginMenu();
		
		mainMenu();
	}
	
	private static void exit() {
		System.out.println(Colors.GREEN + "Goodbye!" + Colors.RESET);		
		input.close();
		System.exit(0);
	}

	private static void loginMenu() {
		int option = 0;

		do {
			System.out.println(Colors.PURPLE);
			System.out.println("+----------------+");
			System.out.println("| Movie Ratings! |");
			System.out.println("+----------------+");
			System.out.println(Colors.RESET);

			printAllMovies();
			
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

	private static void login() {
		System.out.println(Colors.PURPLE);
		System.out.println("+---------------------+");
		System.out.println("| Enter Login Details |");
		System.out.println("+---------------------+");
		System.out.println(Colors.RESET);
		System.out.print("Email: " + Colors.GREEN);
		String email = input.nextLine().trim();
		System.out.print(Colors.RESET + "Password: " + Colors.GREEN);
		String password = input.nextLine().trim();
		System.out.print(Colors.RESET);

		User found = allUsers.stream().filter(e -> e.getEmail().equals(email)).findFirst().orElse(null);
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
		
		String regex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		String email = "";
		
		do {
			System.out.print("Email: " + Colors.YELLOW);
			email = input.nextLine().trim();
			
			if(!pattern.matcher(email).matches())
				System.out.println(Colors.RED + "Email must be valid. Try again." + Colors.RESET);
		} while(!pattern.matcher(email).matches());
		
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

		currentUser = new User(email, password);
		allUsers.add(currentUser);

		System.out.println(Colors.CYAN + "Account created.\n" + Colors.RESET);
	}
	
	
	private static void mainMenu() {
		System.out.println(Colors.PURPLE);
		System.out.println("+----------------+");
		System.out.println("| Movie Ratings! |");
		System.out.println("+----------------+");
		System.out.println(Colors.RESET);
		
		if(currentUser.getUserRatings().isEmpty())
			System.out.println(Colors.YELLOW + "You have no ratings!\n" + Colors.RESET);
		else {
			System.out.println(Colors.GREEN + "Your Movie Ratings:");
			currentUser.printUserRatings();
			System.out.println(Colors.RESET);
		}
		
		printAllMovies();
		
		boolean found = false;
		do {
			System.out.print(Colors.RESET + "Enter Movie To Rate ('q' to log out):" + Colors.YELLOW);
			String movieTitle = input.nextLine().trim();
			
			if(movieTitle.equalsIgnoreCase("q")) {
				currentUser = null;
				loginMenu();
				
				if(currentUser == null)
					exit();
				
				mainMenu();
				return;
			}
			System.out.println(Colors.RESET);
			
			found = movies.containsKey(movieTitle);
			
			if(found) {
				double rating = -1;
				
				do {
					System.out.print(Colors.RESET + "Enter Rating (0-5):" + Colors.YELLOW);
					try {
						rating = Double.parseDouble(input.nextLine().trim());
					} catch (Exception e) {
						System.out.println(Colors.RED + "Not a valid choice. Try again." + Colors.RESET);
						continue;
					}
					
					if(rating < 0 || rating > 5)
						System.out.println(Colors.RED + "Rating must be between 0 and 5. Try again." + Colors.RESET);
				} while(rating < 0 || rating > 5);
				System.out.println(Colors.RESET);
				
				currentUser.addRating(movies.get(movieTitle), rating);
			}
			else {
				System.out.println(Colors.RED + "Invalid Movie Selected. Try Again" + Colors.RESET);
			}
				
		} while(!found);
		
		mainMenu();
	}

	private static void printAllMovies() {
		System.out.print(Colors.GREEN);
		System.out.printf("%-30s\t%s\t%s%n", "Title", "Average Rating", "Number of Ratings");
		System.out.print(Colors.CYAN);
		movies.forEach((title, movie) -> {
			int movieNumRatings = movie.getNumRatings();
			String movieAvgRating = movieNumRatings > 0 ? String.valueOf(movie.getAvgRating()) : "N/A";
			System.out.printf("%-30s\t%s\t\t%s%n", title, movieAvgRating, movieNumRatings);
		});
		System.out.println(Colors.RESET + "\n");
	}

}
