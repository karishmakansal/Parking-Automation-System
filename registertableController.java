/**
 * Sample Skeleton for 'registertable.fxml' Controller Class
 */

package customerregtable;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import connectdb.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class registertableController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tbl"
    private TableView<registerBean> tbl; // Value injected by FXMLLoader
    Connection con;
    PreparedStatement pst;
    

    @FXML
    void dofetch(ActionEvent event) {

    	TableColumn<registerBean,String> name=new 	TableColumn<registerBean,String>("NAME");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	
    	TableColumn<registerBean,String> address=new 	TableColumn<registerBean,String>("ADDRESS");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	
    	TableColumn<registerBean,String> mobileno=new 	TableColumn<registerBean,String>("MOBILENO");
    	mobileno.setCellValueFactory(new PropertyValueFactory<>("mobileno"));
    	
    	TableColumn<registerBean,String> emailid=new 	TableColumn<registerBean,String>("EMAILID");
    	emailid.setCellValueFactory(new PropertyValueFactory<>("emailid"));
    	
    	TableColumn<registerBean,String> proofselected=new 	TableColumn<registerBean,String>("PROOF");
    	proofselected.setCellValueFactory(new PropertyValueFactory<>("proofselected"));
    	
    	TableColumn<registerBean,String> proofid=new 	TableColumn<registerBean,String>("PROOFID");
    	proofid.setCellValueFactory(new PropertyValueFactory<>("proofid"));
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(name,address,mobileno,emailid,proofselected,proofid);
    	tbl.setItems(list);
    	
    }
    ObservableList<registerBean> list;
    void fetchallrecords()
    {
    	list=FXCollections.observableArrayList();
    	try
    	{
    		pst=con.prepareStatement("select * from customerregistration");
    		ResultSet table=pst.executeQuery();
        	while(table.next())
        	{
        		String name =table.getString("Name");
        		String address =table.getString("Address");
        		String mobileno =table.getString("Mobileno");
        		String emailid =table.getString("Emailid");
        		String proofSelected =table.getString("proofselected");
        		String proofid =table.getString("proofId");
        		registerBean sb=new registerBean(name,address,mobileno,emailid,proofSelected,proofid);
        		
        		list.add(sb);     		
        	}
    		
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
  con=(Connection) dbConnection.doConnect();
  fetchallrecords();
    }
}
