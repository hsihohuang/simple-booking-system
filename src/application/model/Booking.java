package application.model;

/**
 * Booking對Passenger與SpecificFlight都是一對一的關係，所以Booking分別有一個Passenger與SpecificFlight的屬性，
 * 記錄這筆Booking是哪一個Passenger訂的，以及所訂的是哪一個班機。
 *
 * Booking這個類別是不能自己產生物件的(因為訂位這個動作不會自己發生，是由乘客來做這個動作)，需要透過Passenger的makeBooking函式來執行，
 * 所以Booking類別的建構式是non-public的，也因為Passenger與SpecificFlight需要和Booking建立雙向的連結關係，
 * 所以都有non-public的addLinkToBooking的函式。
 */
public class Booking {
	private Passenger passenger;
	private SpecificFlight theFlight;
																
	Booking(Passenger passenger,  SpecificFlight theFlight){
		//建立Passenger與Booking的雙向連結
		this.passenger = passenger;
		passenger.addLinkToBooking(this);  
		
		//建立SpecificFlight與Booking的雙向連結
		this.theFlight = theFlight;		
		theFlight.addLinkToBooking(this);
	}
	
	String getBookingInfoString(String mode){
		return mode.equals("flight")? this.theFlight.getFlightInfo()+"\n": this.passenger.getPassengerInfo()+"\n";
	}	
}