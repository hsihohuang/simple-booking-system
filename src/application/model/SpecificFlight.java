package application.model;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * SpecificFlight對Booking是一對多的關係，所以有一個屬性是Booking型別的ArrayList，存放該班機被哪幾筆Booking訂了
 */
public class SpecificFlight {
	private int flightID;
	private String flightInfo;
	private ArrayList<Booking> bookingList = new ArrayList<Booking>(0);

	public SpecificFlight(int flightID, String flightInfo){
		this.flightID = flightID;
		this.flightInfo = flightInfo;
	}
	
	public int getFlightID(){
		return this.flightID;
	}	

	public String getFlightInfo(){
		return this.flightInfo;
	}
	
	public String getAllBookingOfSpecificFlight(){  //取得該班機所有的乘客資料
		String result ="";
		for (Iterator<Booking> iterator =  this.bookingList.iterator();iterator.hasNext();)
			result += iterator.next().getBookingInfoString("passenger");
		return result;
	}
	
	void addLinkToBooking(Booking bookOne){ //與Booking建立Link
		bookingList.add(bookOne);
	}	
}
