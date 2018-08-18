package ass1;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Room {
	String capacity;
	String number;
	ArrayList<Booking> Bookings = new ArrayList<Booking>();
	//ArrayList<LocalDate> bookStart = new ArrayList<LocalDate>();
	//ArrayList<LocalDate> bookEnd = new ArrayList<LocalDate>();
	public Room(String number, String capacity) {
		super();
		this.capacity = capacity;
		this.number = number;
		
		
		
	}
}
