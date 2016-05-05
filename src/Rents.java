import java.sql.*;
import java.util.Scanner;

/**
 * All the methods needed to properly user the Rents table.
 * 
 * @author Morris Ballenger
 * @version 1.0
 *
 */
public class Rents extends ConnectionToDatabase{

	public static void renting() {

		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		System.out.print("What is the customers ID? ");
		String customerId = keyboard.nextLine();
		System.out.print("What is the store ID? ");
		String storeId = keyboard.nextLine();
		System.out.print("What is the item name? ");
		String itemName = keyboard.nextLine();
		System.out.print("What is the return date? (YYYY-MM-DD) ");
		String returnDate = keyboard.nextLine();

		if (Item.isItemAvailable(itemName, storeId)) {
			String rent = "INSERT INTO Rents VALUES(" + customerId + ", '" + itemName + "', " + storeId + ", '"
					+ returnDate + "');";

			try {
				Item.subStock(itemName, storeId, 1);
				Statement statement = connection.createStatement();
				statement.executeUpdate(rent);
				System.out.println("\n" + itemName + " has succesfully been rented.");

			} catch (SQLException e) {
				System.out.println("Please try again and enter valid information.");

			}
		} else
			System.out.println("Item not rented, that item is not in stock.");

	}

	public static void returning() {

		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		System.out.print("\nWhat is the customers ID? ");
		String customerId = keyboard.nextLine();
		System.out.print("What is the store ID? ");
		String storeId = keyboard.nextLine();
		System.out.print("What is the item name? ");
		String itemName = keyboard.nextLine();

		String delete = "DELETE FROM Rents WHERE customerId = " + customerId + " AND storeId = " + storeId
				+ " AND itemName = '" + itemName + "' LIMIT 1;";

		try {

			checkIfLate(customerId, storeId, itemName);

			Statement statement = connection.createStatement();
			statement.executeUpdate(delete);
			Item.addStock(itemName, storeId, "1");
			System.out.println(itemName + " has succesfully been returned.");

		} catch (SQLException e) {
			System.out.println("Please try again and enter valid information.");

		}
	}

	private static void checkIfLate(String customerId, String storeId, String itemName) {

		String findDate = "SELECT * FROM Rents WHERE customerId = " + customerId + " AND storeId = " + storeId
				+ " AND itemName = '" + itemName + "' LIMIT 1;";
		String currentDate = "SELECT NOW();";
		
		try {
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(findDate);
			resultSet.next();
			String returnDate = resultSet.getString("returnDate");
			String[] splitReturnDate = returnDate.split("-");
			
			resultSet = statement.executeQuery(currentDate);
			resultSet.next();
			String dateNow = resultSet.getString("now()");
			System.out.println("The dateNow is " + dateNow);
			dateNow = dateNow.substring(0, 10);
			String splitDateNow[] = dateNow.split("-");
			
			int difference = ((Integer.parseInt(splitDateNow[0])) - (Integer.parseInt(splitReturnDate[0]))) * 365
					+ ((Integer.parseInt(splitDateNow[1])) - (Integer.parseInt(splitReturnDate[1]))) * 30
					+ ((Integer.parseInt(splitDateNow[2])) - (Integer.parseInt(splitReturnDate[2])));
			
			System.out.println("The difference is: " + difference);

			if (difference > 0) {
				String findFees = "SELECT * FROM Item WHERE name = '" + itemName + "' LIMIT 1;";
				resultSet = statement.executeQuery(findFees);
				resultSet.next();
				String fees = (Double.parseDouble(resultSet.getString("lateFee")) * difference) + "";
				Customer.addFees(customerId, fees);
			}

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}
	}

	public static void listRentals(){
		
		String select = "SELECT * FROM Rents";

		try {

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(select);

			System.out.println("customerId, itemName, storeId, returnDate");
			while (resultSet.next()) {
				String column = resultSet.getString("customerId") + ", " + resultSet.getString("itemName") + ", "
						+ resultSet.getString("storeId") + ", " + resultSet.getString("returnDate");
				System.out.println(column);
			}

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}
	}
}
