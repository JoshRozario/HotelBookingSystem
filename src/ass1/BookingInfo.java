
package ass1;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookingInfo {
	String name;
	String hotel;
	LocalDate bookS;
	LocalDate bookE;
	ArrayList<String> rooms = new ArrayList<String>();
	public BookingInfo(String name, String hotel,LocalDate bookS, LocalDate bookE) {
		this.name = name;
		this.hotel = hotel;
		this.bookS = bookS;
		this.bookE = bookE;
	}
}
