import java.sql.*;

public class Rents extends Item {

	public void rent(String customerId, String storeId, String itemName, String returnDate) {

		String rent = "INSERT INTO Rents VALUES(" + itemName + ", " + returnDate + ", " + customerId + ", " + storeId
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
