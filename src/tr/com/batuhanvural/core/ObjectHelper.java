package tr.com.batuhanvural.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import tr.com.batuhanvural.interfaces.CoreInterfaces;

public class ObjectHelper extends CoreFields implements CoreInterfaces {


	static {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	
	@Override
	public Connection getConnection() {
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(getUrl(),getUserName(),getPassword());
		} catch (SQLException e) {
			System.out.println("Bağlantı başarısız.");
			e.printStackTrace();
		}
		
		return connection;
	}
	
	

}
