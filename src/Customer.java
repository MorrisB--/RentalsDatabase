import java.sql.*;

public class Customer extends ConnectionToDatabase {

	public void createCustomer(String firstName, String lastName, String address, String phoneNumber) {

		String createCustomer = "INSERT INTO Customer VALUES (" + (this.getRows("Customer") + 1) + ", '" + firstName + "', '"
				+ lastName + "', '" + address + "', '" + phoneNumber + "');";

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

	public void subtractFees(String Id, int payment) {

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

}
