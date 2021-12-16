package connectdb;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import connectdb.dbConnection;

public class jdbcController {
public static void main(String args[])
{
	new jdbcController().doSave();
}
void doSave()
{
Connection con=(Connection) dbConnection.doConnect();	
try{
	PreparedStatement pst=con.prepareStatement("insert into std values(?,?,?)");
	pst.setInt(1,102);
	pst.setString(2,"raveenaa");
	pst.setFloat(3, 99f);
	pst.executeUpdate();
	System.out.println("saved.........");		
}
catch(SQLException e)
{
	e.printStackTrace();
}
}
}
