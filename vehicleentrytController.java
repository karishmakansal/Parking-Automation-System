/**
 * Sample Skeleton for 'vehicleentry.fxml' Controller Class
 */

package customentry;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import connectdb.dbConnection;

public class vehicleentrytController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtmob"
    private TextField txtmob; // Value injected by FXMLLoader

    @FXML // fx:id="txtrandom"
    private RadioButton txtrandom; // Value injected by FXMLLoader

    @FXML // fx:id="radiobtn"
    private ToggleGroup radiobtn; // Value injected by FXMLLoader

    @FXML // fx:id="txtregular"
    private RadioButton txtregular; // Value injected by FXMLLoader

    @FXML // fx:id="txt4"
    private RadioButton txt4; // Value injected by FXMLLoader

    @FXML // fx:id="radiobtn2"
    private ToggleGroup radiobtn2; // Value injected by FXMLLoader

    @FXML // fx:id="txt2"
    private RadioButton txt2; // Value injected by FXMLLoader

    @FXML // fx:id="txtvehicle"
    private TextField txtvehicle; // Value injected by FXMLLoader
    @FXML
    private ListView<String> listfloor;

    @FXML
    private ListView<String> listcapacity;

   
    @FXML
    private Label txtlabel;


    String flr;
    Connection con;
    PreparedStatement pst;
    String type1;
    int cap;
    int occ;
    int rid;
    String customer;
    
    @FXML
    void dofloor(ActionEvent event) {
  	
  	if(txt2.isSelected()==true)
	{
		type1="2";
	}
	else if(txt4.isSelected()==true)
	{

    		type1="4";
    	}
    	 ArrayList<String> floorarray=new ArrayList<>();
    	 ArrayList<String> fss=new ArrayList<>();
     try
     
     {
    	 pst=con.prepareStatement("select  Floor,type,capacity,Occupied from parkinglayout");
    	 ResultSet table=pst.executeQuery();
    	 while(table.next())
    	 {     String t=table.getString("type");
    	     occ=Integer.parseInt(table.getString("Occupied"));
    	     cap=Integer.parseInt(table.getString("capacity"));
    	    String avail= String.valueOf(cap-occ);
    	    if(t.equals(type1) &&((cap-occ)!=0))
    	    {
    	 String fl=table.getString("Floor"); 
    		 floorarray.add(fl);
    		 fss.add(avail);	
    	 }
     }
     }catch(Exception e)
     {
    	 e.printStackTrace();
     }
     listfloor.getItems().addAll(floorarray); 
     listcapacity.getItems().addAll(fss);
     }
    
    @FXML
    void dosave(ActionEvent event) throws SQLException {
    	save();
    }
    void save() throws SQLException 
    {     
   
		  	if(txtregular.isSelected()==true)
		  	{
		  		customer="regular";
		  	}
		  	else if(txtrandom.isSelected()==true)
			  	{
			  		customer="random";
			  	}
		  	System.out.println(customer);
		  	if(txt2.isSelected()==true)
	    	{
	    		type1="2";
	    	}
	    	else if(txt4.isSelected()==true)
	    	{
	    		type1="4";
	    	}
		  	System.out.println(type1);
		    	String vehicle=txtvehicle.getText();
		    	String mobile=txtmob.getText();
		    flr=(String)listfloor.getSelectionModel().getSelectedItem();
		    	int indx=listfloor.getItems().indexOf(flr);
		    	listcapacity.getSelectionModel().select(indx);
	    		String p= listcapacity.getSelectionModel().getSelectedItem();
		   		
    	try{
    		PreparedStatement pst=con.prepareStatement("insert into vehicleentry values(?,?,?,?,?,?,curdate(),curtime(),1,null) ");
    		System.out.println(pst);
    	pst.setString(1, customer);
    	pst.setString(2, mobile);
    	pst.setString(3, vehicle);
    	pst.setString(4, type1);
    	pst.setString(5, flr);
    	pst.setString(6,p);		
    		pst.executeUpdate();
    	txtlabel.setText("SAVED!!");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	doupdate();
 
    }
    void doupdate()
    {
    	try
    	{
    	PreparedStatement pst=con.prepareStatement("update parkinglayout set Occupied=? where type=? and Floor=?");
    	occ=occ+1;
    	pst.setString(1,String.valueOf(occ));
    	pst.setString(2, type1);
    	pst.setString(3,flr);
    	pst.executeUpdate();
    	}
    	catch(Exception e)
    	{
    	e.printStackTrace();	
    	}
    }
      @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
       con=(Connection) dbConnection.doConnect();
    }
}
