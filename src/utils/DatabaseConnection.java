package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection implements AutoCloseable{

	
	private static java.sql.Connection connection;

	public DatabaseConnection() throws ClassNotFoundException, SQLException, IOException {

			Properties prop =new Properties();	
		    prop.load(Connection.class.getResourceAsStream("configuration.txt"));
		    Class.forName(prop.getProperty("jdbc.driver.class"));
		    String user = prop.getProperty("jdbc.driver.user");
		    String pass = prop.getProperty("jdbc.driver.password");
			String url = prop.getProperty("jdbc.driver.url");
			connection = DriverManager.getConnection(url, user, pass);
	}

	 public java.sql.Connection getConnection() {
		return connection;
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}
	 
	 
}
