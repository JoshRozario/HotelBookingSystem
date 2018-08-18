package ass1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.util.Scanner;


public class Function {

	/**
	 * @param request
	 */
	static String book(String[] request) {
		//Enter code				  							//7-8(possible)=second room booking
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMM").withLocale(Locale.ENGLISH);
		TemporalAccessor accessor = parser.parse(request[2]);
		int month = accessor.get(ChronoField.MONTH_OF_YEAR);
		int day = Integer.parseInt(request[3]);
		int length = Integer.parseInt(request[4]);
		
		
		
		LocalDate bookStart = LocalDate.of(2018, month, day );
		LocalDate bookEnd = bookStart.plusDays(length);
		
		for (Hotels freeH: Hotels.all){
			for (Room freeR: freeH.roomList){
				if (freeR.Bookings.isEmpty()){
					//System.out.println("Booking " + request[1] +" "+ freeH.name + " " + freeR.number);
					freeR.Bookings.add(new Booking(request[1], bookStart, bookEnd));
					return "Booking " + request[1] +" "+ freeH.name + " " + freeR.number;
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
					//System.out.println("Booking " + request[1] +" "+ freeH.name + " " + freeR.number);
					freeR.Bookings.add(new Booking(request[1], bookStart, bookEnd));
					return "Booking " + request[1] +" "+ freeH.name + " " + freeR.number;
				}
				}			
			}
		}
		return "Booking Rejected";
		//System.out.println("Booking Rejected");
	}
	/**
	 * @param request
	 */
	static void createHotel(String[] request) {
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
				hotel.roomList.add(new Room(request[2],request[3]));
				return;
			}													//if it doesn't create a new one and add room
			//System.out.println("Didn't find hotel");
			//return;
		}
		//System.out.println("adding new hotel: "+ request[1]);
		Hotels newHotel = new Hotels();
		newHotel.name = request[1];
		newHotel.roomList.add(new Room(request[2],request[3]));
		Hotels.all.add(newHotel);
		return;
	}
	
	static void process(Scanner sc) {
		Input convert = new Input();
		while(sc.hasNextLine()){
      	  String[] request = null;//
      	  String input = sc.nextLine();
      	  if(input.contains("#")){
      		  String result = input.substring(0, input.indexOf("#"));
      		  if (result.trim().isEmpty()){
      			  //do nothing
      		  }else{
      			 request = (convert.parse(result));
      		  }
      	  }else{
      		 request =  (convert.parse(input));
      	  }
      	  
      	  if(request != null) {
      		  Input.command(request);
      	  }
      	  
      	  
        }
	}
	
}
