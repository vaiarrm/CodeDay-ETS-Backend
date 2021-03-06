package Database;
import java.sql.*;

public class JDBCConnection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://104.236.35.207:3306/ets";
	static final String USER = "ets";
	static final String PASS = "ets";

	public Connection connectionManager() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			System.out.println("Not Connected");
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String [] Args) throws SQLException{

		JDBCConnection jcon = new JDBCConnection();
		Connection conn = jcon.connectionManager();
		Statement stmt = conn.createStatement();
		for(int i = 10; i < 100 ; i++){
			int 	driverid				= i;
			String driverAdd                = "243 S WABASH AVE";
			String driverCity               = "CHICAGO";
			String driverState              = "IL";
			String driverZipCode           	= "606"+i;
			String driverPhone              = "(800) 123-4567";
			String driverFName  			= "JOHN";
			String driverLName 				= "DOE" + i; 
			String driverLic          		= "klmdelta";
			String driverStat	    		= "A";

			String insertQuery = 	"INSERT INTO DRIVER VALUES ( "
					+  driverid +  " , "  
					+ "\'" + driverAdd  	+ "\'" +  " , "  
					+ "\'" + driverCity 	+ "\'" +  " , " 
					+ "\'" + driverState 	+ "\'" +  " , " 
					+ "\'" + driverZipCode 	+ "\'" +  " , " 
					+ "\'" + driverPhone 	+ "\'" +  " , " 
					+ "\'" + driverFName 	+ "\'" +  " , " 
					+ "\'" + driverLName	+ "\'" +  " , "
					+ "\'" + driverLic	+ "\'" +  " , "
					+ "\'" + driverStat	+ "\'" +  " )";
			stmt.executeUpdate(insertQuery);
		}
		//conn.commit();
		conn.close();
	}


}
