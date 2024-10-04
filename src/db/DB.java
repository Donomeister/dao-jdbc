package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	public static Connection conn = null;
	
	public static Connection getConnection(){
		if(conn != null) {
			try {
				Properties props = loadProperties();
		
				String DBurl = props.getProperty("DBurl");
			
				conn = DriverManager.getConnection(DBurl, props);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	private static Properties loadProperties() {
		Properties props = new Properties();
		
		try(FileInputStream fs = new FileInputStream("DB.properties")){
			
			props.load(fs);
			return props;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return props;
	}
}
