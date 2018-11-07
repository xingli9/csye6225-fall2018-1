package csye6225Web;

import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

	private static MyConnection instance = null;

	private MyConnection() {
		ResourcePropertySource propertySource2 = null; //name, location
		try {
			propertySource2 = new ResourcePropertySource("resources", "classpath:application.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
		URL = propertySource2.getProperty("spring.datasource.url").toString();
		USER = propertySource2.getProperty("spring.datasource.username").toString();
		PASSWORD = propertySource2.getProperty("spring.datasource.password").toString();
	}

	private static String DRIVER = "org.mariadb.jdbc.Driver";
	private static String URL;
	private static String USER;
	private static String PASSWORD;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
    	Class.forName(DRIVER);
		if (instance == null) {
			instance = new MyConnection();
		}
		System.out.println("username: -" + USER + "+ password: -" +  PASSWORD + "+url +" + URL);
    	return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	public static void closeConnection(Connection conn) {
   	 try {
   		 conn.close();
   	 } catch (SQLException e) {
   		 e.printStackTrace();
   	 }
	}

}
