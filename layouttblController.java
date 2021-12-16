/**
 * Sample Skeleton for 'layouttbl.fxml' Controller Class
 */

package layouttable;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;

import connectdb.dbConnection;
import customerregtable.registerBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class layouttblController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tbl"
    private TableView<layoutBean> tbl; // Value injected by FXMLLoader
    Connection con;
    PreparedStatement pst;
    

    @FXML
    void dofetch(ActionEvent event) {
    	TableColumn<layoutBean,String> floor=new TableColumn<layoutBean,String>("Floor");
    	floor.setCellValueFactory(new PropertyValueFactory<>("floor"));
    	
    	TableColumn<layoutBean,Integer> capacity=new TableColumn<layoutBean,Integer>("FloorCapacity");
    capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
    	
    	TableColumn<layoutBean,String> type=new TableColumn<layoutBean,String>("Vh Type");
    	type.setCellValueFactory(new PropertyValueFactory<>("type"));
    	

    	TableColumn<layoutBean,Integer> Occupied=new TableColumn<layoutBean,Integer>("Occupied");
    	Occupied.setCellValueFactory(new PropertyValueFactory<>("Occupied"));

	TableColumn<layoutBean,Integer> Amount=new TableColumn<layoutBean,Integer>("FloorCapacity");
	Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
    	
	tbl.getColumns().clear();
	tbl.getColumns().addAll(floor,capacity,type,Occupied,Amount);
	tbl.setItems(list);
    	    }

    ObservableList<layoutBean> list;
    void fetchallrecords()
    {
    	list=FXCollections.observableArrayList();
    	try
    	{
    		pst=con.prepareStatement("select * from parkinglayout");
    		ResultSet table=pst.executeQuery();
        	while(table.next())
        	{
        		String flr =table.getString("Floor");
        		int capacity =table.getInt("capacity");
        		String type =table.getString("type");
        		int Occupied =table.getInt("Occupied");
        		int Amount =table.getInt("Amount");
        		
        	layoutBean sb=new layoutBean(flr,capacity,type,Occupied,Amount);
        		list.add(sb) ;  		
        	}
    		
    		
    	}
    	catch(Exception e)
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
