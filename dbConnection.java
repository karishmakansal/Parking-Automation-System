package connectdb;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;


public class dbConnection {
	public static Connection doConnect()  
	{
		Connection con=null;
		try{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/carparking","root","123");
		System.out.println("connected.....");

	
		

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	

	public static void main(String[] args) {
		doConnect();

	}

}
