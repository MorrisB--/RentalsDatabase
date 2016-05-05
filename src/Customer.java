import java.sql.*;
import java.util.Scanner;

public class Customer extends ConnectionToDatabase {

	public static void createCustomer() {

		Scanner keyboard = new Scanner(System.in);
		System.out.print("What is the customers first name? ");
		String firstName = keyboard.next();
		System.out.print("What is the customers last name? ");
		String lastName = keyboard.next();
		System.out.print("What is the customers address? ");
		String address = keyboard.nextLine();
		System.out.print("What is the customers phone number? ");
		String phoneNumber = keyboard.next();
		keyboard.close();

		String createCustomer = "INSERT INTO Customer VALUES (" + (getRows("Customer") + 1) + ", '" + firstName + "', '"
				+ lastName + "', '" + address + "', '" + phoneNumber + "', 0);";

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(createCustomer);
			System.out.println(firstName + " " + lastName + " has been succesfully added to the system.");

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}
	}

	public static void addFees(int userId, double fees) {
		try {

			String updateCustomer = "UPDATE Customer SET fees = fees + " + fees + " WHERE userId = " + userId + ";";

			Statement statement = connection.createStatement();
			statement.executeUpdate(updateCustomer);

			System.out.println("Fees added to customer");

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}

	}

	public static void subtractFees() {

		Scanner keyboard = new Scanner(System.in);
		System.out.print("What is the customers ID? ");
		int Id = keyboard.nextInt();
		System.out.print("\nHow much is the customer paying? ");
		double payment = keyboard.nextDouble();
		keyboard.close();

		String subtractFees = "UPDATE Customer SET fees = fees - " + payment + " WHERE userId = " + Id + ";";

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(subtractFees);
			System.out.println("Payment successfully made.");

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}

	}

	public static void listCustomers() {

		String select = "SELECT * FROM Customer";

		try {

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(select);

			System.out.println("userId, firstName, lastName, address, phoneNumber, fees");
			while (resultSet.next()) {
				String column = resultSet.getString("userId") + " " + resultSet.getString("firstName") + " "
						+ resultSet.getString("lastName") + " " + resultSet.getString("address") + " "
						+ resultSet.getString("phoneNumber") + " " + resultSet.getString("fees");
				System.out.println(column);
			}

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}
	}

}
