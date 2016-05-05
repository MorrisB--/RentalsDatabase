import java.sql.*;
import java.util.*;

public class ConnectionToDatabase {

	static Connection connection = null;

	public static boolean connectToDatabase() {

		try {

			@SuppressWarnings("resource")
			Scanner keyboard0 = new Scanner(System.in);
			System.out.print("Enter your username: ");
			String username = keyboard0.next();

			System.out.print("Enter your password: ");
			String password = keyboard0.next();

			connection = DriverManager.getConnection("jdbc:mysql://cs.neiu.edu:3306/db_Spr16_" + username + "?user="
					+ username + "&password=" + password);

			System.out.println("Successfully connected to database.");
			return true;

		} catch (SQLException e) {
			System.out.println("Invalid username or password. Restart program and try again.");
		}

		return false;
	}

	public static void main(String[] args) {

		if (connectToDatabase()) {
			Scanner keyboard = new Scanner(System.in);
			boolean exit = false;
			do {
				System.out.print("\nWhat would you like to do?\n\n1) List customers\n2) List rentals\n"
						+ "3) List items\n4) List stores\n5) Register a customer\n"
						+ "6) Renting to customer\n7) Returning from customer \n"
						+ "8) Customer paying fees\n9) Create store\n10) Create new item\n11) Stock item\n"
						+ "12) Remove stock\n13) Exit\nChoice: ");
				int choice = keyboard.nextInt();
				System.out.println();

				switch (choice) {
				case 1:
					Customer.listCustomers();
					break;
				case 2:
					Rents.listRentals();
					break;
				case 3:
					Item.listItems();
					break;
				case 4:
					Store.listStores();
					break;
				case 5:
					Customer.createCustomer();
					break;
				case 6:
					Rents.renting();
					break;
				case 7:
					Rents.returning();
					break;
				case 8:
					Customer.subtractFees();
					break;
				case 9:
					Store.createStore();
					break;
				case 10:
					Item.createItem();
					break;
				case 11:
					Item.addStock();
					break;
				case 12:
					Item.subStock();
					break;
				case 13:
					exit = true;
					System.out.println("You are logged out.");
					break;
				default:
					System.out.println("Please choose a valid number");
				}
			} while (!exit);

			keyboard.close();
		}
	}

	public static int getRows(String tableName) {

		int rows = 0;
		String selectCount = "SELECT * FROM " + tableName;

		try {

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectCount);

			while (rs.next())
				rows++;

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}

		return rows;
	}
}
