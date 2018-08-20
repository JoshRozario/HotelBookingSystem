package ass1;

import java.time.LocalDate;

public class Booking implements Comparable<Booking> {
	private String name;
	private LocalDate bookS;
	private LocalDate bookE;
	
	public Booking(String name, LocalDate bookS, LocalDate bookE) {
		this.name = name;
		this.bookS = bookS;
		this.bookE = bookE;
	}
	
	
	public String getName() {
		return name;
	}


	public LocalDate getBookS() {
		return bookS;
	}


	public LocalDate getBookE() {
		return bookE;
	}


	@Override
	public int compareTo(Booking date) {
		return this.bookS.compareTo(date.bookS);
	}
	
}
