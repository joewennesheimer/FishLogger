package application;

//import statements
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		//sets stage for form to show
		Parent root = FXMLLoader.load(getClass().getResource("FishLogger.fxml"));
		primaryStage.setTitle("Joe's Fish Logger");
		primaryStage.setScene(new Scene(root, 600, 600));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		
		//launches form
		launch(args);
	}
}
