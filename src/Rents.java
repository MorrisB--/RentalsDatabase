import java.sql.*;
import java.util.Scanner;

public class Rents extends Item {

	public static void renting() {

		Scanner keyboard = new Scanner(System.in);
		System.out.print("\nWhat is the customers ID? ");
		int customerId = keyboard.nextInt();
		System.out.print("What is the store ID? ");
		int storeId = keyboard.nextInt();
		System.out.print("What is the item name? ");
		String itemName = keyboard.next();
		System.out.print("What is the return date? ");
		String returnDate = keyboard.next();
		keyboard.close();
		
		String rent = "INSERT INTO Rents VALUES('" + itemName + "', " + returnDate + ", " + customerId + ", " + storeId
				+ ");";

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(rent);
			subStock(itemName, storeId, 1);
			System.out.println(itemName + " has succesfully been rented.");

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}
	}

	public void returning(String customerId, String storeId, String itemName) {

		String delete = "DELETE FROM Rents WHERE customerId = " + customerId + " AND storeId = " + storeId
				+ " AND itemName = " + itemName + "LIMIT 1;";

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(delete);
			addStock(itemName, storeId, 1);
			System.out.println(itemName + " has succesfully been rented.");

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}
	}
 
}
