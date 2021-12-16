package Tablelayout;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import connectdb.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<LayoutBean> tbl;

    @FXML
    void doFetchAll(ActionEvent event) {
    	TableColumn<LayoutBean, String> floor=new TableColumn<LayoutBean, String>("Floor");//Dikhava Title
    	floor.setCellValueFactory(new PropertyValueFactory<>("floor"));//bean field name, no link with table col name

    	TableColumn<LayoutBean, Integer>capacity =new TableColumn<LayoutBean, Integer>("capacity");//Dikhava Title
    	capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));//bean field name, no link with table col name
    	
    	TableColumn<LayoutBean, String> type=new TableColumn<LayoutBean, String>("type");//Dikhava Title
    	type.setCellValueFactory(new PropertyValueFactory<>("type"));//bean field name, no link with table col name

    	TableColumn<LayoutBean, Integer>occupied=new TableColumn<LayoutBean, Integer>("Occupied");//Dikhava Title
    	occupied.setCellValueFactory(new PropertyValueFactory<>("occupied"));//bean field name, no link with table col name
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(floor,capacity,type,occupied);
    	tbl.setItems(list);
    	
    }
    ObservableList<LayoutBean> list;
    Connection con;
    void fetchAllRecords()
    {
    	list=FXCollections.observableArrayList();
    	try{
         PreparedStatement	pst=con.prepareStatement("select * from playout");
        	ResultSet table= pst.executeQuery();
        		
        		while(table.next())
        		{
        			String floor=table.getString("floor");//col. name acc. to table
        			int capacity=table.getInt("capacity");
        			String type=table.getString("type");
        			int occupied=table.getInt("occupied");
        			System.out.println(floor+"  "+capacity+"  "+type+"   "+occupied);
        			LayoutBean lb=new LayoutBean(floor,capacity,type,occupied);
        			list.add(lb);
        			
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	
    }
   

	@FXML
    void initialize() {
		 con=DBConnection.doConnect();
    	 fetchAllRecords();

    }
}

