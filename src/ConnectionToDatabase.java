import java.sql.*;
import java.util.*;

public class ConnectionToDatabase {

	static Connection connection = null;

	public static void connectToDatabase() {

		try {

			@SuppressWarnings("resource")
			// keyboard will be closed in the main method
			Scanner keyboard0 = new Scanner(System.in);
			System.out.print("Enter your username: ");
			String username = keyboard0.next();

			System.out.print("Enter your password: ");
			String password = keyboard0.next();

			connection = DriverManager.getConnection("jdbc:mysql://cs.neiu.edu:3306/db_Spr16_" + username + "?user="
					+ username + "&password=" + password);

			System.out.println("Successfully connected to database.\n");
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}

	}

	public static void main(String[] args) {

		connectToDatabase();
		Scanner keyboard = new Scanner(System.in);

		System.out.println(
				"What would you like to do?\n\n1) Register a customer\n2) Add a new store\n3) Create a new item\n4) Add items to a store\n5) Renting to customer\n6) Returning from customer\n7) Customer paying fees \n8) Remove items from store");
		int choice = keyboard.nextInt();

		switch (choice) {
		case 1:
			Customer.createCustomer();
			break;
		case 2:
			Store.createStore();
			break;
		case 3:
			Item.createItem();
			break;
		case 4:
			Item.addStock();
			break;
		case 5:
			Rents.renting();
			break;
		case 6:
			Rents.returning();
			break;
		case 7:
			Customer.subtractFees();
			break;
		case 8:
			Item.subStock();
			break;
		default:
			System.out.println("Please choose a valid number");
		}

		keyboard.close();
		// Connection connection = null;

		// try {
		/*
		 * Scanner keyboard = new Scanner(System.in); System.out.println(
		 * "Enter your username: "); String username = keyboard.next();
		 * 
		 * System.out.println("Enter your password: "); String password =
		 * keyboard.next(); keyboard.close();
		 * 
		 * connection =
		 * DriverManager.getConnection("jdbc:mysql://cs.neiu.edu:3306/db_Spr16_"
		 * + username + "?user=" + username + "&password=" + password);
		 */
		// String sql1 = "INSERT INTO Breakfast VALUES (11, 'Syrup')";
		// String sql2 = "INSERT INTO Breakfast VALUES (12, 'Extra Salt')";
		// String sql3 = "DELETE FROM Breakfast WHERE id = 12";
		// String sql4 = "INSERT INTO Breakfast VALUES (12, 'Light Salt')";
		// String sql5 = "SELECT * FROM Breakfast";

		// Statement statement1 = connection.createStatement();
		// statement1.executeUpdate(sql1);
		// statement1.executeUpdate(sql2);
		// statement1.executeUpdate(sql3);
		// statement1.executeUpdate(sql4);

		// System.out.println("\n" + sql5 + "\nid name");
		// ResultSet rs = statement1.executeQuery(sql5);
		// while (rs.next()) {
		// String all = rs.getString("id") + " " + rs.getString("name");
		// System.out.println(all);
		// }
		/*
		 * } catch (SQLException e) {
		 * 
		 * System.out.println("SQLException: " + e.getMessage());
		 * System.out.println("SQLState: " + e.getSQLState());
		 * System.out.println("SQLException: " + e.getErrorCode()); }
		 */
	}

	public static int getRows(String tableName) {

		int rows = 0;
		String selectCount = "SELECT * FROM " + tableName;

		try {

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectCount);

			/*
			 * Is there a smarter way to this? Can I somehow extract the int
			 * from SELCT COUNT(*) FROM CUSTOMER NTS: Try casting ResultSet to
			 * Int
			 */
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
