package Layout;

import java.awt.Label;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import connectdb.DBConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ParkingLayoutViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboflr;
    @FXML
    private Label settype;

    @FXML
    private RadioButton rad2;

    @FXML
    private RadioButton rad4;

    @FXML
    private TextField txtcap;
    Connection con;
    PreparedStatement pst;

    @FXML
    void doclose(ActionEvent event) {
     Alert confirm=new Alert(AlertType.CONFIRMATION);
    	confirm.setTitle("Closing..");
    	confirm.setContentText("R U sure?");
    	Optional<ButtonType> res= confirm.showAndWait();
    	if(res.get()==ButtonType.OK)
    			System.exit(1);
    }
int Occupied=1;


  @FXML
    void doselect(ActionEvent event) {

  }  


	@FXML
    void sosave(ActionEvent event) {
    
        String floor=comboflr.getSelectionModel().getSelectedItem();
       
       int capacity=Integer.parseInt(txtcap.getText());
 System.out.println(capacity);
     
          String type="";
        if(rad2.isSelected()==true)
        	type="2 wheeler";
        else
        	type="4 wheeler";
        try{
        	PreparedStatement pst=con.prepareStatement("insert into playout values(?,?,?,?)");
        	pst.setString(1, floor);
        	pst.setInt(2, capacity);
        	pst.setString(3,type);
        	pst.setInt(4, Occupied);
        	pst.executeUpdate();
        	System.out.println("Lauot saved");
        }
        	catch(SQLException e)
        	{
        		e.printStackTrace();
        	}
        	
        	
       }

    @FXML
    void initialize() {
       String floor[]={"1st","2nd","3rd","4th","5th"};
       comboflr.getItems().addAll(floor);
       
       con=DBConnection.doConnect();
    }
}