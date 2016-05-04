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
		System.out.print("What is the return date? (YYYY-MM-DD) ");
		String returnDate = keyboard.next();
		keyboard.close();

		if (isItemAvailable(itemName, storeId)) {
			String rent = "INSERT INTO Rents VALUES(" + customerId + ", '" + itemName + "', " + storeId + ", '"
					+ returnDate + "');";

			try {
				subStock(itemName, storeId, 1);
				Statement statement = connection.createStatement();
				statement.executeUpdate(rent);
				System.out.println("\n" + itemName + " has succesfully been rented.");

			} catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("SQLException: " + e.getErrorCode());
			}
		} else
			System.out.println("Item not rented, that item is not in stock.");

	}

	public static void returning() {

		Scanner keyboard = new Scanner(System.in);
		System.out.print("\nWhat is the customers ID? ");
		int customerId = keyboard.nextInt();
		System.out.print("What is the store ID? ");
		int storeId = keyboard.nextInt();
		System.out.print("What is the item name? ");
		String itemName = keyboard.next();
		keyboard.close();

		String delete = "DELETE FROM Rents WHERE customerId = " + customerId + " AND storeId = " + storeId
				+ " AND itemName = '" + itemName + "' LIMIT 1;";

		try {

			checkIfLate(customerId, storeId, itemName);

			Statement statement = connection.createStatement();
			statement.executeUpdate(delete);
			addStock(itemName, storeId, 1);
			System.out.println(itemName + " has succesfully been returned.");

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}
	}

	private static void checkIfLate(int customerId, int storeId, String itemName) {

		String findDate = "SELECT * FROM Rents WHERE customerId = " + customerId + " AND storeId = " + storeId
				+ " AND itemName = '" + itemName + "' LIMIT 1;";
		try {
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(findDate);
			resultSet.next();
			String returnDate = resultSet.getString("returnDate");

			String[] date = returnDate.split("-");

			// consider turning this into its own method
			long newReturnDate = (Long.parseLong(date[0]) * 365 * 24 * 60 * 60 * 1000)
					+ (Long.parseLong(date[1]) * 30 * 24 * 60 * 60 * 1000)
					+ (Long.parseLong(date[2]) * 24 * 60 * 60 * 1000);
			long epoch = 62125920000000L;
			newReturnDate -= epoch;
			long today = System.currentTimeMillis();
			System.out.println("For: "+ returnDate + " The returnDate is: " + newReturnDate + "\nTodays date is: " + today);
			long difference = (today - newReturnDate) / 1000 / 60 / 60 / 24;
			System.out.println("The difference is: " + difference);

			if (difference > 0) {
				String findFees = "SELECT * FROM Item WHERE name = '" + itemName + "' LIMIT 1;";
				resultSet = statement.executeQuery(findFees);
				resultSet.next();
				double fees = Double.parseDouble(resultSet.getString("lateFee"));
				Customer.addFees(customerId, fees);
			}

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}
	}

}
