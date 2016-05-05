import java.sql.*;
import java.util.*;

/**
 * All the methods needed to properly user the Store table.
 * 
 * @author Morris Ballenger
 * @version 1.0
 *
 */
public class Store extends ConnectionToDatabase {

	public static void createStore() {

		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		System.out.print("What is the location of the store? ");
		String location = keyboard.nextLine();
		System.out.print("What is the name of the store? ");
		String name = keyboard.nextLine();
		
		String createStore = "INSERT INTO Store VALUES (" + (getRows("Store") + 1) + ", '" + location + "', '" + name
				+ "');";
		String addStore = "ALTER TABLE Item ADD " + ("count_store" + (getRows("Store")+1)) + " INT DEFAULT 0;";

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(createStore);
			statement.executeUpdate(addStore);
			System.out.println(name + " in " + location + " has been succesfully added to the system.");

		} catch (SQLException e) {
			System.out.println("Please try again and enter valid information.");
		}

	}
	
public static void listStores(){
	String select = "SELECT * FROM Store";

	try {

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(select);

		System.out.println("storeId, location, name");
		while (resultSet.next()) {
			String column = resultSet.getString("storeId") + ", " + resultSet.getString("location") + ", "
					+ resultSet.getString("name");
			System.out.println(column);
		}

	} catch (SQLException e) {
		System.out.println("Please try again and enter valid information.");
	}
}

}
