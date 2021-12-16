package entrytable;




/**
 * Sample Skeleton for 'registertable.fxml' Controller Class
 */


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

public class entrytblController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tbl"
    private TableView<entryBean> tbl; // Value injected by FXMLLoader
    Connection con;
    PreparedStatement pst;
    

    @FXML
    void dofetch(ActionEvent event) {

    	TableColumn<entryBean,String> CustomerType=new 	TableColumn<entryBean,String>("CustomerType");
    	CustomerType.setCellValueFactory(new PropertyValueFactory<>("CustomerType"));
    	

    	TableColumn<entryBean,String> mobileno=new 	TableColumn<entryBean,String>("Mobileno");
    	mobileno.setCellValueFactory(new PropertyValueFactory<>("mobileno"));
    	

    	TableColumn<entryBean,String>  Vehicleno=new 	TableColumn<entryBean,String>(" Vehicleno");
    	 Vehicleno.setCellValueFactory(new PropertyValueFactory<>(" Vehicleno"));

    	TableColumn<entryBean,String> type=new 	TableColumn<entryBean,String>("Vehicle type");
    	type.setCellValueFactory(new PropertyValueFactory<>("type"));
    	
    	

    	TableColumn<entryBean,Integer>  availability=new 	TableColumn<entryBean,Integer>("vacant");
    	 availability.setCellValueFactory(new PropertyValueFactory<>(" availability"));

     	
    	
    	

    	TableColumn<entryBean,String> date=new 	TableColumn<entryBean,String>("EntrtDate");
    	date.setCellValueFactory(new PropertyValueFactory<>("date"));
    	
    	

    	TableColumn<entryBean,String> time=new 	TableColumn<entryBean,String>("Entry");
    	time.setCellValueFactory(new PropertyValueFactory<>("time"));
    	
    	
    	
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(CustomerType,mobileno, Vehicleno,type, availability,date,time);
    	tbl.setItems(list);
    	
    }
    ObservableList<entryBean> list;
    void fetchallrecords()
    {
    	list=FXCollections.observableArrayList();
    	try
    	{
    		pst=con.prepareStatement("select * from vehicleentry");
    		ResultSet table=pst.executeQuery();
        	while(table.next())
        	{
        		String CustomerType=table.getString("CustomerType");       		
        		String mobileno=table.getString(" mobileno");
        		String Vehicleno=table.getString(" Vehicleno");
        		String type=table.getString("type");
        		int availability=table.getInt(" availability");
        		String date=table.getString(" date");
        		String time=table.getString("time");
        entryBean sb=new entryBean(CustomerType,mobileno,Vehicleno,type,availability,date,time);
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
