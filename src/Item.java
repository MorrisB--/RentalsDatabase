import java.sql.*;
import java.util.Scanner;

public class Item extends ConnectionToDatabase {

	public void createItem(String name, double price, int lateFee) {

		String insertItem = "INSERT INTO Item VALUES(" + name + ", " + price + ", " + lateFee + ");";

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

	public void addStock() {

		Scanner keyboard = new Scanner(System.in);
		System.out.print("\nWhat is the name of the item? ");
		String name = keyboard.next();
		System.out.print("What is the store ID? ");
		String storeId = keyboard.next();
		System.out.print("How many of the items are you adding? ");
		String count = keyboard.next();
		keyboard.close();
		
		String incStock = "UPDATE ITEM SET store" + storeId + " = store" + storeId + " + " + count + " WHERE name = "
				+ name + ";";

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

	public void subStock(String name, String storeId, int count) {

		String decStock = "UPDATE ITEM SET store" + storeId + " = store" + storeId + " - " + count + " WHERE name = "
				+ name + ";";

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(decStock);
			System.out.println(name + " has been succesfully subtracted from the system.");

		} catch (

		SQLException e)

		{
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}

	}
}
