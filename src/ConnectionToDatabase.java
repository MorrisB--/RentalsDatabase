import java.sql.*;
import java.util.*;

/**
 * @author Morris Ballenger
 * @version 1.0
 */

public class ConnectionToDatabase {

	static Connection connection = null;

	public static boolean connectToDatabase() {

		try {

			@SuppressWarnings("resource")
			// Keyboard will be closed in main method
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
				System.out.print("\nWhat would you like to do?\n\n1) List customers\n2) List good customers\n3) List rentals\n"
						+ "4) List items\n5) List stores\n6) Register a customer\n"
						+ "7) Renting to customer\n8) Returning from customer \n"
						+ "9) Customer paying fees\n10) Create store\n11) Create new item\n12) Stock item\n"
						+ "13) Remove stock\n14) Exit\nChoice: ");
				int choice = keyboard.nextInt();
				System.out.println();

				switch (choice) {
				case 1:
					Customer.listCustomers();
					break;
				case 2:
					Customer.listGoodCustomers();
					break;
				case 3:
					Rents.listRentals();
					break;
				case 4:
					Item.listItems();
					break;
				case 5:
					Store.listStores();
					break;
				case 6:
					Customer.createCustomer();
					break;
				case 7:
					Rents.renting();
					break;
				case 8:
					Rents.returning();
					break;
				case 9:
					Customer.subtractFees();
					break;
				case 10:
					Store.createStore();
					break;
				case 11:
					Item.createItem();
					break;
				case 12:
					Item.addStock();
					break;
				case 13:
					Item.subStock();
					break;
				case 14:
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

	/**
	 * Takes any table and returns the number of rows starting at the top.
	 * 
	 * @param tableName
	 * @return the number of rows a table has
	 */
	public static int getRows(String tableName) {

		int rows = 0;
		String selectCount = "SELECT * FROM " + tableName;

		try {

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectCount);

			while (rs.next())
				rows++;

		} catch (SQLException e) {
			System.out.println("Please try again and enter valid information.");
		}

		return rows;
	}
}
