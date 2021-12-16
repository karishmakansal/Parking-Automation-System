package VehicleExit;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import connectdb.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class VehicleExitViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtvn;

    @FXML
    private TextField txtfllr;

    @FXML
    private TextField csttype;

    @FXML
    private TextField entrytime;

    @FXML
    private TextField entrydate;

    @FXML
    private TextField txtctime;

    @FXML
    private TextField txtcdate;

    @FXML
    private TextField txtamt;
    Connection con;
    PreparedStatement pst;

    @FXML
    void FetchAlll(ActionEvent event) {
    	String vehicleno=txtvn.getText();
    	try{
        	pst=con.prepareStatement("select * from parking where =?");
        	pst.setString(1,vehicleno );
        	
        		ResultSet table= pst.executeQuery();
        		boolean jasus=false;
        		while(table.next())
        		{
        			jasus=true;
        			String vehno=table.getString("vehicleno");
        			String flr=table.getString("Floor");
        			String date=table.getString("edate");
        			String time=table.getString("etime");
        			String type=table.getString("ctype");
        			//String image=table.getString("pic");
        			//path=table.getString("pic");
        			
        			
        			System.out.println(vehno+"  "+flr+"  "+date+"   "+time+" "+type+" ");
        			txtfllr.setText(flr);
        			entrydate.setText(date);
        			entrytime.setText(time);
        			csttype.setText(type);
        			//FileInputStream fin=new FileInputStream(path);
        			//Image img=new Image(fin);
        			
        			//imgview.setImage(img);
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
    void doBill(ActionEvent event) {

    }

    @FXML
    void doupdate(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	con=DBConnection.doConnect();

    }
}

