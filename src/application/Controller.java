package application;

//import statements
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Controller {
	
	//properties from the fxml form
	@FXML Button btnLogFish;
	@FXML RadioButton rdSMBass;
	@FXML RadioButton rdLMBass;
	@FXML RadioButton rdPike;
	@FXML RadioButton rdBluegill;
	@FXML RadioButton rdPerch;
	@FXML RadioButton rdCrappie;
	@FXML ToggleGroup FishSpecies;
	@FXML TextField txtFishWeight;
	@FXML TextField txtFishLength;
	@FXML Label lblBottomLabel;
	@FXML TableView<Fish> fishDataTable;
	@FXML TableColumn<Fish, Integer> col_FishNumber;
	@FXML TableColumn<Fish, String> col_FishSpecies;
	@FXML TableColumn<Fish, Double> col_FishWeight;
	@FXML TableColumn<Fish, Integer> col_FishLength;
	@FXML MenuItem menuHelp;
	@FXML Button btnClose;
	@FXML MenuItem menuClose;
	
	//fields used to hold values from the form
	private String fishWeight;
	private String fishLength;
	private String fishSpecies; 
	
	//method to handle button click event
	public void LogButtonClicked(){
		
		//checks if weight text box is empty
		if(txtFishWeight.getText().isEmpty()) {
			
			//throws message box if true
			infoBox("Weight cannot be empty", "Empty Weight", null);
		}
		
		//allows user to move to next text box, then checks for next empty text box
		else {
			try{
				
				//takes text from text field, stores it in string variable
				fishWeight = txtFishWeight.getText();
				
				//checks to confirm entered text is a valid number
				Double.parseDouble(fishWeight);
			}
			
			//throws message box if number is not valid
			catch(NumberFormatException e){
				infoBox("Please enter a valid weight", "Not a valid weight", null);
			}
			//checks for empty text box, throws message if empty
			if(txtFishLength.getText().isEmpty()) {
				infoBox("Length cannot be empty", "Empty Length", null);
			}
			
			//if successful, allows user to select fish species
			else {
				try {
					
				//takes text from text field, stores it in string variable
				fishLength = txtFishLength.getText();
				
				//checks to confirm entered text is a valid number
				Integer.parseInt(fishLength);
				}
				
				//throws message box if number is not valid
				catch(NumberFormatException e){
					infoBox("Please use whole numbers for length", "Number is not whole number", null);
				}
				//checks for empty species selection
				if(FishSpecies.getSelectedToggle()==null) {
					
					//throws message if the selection is not made
					infoBox("Species cannot be empty", "Empty Species", null);
				}
				
				//if selection is made, stores fish species in variable and calls methods
				else if(rdSMBass.isSelected()) {
					fishSpecies = "Smallmouth Bass";
					
					//sets text at bottom of application to confirm successful log
					lblBottomLabel.setText(fishSpecies + " has been logged.");
					
					//method stores fish data to the database
					LogFishData();
					
					//sets the table on the form with data from the database
					SetTableData();
				}
				
				//if selection is made, stores fish species in variable and calls methods
				else if(rdLMBass.isSelected()) {
					fishSpecies = "Largemouth Bass";
					
					//sets text at bottom of application to confirm successful log
					lblBottomLabel.setText(fishSpecies + " has been logged.");
					
					//method stores fish data to the database
					LogFishData();
					
					//sets the table on the form with data from the database
					SetTableData();
				}
				
				//if selection is made, stores fish species in variable and calls methods
				else if(rdPike.isSelected()) {
					fishSpecies = "Pike";
					
					//sets text at bottom of application to confirm successful log
					lblBottomLabel.setText(fishSpecies + " has been logged.");
					
					//method stores fish data to the database
					LogFishData();
					
					//sets the table on the form with data from the database
					SetTableData();
				}
				
				//if selection is made, stores fish species in variable and calls methods
				else if(rdBluegill.isSelected()) {
					fishSpecies = "Bluegill";
					
					//sets text at bottom of application to confirm successful log
					lblBottomLabel.setText(fishSpecies + " has been logged.");
					
					//method stores fish data to the database
					LogFishData();
					
					//sets the table on the form with data from the database
					SetTableData();
				}
				
				//if selection is made, stores fish species in variable and calls methods
				else if(rdPerch.isSelected()) {
					fishSpecies = "Perch";
					//sets text at bottom of application to confirm successful log
					lblBottomLabel.setText(fishSpecies + " has been logged.");
					
					//method stores fish data to the database
					LogFishData();
					
					//sets the table on the form with data from the database
					SetTableData();
				}
				
				//if selection is made, stores fish species in variable and calls methods
				else if(rdCrappie.isSelected()) {
					fishSpecies = "Crappie";
					
					//sets text at bottom of application to confirm successful log
					lblBottomLabel.setText(fishSpecies + " has been logged.");
					
					//method stores fish data to the database
					LogFishData();
					
					//sets the table on the form with data from the database
					SetTableData();
				}			
			}
		}
	}
	
	//method to create a simple message box to communicate errors to user
	private static void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
	
	//method to store data to the database
	public  void LogFishData() {
		
		//initializes connection, set to null to be changed later
		Connection conn = null;
		
		//attempts to make connection with database
		try {
			
			//strings to send to database to allow database entry
			String url = "jdbc:mysql://localhost:3306/mydb";
			String username = "root";
			String password = "root";
			conn = DriverManager.getConnection(url, username, password);
			
			//if connection is successful, store values from variables into database
			try {
				PreparedStatement statement = conn.prepareStatement("insert into fish(weight, length, fishspecies) values(?,?,?)");
				statement.setString(1, fishWeight);
				statement.setString(2,fishLength);
				statement.setString(3, fishSpecies);
				statement.executeUpdate();
			}
			
			//if connection fails, throws error to console and error to user
			catch (SQLException e){
				System.out.println(e);
				infoBox(e.toString(), "Database entry error", null);
			}
		}
		
		//if connection fails, throws error to console and error to user
		catch (SQLException e) {
			System.out.println(e);
			infoBox(e.toString(), "Database connection Error", null);
		}
	}
	
	//method to set the table on form
	public void SetTableData() {
		
		//sets the columns for the table in the form
		col_FishNumber.setCellValueFactory(new PropertyValueFactory<Fish, Integer>("fishNum"));
		col_FishSpecies.setCellValueFactory(new PropertyValueFactory<Fish, String>("Species"));
		col_FishWeight.setCellValueFactory(new PropertyValueFactory<Fish, Double>("weight"));
		col_FishLength.setCellValueFactory(new PropertyValueFactory<Fish, Integer>("length"));
		
		//attempts calls getFish method to gather data from database
		try {
			fishDataTable.setItems(getFish());
			
		//if getFish method fails, throws error to console and to user
		} catch (SQLException e) {
			System.out.println(e);
			infoBox(e.toString(), "Database retrieval error", null);
		}
	}
	
	//method to gather data from database and store it in an observable list
	private ObservableList<Fish> getFish() throws SQLException {
		
		//initializes observable list to hold collection of fish data
		ObservableList<Fish> data = FXCollections.observableArrayList();
		
		//attempts to connect to database and grab data that has been logged
		try {
			
			//attempts database connection
			Connection conn = null;
			String url = "jdbc:mysql://localhost:3306/mydb";
			String username = "root";
			String password = "root";
			conn = DriverManager.getConnection(url, username, password);
			
			//string to pass to database to retrieve data
			String sql = "Select * from fish";
			
			//creates a result set to hold multiple rows of data
			ResultSet rs = conn.createStatement().executeQuery(sql); 
			
			//loops throw data set and stores them in the observable list
			while(rs.next()) {
				
				//retrieves columns in set to apply them to the current fish
				int num = rs.getInt("Fishid");
				double weight = rs.getDouble("weight");
				int length = rs.getInt("length");
				String species = rs.getString("fishSpecies");
				
				//creates new instance of fish and stores the data upon instantiation
				Fish fish = new Fish(num, weight, length, species);
				
				//adds the current fish into the observable list
				data.add(fish);
			}
		}
		
		//if connection fails, throws message to console and user
		catch(SQLException e){
			System.out.println(e);
			infoBox(e.toString(), "Database retrieval error", null);
		}
		
		//returns the data set to the method call
		return data;
	}
	public void Help() {
		infoBox("Welcome to my app! With this app, you can log your fish to"
				+ " a database and then see the entire listing of"
				+ " your fish brought back to you. You must enter a length,"
				+ " width, and species in order to log your fish. Enter the data"
				+ " and click the 'log fish' button and away you go!"
				+ " Good luck fishing!", "Application Help", null);
	}

	public void Close() {
		System.exit(0);
	}
}
