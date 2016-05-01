import java.sql.*;

public class Item extends ConnectionToDatabase {
	
	public void createItem(String name, double price, int lateFee){
		
		String insertItem = "INSERT INTO Item VALUES(" + name + ", " + price + ", " + lateFee + ");";
		
		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(insertItem);
			System.out.println(name +  " has been succesfully added to the system.");

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}
		
	}

}
