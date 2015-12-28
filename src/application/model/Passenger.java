package application.model;

import java.util.ArrayList;
import java.util.Iterator;
import application.model.Booking;

/**
 * Passenger與Booking一對多的關係，所以Passenger的屬性中有一個Booking型別的ArrayList來存放該乘客所有的Booking。
 */
public class Passenger {
	private String name;
	private String cellphone;
	private ArrayList<Booking> bookingList = new ArrayList<Booking>(0);
	
	public Passenger(String name, String cellphone){
		this.name = name;
		this.cellphone = cellphone;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPassengerInfo(){
		return "Name: "+this.name +", Cellphone: "+ this.cellphone;
	}
	
	public String getAllBookingOfPassenger(){ //取得該乘客所有訂票資訊
		String result = "";
		if(this.bookingList.isEmpty())
			result = "您沒有任何訂單!!";
		else{
			result = getPassengerInfo()+"\n\n";
			for (Iterator<Booking> iterator =  this.bookingList.iterator();iterator.hasNext();)
				result += iterator.next().getBookingInfoString("flight");
		}			
		return result;
	}
	
	public void makeBooking(SpecificFlight theFlight){  //乘客訂票
		new Booking(this, theFlight);
	}
	
	void addLinkToBooking(Booking bookOne){  //與Booking建立Link
		bookingList.add(bookOne);
	}	
}
