import java.sql.*;
import java.util.*;

public class Item extends ConnectionToDatabase {

	public static void createItem() {

		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		System.out.print("What is the name of the item? ");
		String name = keyboard.nextLine();
		System.out.print("What is the price of the item? ");
		String price = keyboard.next();
		System.out.print("What is the late fee for the item? ");
		String lateFee = keyboard.next();

		String insertItem = "INSERT INTO Item (name, price, lateFee) VALUES('" + name + "', " + price + ", " + lateFee
				+ ");";

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(insertItem);
			System.out.println(name + " has been succesfully added to the system.");

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}

	}

	public static void addStock() {

		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		System.out.print("\nWhat is the name of the item? ");
		String name = keyboard.nextLine();
		System.out.print("What is the store ID? ");
		String storeId = keyboard.nextLine();
		System.out.print("How many of the items are you adding? ");
		String count = keyboard.nextLine();

		String incStock = "UPDATE Item SET count_store" + storeId + " = count_store" + storeId + " + " + count
				+ " WHERE name = '" + name + "';";

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(incStock);
			System.out.println(name + " has been succesfully added to the system.");

		} catch (

		SQLException e)

		{
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}

	}

	public static void addStock(String name, String storeId, String count) {

		String incStock = "UPDATE Item SET count_store" + storeId + " = count_store" + storeId + " + " + count
				+ " WHERE name = '" + name + "';";

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(incStock);

		} catch (

		SQLException e)

		{
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}

	}

	public static void subStock() {

		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		System.out.print("\nWhat is the name of the item? ");
		String name = keyboard.nextLine();
		System.out.print("What is the store ID? ");
		String storeId = keyboard.nextLine();
		System.out.print("How many of the items are you removing? ");
		String count = keyboard.nextLine();

		String decStock = "UPDATE Item SET count_store" + storeId + " = count_store" + storeId + " - " + count
				+ " WHERE name = '" + name + "';";

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(decStock);
			System.out.println(name + " has been succesfully subtracted from the system.");

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}

	}

	public static void subStock(String name, String storeId, int count) {

		String decStock = "UPDATE Item SET count_store" + storeId + " = count_store" + storeId + " - " + count
				+ " WHERE name = '" + name + "';";

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(decStock);

		} catch (

		SQLException e)

		{
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}

	}

	public static boolean isItemAvailable(String itemName, String storeId) {

		String select = "SELECT count_store" + storeId + " FROM Item WHERE name = '" + itemName + "';";

		try {

			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(select);
			result.next();
			int count = Integer.parseInt(result.getString("count_store" + storeId));

			if (count > 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}

		return false;

	}

	public static void listItems() {
		String select = "SELECT * FROM Item";

		try {

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(select);

			System.out.println("name, price, lateFee");
			while (resultSet.next()) {
				String column = resultSet.getString("name") + ", " + resultSet.getString("price") + ", "
						+ resultSet.getString("lateFee");
				System.out.println(column);
			}

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}
	}
}
