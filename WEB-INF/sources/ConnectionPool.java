/*
 *	database pool connection utility class
 */

import java.sql.*;

import javax.sql.DataSource;
import javax.naming.InitialContext;

public class ConnectionPool{

	private static ConnectionPool cp;
	private static DataSource ds;
	
	private ConnectionPool(){
		try{
			InitialContext ic=new InitialContext();
			ds=(DataSource) ic.lookup("java:comp/env/jdbc/YOUR_DATABASE_NAME_HERE");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static ConnectionPool getInstance(){
		if(cp==null){
			cp=new ConnectionPool();
		}
		
		return cp;
	}

	public Connection getConnection(){
		try{
			return ds.getConnection();
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void freeConnection(Connection c){
		try{
			c.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
