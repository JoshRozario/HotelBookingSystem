package ass1;

import java.time.LocalDate;

public class Booking implements Comparable<Booking> {
	String name;
	LocalDate bookS;
	LocalDate bookE;
	public Booking(String name, LocalDate bookS, LocalDate bookE) {
		this.name = name;
		this.bookS = bookS;
		this.bookE = bookE;
	}
	@Override
	public int compareTo(Booking date) {
		return this.bookS.compareTo(date.bookS);
	}
	
}
