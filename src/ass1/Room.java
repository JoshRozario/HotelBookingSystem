package ass1;

import java.util.ArrayList;


public class Room {
	private String capacity;
	private String number;
	public ArrayList<Booking> Bookings = new ArrayList<Booking>();
	public Room(String number, String capacity) {
		super();
		this.capacity = capacity;
		this.number = number;
	}
	public String getCapacity() {
		return capacity;
	}
	public String getNumber() {
		return number;
	}
	
}
