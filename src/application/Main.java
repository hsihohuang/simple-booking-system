package application;
	
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import application.model.Passenger;
import application.model.SpecificFlight;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


/**
 * Main.java是程式的進入點，會把介面的場景view底下的Main.xml與application.css(css裡目前沒有任何設定)載入，並設定到介面的舞台上。
 * 以static的HashMap, ArrayList作簡易的資料儲存;另可改寫成連接資料庫(寫在model裡),以JDBC來做連接
 * @author HsiHo Huang
 * @date 2015/01/14
 * @version 2.0
 */
public class Main extends Application {
	public static HashMap<String,Passenger> passengerMap = new HashMap<String,Passenger>();
	public static HashMap<Integer,SpecificFlight> specificFlightMap = new HashMap<Integer,SpecificFlight>();
	public static ArrayList<String> flightInfoArray = new ArrayList<String>();
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("view/Main.fxml"));
			Scene scene = new Scene(root,400,410);
			scene.getStylesheets().add(getClass().getResource("view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {	
		setDefaultData();
		
		launch(args);
	}
	
	public static void setDefaultData(){
		SpecificFlight specificFlight1 = new SpecificFlight(1, "2014-11-06 10:30, Destination country: Japan");
		SpecificFlight specificFlight2 = new SpecificFlight(2, "2014-11-08 18:45, Destination country: Japan");
		SpecificFlight specificFlight3 = new SpecificFlight(3,"2014-11-09 22:05, Destination country: Taiwan");
		
		specificFlightMap.put(specificFlight1.getFlightID(),specificFlight1);
		specificFlightMap.put(specificFlight2.getFlightID(),specificFlight2);
		specificFlightMap.put(specificFlight3.getFlightID(),specificFlight3);
		
		Passenger passenger1 = new Passenger("HsiHo", "0912345678");
		passengerMap.put(passenger1.getName(), passenger1);
		passenger1.makeBooking(specificFlight1);
		passenger1.makeBooking(specificFlight3);
		
		buildFlightInfoArray();
	}
	
	public static void buildFlightInfoArray(){
	    Iterator<Integer> iterator = Main.specificFlightMap.keySet().iterator();
	    while(iterator.hasNext())
	    	flightInfoArray.add( Main.specificFlightMap.get(iterator.next()).getFlightInfo());
	}
}
