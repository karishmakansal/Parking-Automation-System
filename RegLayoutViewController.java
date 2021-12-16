package Registrationlayout;



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

public class RegLayoutViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ReglayoutBean> tbl;

    @FXML
    void doFtechAll(ActionEvent event) {
    	 {
    	    	TableColumn<ReglayoutBean, String> Mobile=new TableColumn<ReglayoutBean, String>("Mobile");//Dikhava Title
    	    	Mobile.setCellValueFactory(new PropertyValueFactory<>("Mobile"));//bean field name, no link with table col name

    	    	TableColumn<ReglayoutBean, String> Name=new TableColumn<ReglayoutBean, String>("Name");//Dikhava Title
    	    	Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
    	    	
    	    	TableColumn<ReglayoutBean, String> Address=new TableColumn<ReglayoutBean, String>("Address");//Dikhava Title
    	    	Address.setCellValueFactory(new PropertyValueFactory<>("Address"));

    	    	TableColumn<ReglayoutBean, String> City=new TableColumn<ReglayoutBean, String>("City");//Dikhava Title
    	    	City.setCellValueFactory(new PropertyValueFactory<>("City"));
    	    	TableColumn<ReglayoutBean, String> Pic=new TableColumn<ReglayoutBean, String>("Pic");//Dikhava Title
    	    	Pic.setCellValueFactory(new PropertyValueFactory<>("Pic"));
    	    	tbl.getColumns().clear();
    	    	tbl.getColumns().addAll(Mobile,Name,Address,City,Pic);
    	    	tbl.setItems(list);
    	    	
    	    }
    }
    	    ObservableList<ReglayoutBean> list;
    	    Connection con;
    	    void fetchAllRecords()
    	    {
    	    	list=FXCollections.observableArrayList();
    	    	try{
    	         PreparedStatement	pst=con.prepareStatement("select * from customer");
    	        	ResultSet table= pst.executeQuery();
    	        		
    	        		while(table.next())
    	        		{
    	        			String Mobile=table.getString("Mobile");//col. name acc. to table
    	        			String Name=table.getString("Name");
    	        			String Address=table.getString("Address");
    	        			String City=table.getString("City");
    	        			String Pic=table.getString("Pic");
    	        			
    	        			System.out.println(Mobile+"  "+Name+"  "+Address+"   "+City+" "+Pic);
    	        			ReglayoutBean rb=new ReglayoutBean(Mobile,Name,Address,City,Pic);
    	        			list.add(rb);
    	        			
    	        		}
    	        	}
    	        	catch(Exception ex)
    	        	{
    	        		ex.printStackTrace();
    	        	}
    	    	

    	    }
    

    @FXML
    void initialize()
    {
    	con=DBConnection.doConnect();
   	 fetchAllRecords();
    }
}

