import java.sql.*;

public class Store extends ConnectionToDatabase {

	public void createStore(String location, String name) {

		String createStore = "INSERT INTO Store VALUES (" + (getRows("Store") + 1) + ", " + location + ", " + name
				+ ");";
		String addStore = "ALTER TABLE Item ADD " + ("StoreId" + getRows("Store")) + " INT DEFAULT 0;";

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(createStore);
			statement.executeUpdate(addStore);
			System.out.println(name + " at " + location + " has been succesfully added to the system.");

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}

	}

}
