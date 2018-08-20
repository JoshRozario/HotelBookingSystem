
package ass1;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookingInfo {
	private String name;
	private String hotel;
	private LocalDate bookS;
	private LocalDate bookE;
	public ArrayList<String> rooms = new ArrayList<String>();
	public BookingInfo(String name, String hotel,LocalDate bookS, LocalDate bookE) {
		this.name = name;
		this.hotel = hotel;
		this.bookS = bookS;
		this.bookE = bookE;
	}
	public String getName() {
		return name;
	}
	public String getHotel() {
		return hotel;
	}
	public LocalDate getBookS() {
		return bookS;
	}
	public LocalDate getBookE() {
		return bookE;
	}
	
}
