package ass1;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

public class Input {
	/**
	 * this function splits user input by white space and returns an array
	 * @param input 
	 * @return String[] this returns the user input split into an array
	 */
	
	String[] parse(String input){
		String[] parts = input.split("\\s+");
		return parts;
		
	}

	static void command(String[] request){								//below are index numbers and respective meaning for each command
		
		if (request[0].equals("Booking")){							//0=command 1=bookingName 2-4=date 5-6=room booking
			//Enter code				  							//7-8(possible)=second room booking
			DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMM").withLocale(Locale.ENGLISH);
			TemporalAccessor accessor = parser.parse(request[2]);
			int month = accessor.get(ChronoField.MONTH_OF_YEAR);
			int day = Integer.parseInt(request[3]);
			int length = Integer.parseInt(request[4]); 
			
			LocalDate bookStart = LocalDate.of(2018, month, day );
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd");
			//String formattedString = bookStart.format(formatter);
			//System.out.println(formattedString + length);
			LocalDate bookEnd = bookStart.plusDays(length);
			
			for (Hotels freeH: Hotels.all){
				for (Room freeR: freeH.roomList){
					if (freeR.Bookings.isEmpty()){
						System.out.println("Booking " + request[1] +" "+ freeH.name + " " + freeR.number);
						freeR.Bookings.add(new Booking(request[1], bookStart, bookEnd));
						return;
					}
					boolean free = true;
					for(Booking BookingC: freeR.Bookings){
						if (bookStart.isAfter(BookingC.bookS)&&bookStart.isBefore(BookingC.bookE)){
							//System.out.println("Booking rejected");
							free = false;
						}if (bookEnd.isAfter(BookingC.bookS)&&bookStart.isBefore(BookingC.bookE)){
							//System.out.println("Booking rejected");
							free = false;
						}
					if (free == true){
						System.out.println("Booking " + request[1] +" "+ freeH.name + " " + freeR.number);
						freeR.Bookings.add(new Booking(request[1], bookStart, bookEnd));
						return;
					}
					}			
				}
			}
			System.out.println("Booking Rejected");
			
			
			
		} else if (request[0].equals("Hotel")){						//0=command 1=Hotel 2=roomNumb 3=capacity
			for (Hotels hotel: Hotels.all){ 						// for each currently known/registered hotel and check 
				//System.out.println("Searching for hotel");			//whether hotel already exists
				if (hotel.name.equals(request[1])){ 				//if it does exist add room
					for (Room room : hotel.roomList) {
						if (room.number.equals(request[2])){
							//System.out.println("room already exists maybe?");
							return ;//MIGHT BE WRONG YOU MIGHT WANNA CHANGE
						}
					}
					//System.out.println("added new room to hotel: "+ request[1]);
					hotel.roomList.add(new Room(request[2],(Integer.parseInt(request[3]))));
					return;
				}													//if it doesn't create a new one and add room
				//System.out.println("Didn't find hotel");
				//return;
			}
			//System.out.println("adding new hotel: "+ request[1]);
			Hotels newHotel = new Hotels();
			newHotel.name = request[1];
			newHotel.roomList.add(new Room(request[2],(Integer.parseInt(request[3]))));
			Hotels.all.add(newHotel);
			return;
			
			
		}else if (request[0].equals("Change")){//0=command 1=bookingName 2-4=date 5-6=room booking 
			String split = "Booking Aarthi Jan 27 3 single 1";
			String[] test = split.split("\\s+");
			System.out.println(test[0]);
			for (Hotels freeH: Hotels.all){
				for (Room freeR: freeH.roomList){
					Iterator<Booking> it = freeR.Bookings.iterator();
					while (it.hasNext()){
						Booking check = it.next();
						if (check.name.equals(request[1])){
							it.remove();
							System.out.println("removed");
							Input.command(test);
							System.out.println("replaced");
						}
						
					}
						
					
				}
			}
			 
		}else if (request[0].equals("Cancel")){//0=command 1=bookingName
			//Enter code
			
		}else if (request[0].equals("Print")){//0=command 1=Hotel
			//Enter code
		}else{
			//Enter code
		}
		
	}

}
