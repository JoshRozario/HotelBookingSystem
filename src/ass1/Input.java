package ass1;

public class Input {
	/**
	 * this function splits user input by white space and returns an array
	 * @param input user input 
	 * @return String[] this returns the user input split into an array
	 */
	
	String[] parse(String input){
		String[] parts = input.split("\\s+");
		return parts;
		
	}
	/**
	 * This function takes in user input that has been split into
	 * parts and applies the appropriate functions/methods to either
	 * "register" Hotels, make bookings, change/cancel bookings, or
	 * print hotel booking details
	 * @param request user input that has been split
	 * 
	 */

	static void command(String[] request){	//below are index numbers and respective meaning for each command
		
		if (request[0].equals("Booking")){					//0=command 1=bookingName 2-4=date 5-6=room booking
															//7-8(optional) another room
			BookingInfo test = Function.book(request);
			if (test.rooms.isEmpty()){
				System.out.println("Booking rejected");
				return;
			}
			System.out.printf("Booking %s %s",test.getName(), test.getHotel());
			for (String number : test.rooms){
				System.out.printf(" %s", number);
			}
			System.out.println("");
			HotelBookingSystem.bookings.put(test.getName(), test);
			
		} else if (request[0].equals("Hotel")){				//0=command 1=Hotel 2=roomNumb 3=capacity
			Function.createHotel(request);
			
			
			
		}else if (request[0].equals("Change")){				//0=command 1=bookingName 2-4=date 5-6=room booking 
			Function.removeBooking(request);
			
			BookingInfo attempt = Function.book(request);
			if (attempt.rooms.isEmpty()){
				System.out.println("Booking rejected");
				return;
			}
			System.out.printf("Change %s %s",attempt.getName(), attempt.getHotel());
			for (String number : attempt.rooms){
				System.out.printf(" %s", number);
			}
			System.out.println("");
			HotelBookingSystem.bookings.put(attempt.getName(), attempt);
			
				
			 
		}else if (request[0].equals("Cancel")){//0=command 1=bookingName
			Function.removeBooking(request);
			System.out.println("Cancel " + request[1]);
			
		}else if (request[0].equals("Print")){//0=command 1=Hotel
			Function.print(request);
			
		}else{
			//Enter code
		}
		
		
		
	}
	


}
