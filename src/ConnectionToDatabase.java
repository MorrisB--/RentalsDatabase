import java.sql.*;
import java.util.*;

public class ConnectionToDatabase {

	public static void main(String[] args) {

		Connection connection = null;

		try {

			Scanner keyboard = new Scanner(System.in);
			System.out.println("Enter your password: ");
			String password = "user=mballenger&password=" + keyboard.next();
			
			connection = DriverManager.getConnection("jdbc:mysql://cs.neiu.edu:3306/db_Spr16_mballenger?" + password);

			Statement statement1 = connection.createStatement();
			String sql1 = "INSERT INTO Guest VALUES (106, 'TestL','TestF','111-111-1111')";
			statement1.executeUpdate(sql1);

			keyboard.close();

		} catch (SQLException e) {

			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("SQLException: " + e.getErrorCode());

		}

	}

}
