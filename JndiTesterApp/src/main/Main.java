import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Main {
	public static void main(String[] args) {
		try {
			DataSource datasource = (DataSource) new InitialContext().lookup("jdbc/DB2");
			System.out.println("Found: " + datasource);
			
			try (Connection connection = datasource.getConnection()) {
				try (Statement statement = connection.createStatement()) {
					try (ResultSet results = statement.executeQuery("SELECT USER_ID FROM TABLE WHERE TRANSACTION_ID = '26874006'")) {
						if (results.next()) {
							System.out.println("Data from read: " + results.getString(1));
						}
					}
				}
			}
		} catch (NamingException | SQLException e) {
			System.out.println("Could not lookup");
			e.printStackTrace();
		}
	}

}