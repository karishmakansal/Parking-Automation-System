package CarRegsitration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.image.*;

import connectdb.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class RegistrationViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgview;

    @FXML
    private TextField txtmob;

    @FXML
    private TextField txtadd;

    @FXML
    private TextField txtcity;

    @FXML
    private Button btnbrs;

    @FXML
    private TextField txtname;
    Connection con;
    PreparedStatement pst;
    String path;
    
    @FXML
    void dobrowse(ActionEvent event) throws FileNotFoundException {
    	FileChooser fc=new FileChooser();
    	File file = fc.showOpenDialog(null);
      Image image=new Image(file.toURI().toString());
     // System.out.println("path");
      imgview.setImage(image);
      //FileInputStream fin=new FileInputStream(file.getAbsolutePath());
      path=file.getAbsolutePath();
      System.out.println(path);
      
    }

    @FXML
    void dofetch(ActionEvent event) {
String Mobile=txtmob.getText();
    	try{
        	pst=con.prepareStatement("select * from customer where Mobile=?");
        	pst.setString(1,Mobile );
        	
        		ResultSet table= pst.executeQuery();
        		boolean jasus=false;
        		while(table.next())
        		{
        			jasus=true;
        			String Mobile1=table.getString("Mobile");
        			String Name=table.getString("Name");
        			String Address=table.getString("Address");
        			String City=table.getString("City");
        			//String image=table.getString("pic");
        			path=table.getString("pic");
        			
        			
        			System.out.println(Mobile1+"  "+Name+"  "+Address+"   "+City+" "+path+" ");
        			txtname.setText(Name);
        			txtadd.setText(Address);
        			txtcity.setText(City);
        			FileInputStream fin=new FileInputStream(path);
        			Image img=new Image(fin);
        			
        			imgview.setImage(img);
        		}
        		if(jasus==false)
        			System.out.println("Invalid number");
        		
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}

    }

    @FXML
    void donew(ActionEvent event) {
        txtmob.setText("");
		txtname.setText("");
		txtadd.setText("");
		txtcity.setText("");
		FileInputStream fin;
		try {
			fin = new FileInputStream(new File("userinfo.png"));
			Image img=new Image(fin);
		      imgview.setImage(img);

		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    }

    @FXML
    void dosave(ActionEvent event) {
      String Mobile=txtmob.getText();
        String Name=txtname.getText();
        String Address=txtadd.getText();
        String City=txtcity.getText();
        Image image=imgview.getImage();
       
        
       try{
    	   
    	   PreparedStatement pst=con.prepareStatement("insert into customer values(?,?,?,?,?)");
    	   
    	   pst.setString(1, Mobile);
    	   pst.setString(2, Name);
    	   pst.setString(3, Address);
    	   pst.setString(4, City);
    	   pst.setString(5, path);
    	   pst.executeUpdate();
    	   System.out.println("Saved");   
       }
       catch(SQLException e)
       
       {
       	e.printStackTrace();
       }
    }

    @FXML
    void doupdate(ActionEvent event) {
 String Mobile=txtmob.getText();
           String Name=txtname.getText();
           String Address=txtadd.getText();
           String City=txtcity.getText();
           Image image=imgview.getImage();
           
           
          try{
       	   
       	   PreparedStatement pst=con.prepareStatement("update customer set Name=?,Address=?,City=?,pic=? where Mobile=?");
       	pst.setString(1, Name);
 	   pst.setString(2, Address);
 	   pst.setString(3, City);
 	   pst.setString(4,path );
 	  pst.setString(5, Mobile);
       	  
       	   pst.executeUpdate();
       	   System.out.println("Record updated..");   
          }
          catch(SQLException e)
          
          {
          	e.printStackTrace();
          }
    }

    @FXML
    void initialize() {
        con=DBConnection.doConnect();
    }
}
