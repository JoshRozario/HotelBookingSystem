package ass1;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Room {
	boolean booked;
	int capacity;
	String booking;
	String number;
	LocalDate bookStart;
	LocalDate bookEnd;
	public Room(String number, int capacity) {
		super();
		this.capacity = capacity;
		this.number = number;
		
		
	}
}
