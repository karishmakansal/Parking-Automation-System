package VehicleEntry;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import connectdb.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

public class VehicleEntryViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combotype;

    @FXML
    private ListView<String> lstfllr;

    @FXML
    private ListView<String> lststatus;

    @FXML
    private TextField txtmob;
    @FXML
    private RadioButton chkregular;

    @FXML
    private ToggleGroup name;

    @FXML
    private RadioButton chkrandom;

    @FXML
    private TextField txtvehicle;
    Connection con;
    PreparedStatement pst;

    @FXML
    void doclear(ActionEvent event) {
    	 Alert confirm=new Alert(AlertType.CONFIRMATION);
     	confirm.setTitle("Clearing..");
     	confirm.setContentText("R U sure?");
     	Optional<ButtonType> res= confirm.showAndWait();
     	if(res.get()==ButtonType.OK)
     			System.exit(1);
    }

    @FXML
    void dosave(ActionEvent event) {
    	String mob=txtmob.getText();
    	String vn=txtvehicle.getText();
    	String vtype=combotype.getSelectionModel().getSelectedItem();
    	String flr=lstfllr.getSelectionModel().getSelectedItem();
    	String ctype="";
        if(chkregular.isSelected()==true)
        	ctype="regular";
        else
        	ctype="random";
    	
    	int status=1;
    
    	try{
    		PreparedStatement pst=con.prepareStatement("insert into parking values(0,?,?,?,?,1,curtime(),curdate())");	
    		//pst.setInt(1, rid);
    		pst.setString(1,ctype);
    		pst.setString(2,vn);
    		pst.setString(3,vtype);
    		pst.setString(4,flr);
    	//pst.setString(5,flr);
    		pst.executeUpdate();
    		doUpdateLayout(Integer.parseInt(flr));
    		System.out.println("saved..");
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    }
    	
    	void doUpdateLayout(int floor)
    	{
    	PreparedStatement pst;
    	try{
    		pst=con.prepareStatement("update playout set occupied=occupied+1 where floor=?");
    		pst.setInt(1, floor);
    		pst.executeUpdate();
    		
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    	
    		
    	}
    		
    		
    
    	
      
    

    @FXML
    void doselect(ActionEvent event) 
    {
//    String tw[]={"1","2"};
//    String fw[]={"3","4","5"};
//    
//     if(combotype.getSelectionModel().getSelectedItem().equals("2 wheeler"))
//     {
//    	 lstfllr.getItems().clear();
//    	 lstfllr.getItems().addAll(tw);
//     }
//     else
//     {
//    	 lstfllr.getItems().clear();
//    	lstfllr.getItems().addAll(fw);
//     }
    	 String type=combotype.getSelectionModel().getSelectedItem();
    	 lstfllr.getItems().clear();
    	 lststatus.getItems().clear();
    	 String cap=combotype.getSelectionModel().getSelectedItem();
    	 
    	 
    	 try{
    		 PreparedStatement pst=con.prepareStatement("select * from playout where type=?");
    		 pst.setString(1, type);
    		 ResultSet rs=pst.executeQuery();
    		 while(rs.next())
    		 {
    			String Floor=rs.getString("Floor");
    			lstfllr.getItems().add(Floor);
    			String r=rs.getString("capacity");
    			String o=rs.getString("occupied");
    			int i=Integer.parseInt(r)-Integer.parseInt(o);
    			lststatus.getItems().add(String.valueOf(i));
    			
    		 }
    	 }
    		 catch(SQLException e){
    			 
    		 }
    		 
    	 
     
    }

    @FXML
    void initialize() {
       
String uid[]={"2 wheeler","4 wheeler"};
combotype.getItems().addAll(uid);
con=DBConnection.doConnect();

    }
}
