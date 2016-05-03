import java.sql.*;
import java.util.Scanner;

public class Item extends ConnectionToDatabase {

	public static void createItem() {

		Scanner keyboard = new Scanner(System.in);
		System.out.print("\nWhat is the name of the item? ");
		String name = keyboard.next();
		System.out.print("What is the price of the item? ");
		String price = keyboard.next();
		System.out.print("What is the late fee for the item? ");
		String lateFee = keyboard.next();
		keyboard.close();

		/*
		 * (name, price, lateFee) is needed because all other columns will use
		 * their default values
		 */
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

		Scanner keyboard = new Scanner(System.in);
		System.out.print("\nWhat is the name of the item? ");
		String name = keyboard.next();
		System.out.print("What is the store ID? ");
		String storeId = keyboard.next();
		System.out.print("How many of the items are you adding? ");
		String count = keyboard.next();
		keyboard.close();

		String incStock = "UPDATE Item SET storeId" + storeId + " = storeId" + storeId + " + " + count
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

	public static void addStock(String name, int storeId, int count) {

		String incStock = "UPDATE Item SET storeId" + storeId + " = storeId" + storeId + " + " + count
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

		Scanner keyboard = new Scanner(System.in);
		System.out.print("\nWhat is the name of the item? ");
		String name = keyboard.next();
		System.out.print("What is the store ID? ");
		int storeId = keyboard.nextInt();
		System.out.print("How many of the items are you removing? ");
		int count = keyboard.nextInt();
		keyboard.close();

		String decStock = "UPDATE Item SET storeId" + storeId + " = storeId" + storeId + " - " + count + " WHERE name = '"
				+ name + "';";

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

	public static void subStock(String name, int storeId, int count) {

		String decStock = "UPDATE Item SET storeId" + storeId + " = storeId" + storeId + " - " + count
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

	public static boolean isItemAvailable(String itemName, int storeId) {

		String select = "SELECT StoreId" + storeId + " FROM Item WHERE name = '" + itemName + "';";

		try {

			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(select);
			result.next();
			int count = Integer.parseInt(result.getString("StoreId" + storeId));

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
}
