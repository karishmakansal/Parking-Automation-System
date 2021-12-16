/**
 * Sample Skeleton for 'vehicleexit.fxml' Controller Class
 */

package customexit;

import java.io.FileOutputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import connectdb.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import connectdb.dbConnection;

public class vehicleexitController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtvehicle"
    private TextField txtvehicle; // Value injected by FXMLLoader

    @FXML // fx:id="txtfloor"
    private TextField txtfloor; // Value injected by FXMLLoader

    @FXML // fx:id="entrydate"
    private TextField entrydate; // Value injected by FXMLLoader

    @FXML // fx:id="exittime"
    private TextField exittime; // Value injected by FXMLLoader

    @FXML // fx:id="exitdate"
    private TextField exitdate; // Value injected by FXMLLoader

    @FXML // fx:id="entrytime"
    private TextField entrytime; // Value injected by FXMLLoader
    @FXML // fx:id="txtlabel"
    private Label txtlabel; // Value injected by FXMLLoader

    @FXML // fx:id="amount"
    private TextField amount; // Value injected by FXMLLoader
    Connection con;
    PreparedStatement pst;
    String flr;
    String vehicletype;
    String Vehicleid;
    String exdate;
    String extime;
    String entdate; 
    String enttime;
    String customer;
    String stat;
    int b;
    String am;
    int h;
    
    

    @FXML
    void dobill(ActionEvent event) {
   String starttime=entdate+" "+enttime;
   String stoptime=exdate+" "+extime;
   SimpleDateFormat  format=new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   Date d1=null;
   Date d2=null;
  double bill;
   try
   {
	   d1=format.parse(starttime);
	   d2=format.parse(stoptime);
	   long diff=d2.getTime()-d1.getTime();
	  // long diffseconds=diff/1000%60;
	   long diffminutes=diff/(60*1000)%60;
	   long diffhours=diff/(60*60*1000)%24;
	   long diffdays=diff/(24*60*60*1000);
	   txtlabel.setText(diffdays+"days "+diffhours+"hrs "+diffminutes+"min ");
	if(vehicletype.equals("2"))
	{
		bill=50*diffdays+20*diffhours+10*diffminutes;
		if(customer.equals("regular"))
		{
			
	    bill=(bill*0.2f);
	    int b=(int)bill;
	        amount.setText(String.valueOf(b)+" Rs");		
		}
		else
		{
			amount.setText(String.valueOf(bill)+"Rs");	
		}
	}
	else if(vehicletype.equals("4"))
	{
		bill=100*diffdays+50*diffhours+20*diffminutes;
		if(customer.equals("regular"))
		{ 
		    bill=(bill*0.2f);
		    int b=(int)bill;
	        amount.setText(String.valueOf(b)+"Rs");		
		}
		else
		{
			amount.setText(String.valueOf(bill)+"Rs");	
		}
	}
	   
   }catch(Exception e)
   {
	   e.printStackTrace();
   }
   try {
	doupdate();
} catch (SQLException e) {
	
	e.printStackTrace();
}
    	}
   

    @FXML
    void doupdate() throws SQLException {
       dostatus();
       dobill();
       doocuupied();
    }
    void dostatus()
    {
    	try{
        	PreparedStatement pst=(PreparedStatement)con.prepareStatement("update vehicleentry set status=?  where Vehicleno=? ");
     pst.setString(2, Vehicleid);
     pst.setString(1, "0");
      	pst.executeUpdate();
        	}
        	catch(Exception e)
        	{
        	e.printStackTrace();	
        	}
    }
    void doocuupied() throws SQLException
    {
    	String k=amount.getText();
    	int ind=k.indexOf(" ");
    	String as=k.substring(0,ind);
    b=Integer.parseInt(as);
 int p=Integer.parseInt(am);
  h=b+p;
 System.out.println(h);
    	try
    	{
    	PreparedStatement pst=(PreparedStatement)con.prepareStatement("update parkinglayout set Occupied=Occupied-1, Amount=? where Floor=? and type=? ");
    	pst.setLong(1, h);
    	pst.setString(2, flr);
    	pst.setString(3, vehicletype );
    	pst.executeUpdate();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    }
    void dobill()
    {
    	try
    	{
    	PreparedStatement pst=(PreparedStatement)con.prepareStatement("select *from parkinglayout where Floor=? and type=? ");
    	pst.setString(1, flr);
    	pst.setString(2, vehicletype );
    	
    	ResultSet table=pst.executeQuery();
    	while(table.next())
    	{
    		am=table.getString("Amount");
    	}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    @FXML
    void dofetch(ActionEvent event) {
    	   Vehicleid=txtvehicle.getText();
    	 try{
    	    	pst=(PreparedStatement) con.prepareStatement("select *from vehicleentry where Vehicleno=?");
    	    	pst.setString(1,Vehicleid );
    	    	ResultSet table=pst.executeQuery();
    	    	boolean jasus=false;
    	    	while(table.next())
    	    	{
    	    		jasus=true;
    	    customer=table.getString("CustomerType");
    	    	 entdate=table.getString("date");
    	    	 enttime=table.getString("time");
    	    	flr=table.getString("Floor");
    	    	 stat=table.getString("status");
    	    	 vehicletype=table.getString("type");
    	    	if(stat.equals("0"))
    	    	{
    	    		 Alert alert=new Alert(Alert.AlertType.INFORMATION);
    	   		  alert.setTitle("INFORMATION DIALOG");
    	   		  alert.setHeaderText("Already exited please recheck");
    	   		  alert.showAndWait();
    	    	}
    	    	else
    	    	{
    	    	  txtfloor.setText(flr);
    	    	  entrydate.setText(entdate);
    	    	  entrytime.setText(enttime);
    	    	  Date date=new Date();
    	    	 SimpleDateFormat  fot=new  SimpleDateFormat("yyyy-MM-dd");
    	    	   exdate=fot.format(date);
    	    	  exitdate.setText(exdate);
    	    	  Date da=new Date();
    	    	  SimpleDateFormat  fo=new  SimpleDateFormat("HH:mm:ss");
    	    	   extime=fo.format(da);
    	    	  exittime.setText(extime);
    	    	  
    	    	}
    	    	}
    	    	if(jasus==false)
    	    	{
    	    	 Alert a1=new Alert(Alert.AlertType.ERROR); 
    		  a1.setTitle("Enter valid vehicle no");
    		  a1.setContentText("PUT VALID VEHICLE NO");
    		  a1.showAndWait();
    	    	}
    	    	}catch(Exception e)
    	 
    	 {
    		 e.printStackTrace();
    }
    }

	@FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
       con=(Connection) dbConnection.doConnect();
    }
}
