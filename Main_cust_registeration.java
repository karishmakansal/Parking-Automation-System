package customerreg;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root=FXMLLoader.load(getClass().getResource("customerregistration.fxml"));
			Scene scene=new Scene(root,582,537);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	public static void main(String[] args) {
      launch(args);
	}
	  
}
