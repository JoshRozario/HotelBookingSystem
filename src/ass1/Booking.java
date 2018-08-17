package ass1;

import java.time.LocalDate;

public class Booking {
	String name;
	LocalDate bookS;
	LocalDate bookE;
	public Booking(String name, LocalDate bookS, LocalDate bookE) {
		this.name = name;
		this.bookS = bookS;
		this.bookE = bookE;
	}
	
}
