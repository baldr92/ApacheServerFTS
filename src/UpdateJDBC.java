import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.NamingException;

public class UpdateJDBC {
	private static final String url = "jdbc:oracle:thin:@10.22.93.34:1521:DBTEST";
	private static final String user = "TESTUSER";
	private static final String password = "testuser";
	private static final String driverName = "oracle.jdbc.driver.OracleDriver";
	private static Connection conn;
	
	public void updateDB(String xml) throws ClassNotFoundException, SQLException, NamingException, ParseException {
		Class.forName(driverName);
		ResultSet checkMQUUID = null;
		PreparedStatement prepStateSelect = null;
		PreparedStatement prepStateUpdate = null;
		try {
		conn = DriverManager.getConnection(url, user, password);
		//conn = ds.getConnection();
		
		
		//date
		SimpleDateFormat rcvFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		String rcvDateString = ListenerMQMsg.dateDb;
		java.util.Date rcvDate = rcvFormat.parse(rcvDateString);		
		java.sql.Timestamp rcvSqlTimestamp = new java.sql.Timestamp(rcvDate.getTime());
		
		
		//mqID
		String guid = ListenerMQMsg.mqId;
		String envid = ListenerMQMsg.envId;
		
		String check = null;
		prepStateSelect = conn.prepareStatement(
				"SELECT TYPEXML FROM KPSPP_TEST WHERE MQUUID=?");

		

		if (guid != null) {     
			prepStateSelect.setString(1, guid);
			System.out.println("SELECT TYPEXML FROM KPSPP_TEST WHERE MQUUID= '" + guid + "'");
			checkMQUUID = prepStateSelect.executeQuery(); 
		} 

		else if (envid != null) { 				
			prepStateSelect.setString(1, envid);
			System.out.println("SELECT TYPEXML FROM KPSPP_TEST WHERE MQUUID= '" + envid + "'");
			checkMQUUID = prepStateSelect.executeQuery(); 
		}
		else {
			System.out.println("UUID IS NOT FOUND");
		}
		while (checkMQUUID.next()) 	{
			check = checkMQUUID.getString("TYPEXML");
			System.out.println("check: " + check);
		}
		conn.setAutoCommit(true);

		System.out.println("rcvSqlTimestamp: " + rcvSqlTimestamp.toString());
			
		if (check != null) {
				prepStateUpdate = conn.prepareStatement(
						"UPDATE KPSPP_TEST SET RECVTIME = ? WHERE MQUUID = ?");
				prepStateUpdate.setTimestamp(1, rcvSqlTimestamp);	
				if (guid != null) {
					prepStateUpdate.setString(2, guid);
					prepStateUpdate.executeUpdate();
					prepStateUpdate.close();
				} else {
					prepStateUpdate.setString(2, envid);
					prepStateUpdate.executeUpdate();
					prepStateUpdate.close();
				}
				System.out.println("prepStateUpdate close");	
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		finally {
			if (checkMQUUID != null) {
				checkMQUUID.close();
			}
			if (prepStateSelect != null) {
				prepStateSelect.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
}

