/**
 * 
 */
package ass1;

import java.util.ArrayList;

public class BookingInfo {
	String name;
	String hotel;
	ArrayList<String> rooms = new ArrayList<String>();
	public BookingInfo(String name, String hotel) {
		this.name = name;
		this.hotel = hotel;
	}
}
