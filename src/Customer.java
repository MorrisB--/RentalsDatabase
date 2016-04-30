
public class Customer extends ConnectionToDatabase {

	public void createCustomer(String firstName, String lastName, String address, String phoneNumber) {

		String createCustomer = "INSERT INTO Customer VALUES (" + (this.getRows() + 1) + ", '" + firstName + "', '"
				+ lastName + "', '" + address + "', '" + phoneNumber + "');";

	}

}
