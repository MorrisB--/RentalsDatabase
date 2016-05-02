import java.sql.*;
import java.util.Scanner;

public class Store extends ConnectionToDatabase {

	public static void createStore() {

		Scanner keyboard = new Scanner(System.in);
		System.out.print("\nWhat is the location of the store? ");
		String location = keyboard.next();
		System.out.print("What is the name of the store? ");
		String name = keyboard.next();
		keyboard.close();
		
		String createStore = "INSERT INTO Store VALUES (" + (getRows("Store") + 1) + ", '" + location + "', '" + name
				+ "');";
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
