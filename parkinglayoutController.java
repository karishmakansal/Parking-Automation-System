/**
 * Sample Skeleton for 'parkinglayout.fxml' Controller Class
 */

package parklayout;

import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;

import connectdb.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import connectdb.dbConnection;

public class parkinglayoutController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="combo"
    private ComboBox<String> combo; // Value injected by FXMLLoader

    @FXML // fx:id="txtcap"
    private TextField txtcap; // Value injected by FXMLLoader

    @FXML // fx:id="radiobtn"
    private ToggleGroup radiobtn; // Value injected by FXMLLoader
    @FXML // fx:id="txt2"
    private RadioButton txt2; // Value injected by FXMLLoader
    @FXML
    private Label txtlabel;


    @FXML // fx:id="txt4"
    private RadioButton txt4; // Value injected by FXMLLoader
    Connection con;
    String type=null;

    

    @FXML
    void docllose(ActionEvent event) {
System.exit(1);
    }

    @FXML
    void dosave(ActionEvent event) {
    	  
	     if(txt2.isSelected()==true)
	     {
	    	  type="2";
	     }
	     else  if(txt4.isSelected()==true)
	     {
	    	  type="4";
	     }
    	save();
    }
    void save() 
    {
		    	String cap=txtcap.getText();
		    	String item=combo.getSelectionModel().getSelectedItem();
		    	
    	try{
    		
    		PreparedStatement pst=con.prepareStatement("insert into parkinglayout values(?,?,?,?,0)");
    		System.out.println(pst);
    		pst.setString(1, item);
    		pst.setString(2, cap);
			pst.setString(3,type);
    		pst.setString(4, "0");
    		
    		pst.executeUpdate();
    		txtlabel.setText("SAVED!!");	
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=(Connection) dbConnection.doConnect();
       String uid[]={"1","2","3","4","5","6","7","8"};
       combo.getItems().addAll(uid);
    }
}
