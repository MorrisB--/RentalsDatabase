import java.sql.*;

public class Customer extends ConnectionToDatabase {

	public void createCustomer(String firstName, String lastName, String address, String phoneNumber) {

		String createCustomer = "INSERT INTO Customer VALUES (" + (this.getRows() + 1) + ", '" + firstName + "', '"
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

}
