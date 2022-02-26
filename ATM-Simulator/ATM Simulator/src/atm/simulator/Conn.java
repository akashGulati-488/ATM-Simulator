package atm.simulator;

import java.sql.*;

public class Conn {
	//using connection interface for connecting to the db
	Connection conn;
	//using statement interface to execute our queries
	Statement s;
	
	public Conn() {
		try {
			//registering the drivers
			Class.forName("com.mysql.cj.jdbc.Driver");
			//creating an connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmsimulator","root","");
            conn.createStatement();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}