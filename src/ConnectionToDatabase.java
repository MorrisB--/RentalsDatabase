import java.sql.*;
import java.util.*;

public class ConnectionToDatabase {
	public static void main(String[] args) {

		Connection connection = null;

		try {

			Scanner keyboard = new Scanner(System.in);
			System.out.println("Enter your username: ");
			String username = keyboard.next();

			System.out.println("Enter your password: ");
			String password = keyboard.next();
			keyboard.close();

			connection = DriverManager.getConnection("jdbc:mysql://cs.neiu.edu:3306/db_Spr16_" + username + "?user="
					+ username + "&password=" + password);

			String sql1 = "INSERT INTO Breakfast VALUES (11, 'Syrup')";
			String sql2 = "INSERT INTO Breakfast VALUES (12, 'Extra Salt')";
			String sql3 = "DELETE FROM Breakfast WHERE id = 12";
			String sql4 = "INSERT INTO Breakfast VALUES (12, 'Light Salt')";
			String sql5 = "SELECT * FROM Breakfast";

			Statement statement1 = connection.createStatement();
			statement1.executeUpdate(sql1);
			// Statement statement2 = connection.createStatement();
			statement1.executeUpdate(sql2);
			// Statement statement3 = connection.createStatement();
			statement1.executeUpdate(sql3);
			// Statement statement4 = connection.createStatement();
			statement1.executeUpdate(sql4);

			// Is this how you wanted us to select all?
			// Statement statement5 = connection.createStatement();
			System.out.println("\n" + sql5 + "\nid name");
			ResultSet rs = statement1.executeQuery(sql5);
			while (rs.next()) {
				String all = rs.getString("id") + "  " + rs.getString("name");
				System.out.println(all);
			}

		} catch (SQLException e) {
			
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());
		}
	}
}
