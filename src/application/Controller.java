package application;

import java.io.IOException;
import application.model.Passenger;
import application.model.SpecificFlight;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {	
	/**
	 * Controller.java是負責處理介面上所有與user的互動，包括場景的切換，監聽user對按鈕、選單的點擊、設定要顯示的資訊等等，
	 * 是model(程式運算)與view(介面)的溝通橋樑。
	 * 
	 * 有@FXML標記代表該變數名稱是在fxml裡的fx:id，如showText 是在fxml裡fx:id="shoeText"的Text元件
	 */
	@FXML
	private Text studentID,showText,finishedBookText,showPassengerTexts;
	@FXML
	private TextField nameTextField, cellphoneTextField, searchNameTextField;
	@FXML
	private ChoiceBox<String> chooseFlightBox;
	
	static private Passenger tmpPassenger;
	static private SpecificFlight tmpFlight;
	
	@FXML
	public void bookAction(ActionEvent event){
		changeScene(event, "view/Register.fxml");
	}
	
	@FXML
	public void registerAction(ActionEvent event){
		String name = nameTextField.getText();
		String cellPhone = cellphoneTextField.getText(); 
	    Passenger passenger = new Passenger(name, cellPhone);
	    tmpPassenger = passenger;
	    if(Main.passengerMap.get(name)==null)
	    	Main.passengerMap.put(name, passenger);	    
	    Parent root = changeScene(event, "view/ChooseTheFlight.fxml");
	    
		chooseFlightBox = (ChoiceBox<String>) root.getChildrenUnmodifiable().get(4);
		chooseFlightBox.setItems(FXCollections.observableArrayList(Main.flightInfoArray));
			
		chooseFlightBox.getSelectionModel().selectedIndexProperty().addListener(new	
		        ChangeListener<Number>() {
			        public void changed(ObservableValue ov,
		                Number value, Number new_value) {
			        		tmpFlight = (Main.specificFlightMap.get((int)ov.getValue()+1));
			        }
		});		
	}
	


	@FXML
	public void toBookAction(ActionEvent event){
		Parent root = changeScene(event, "view/Done.fxml");
		finishedBookText = (Text) root.getChildrenUnmodifiable().get(4);
		finishedBookText.setText(tmpPassenger.getPassengerInfo()+"\n"+tmpFlight.getFlightInfo());
	}
	
	@FXML
	public void searchYourOrderAction(ActionEvent event){
		changeScene(event, "view/SearchOrderAndShow.fxml");
	}
	
	@FXML
	public void searchOrderAction(ActionEvent event){
		String result = "";
		tmpPassenger = Main.passengerMap.get(searchNameTextField.getText());
		if(tmpPassenger== null)
			result = "您沒有註冊，也沒有任何訂單!!";
		else{
			result = tmpPassenger.getAllBookingOfPassenger();
		}
		showText.setText(result);
	}	
		
	@FXML
	public void backToMainAction(ActionEvent event){
		changeScene(event, "view/Main.fxml");
	}
	
	@FXML
	public void backToMainAndAddBookAction(ActionEvent event){
		tmpPassenger.makeBooking(tmpFlight);
		changeScene(event, "view/Main.fxml");
	}
	
	@FXML
	public void searchPassengerAction(ActionEvent event){	
		
		Parent root = changeScene(event, "view/SearchPassengerAndShow.fxml");

		chooseFlightBox = (ChoiceBox<String>) root.getChildrenUnmodifiable().get(1);
		chooseFlightBox.setItems(FXCollections.observableArrayList(Main.flightInfoArray));
			
		chooseFlightBox.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
		            public void changed(ObservableValue ov,
		                    Number value, Number new_value) {
		            			tmpFlight = Main.specificFlightMap.get((int)ov.getValue()+1);
		                        String result = tmpFlight.getAllBookingOfSpecificFlight();
		                        showPassengerTexts = (Text) root.getChildrenUnmodifiable().get(2);
		                        showPassengerTexts.setText(result!= ""? result: "沒有任何乘客");
		            }
		    });
	}
	
	public Parent changeScene(ActionEvent event,String fxml ){
		Parent root = null;
		try{ 
			Node node=(Node)event.getSource();
			Stage stage=(Stage)node.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource(fxml));
			Scene scene = new Scene(root, 400, 410);
			stage.setScene(scene);
			stage.show();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return root;
	}
}
