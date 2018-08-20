package ass1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;


public class Function {

	/** this function books rooms in a hotel under a bookingname
	 * @param request has details which are needed to book
	 * @return 
	 */
	static BookingInfo book(String[] request) {
						  							//7-8(possible)=second room booking
		//formats date input from request
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMM").withLocale(Locale.ENGLISH);
		TemporalAccessor accessor = parser.parse(request[2]);
		int month = accessor.get(ChronoField.MONTH_OF_YEAR);
		int day = Integer.parseInt(request[3]);
		int length = Integer.parseInt(request[4]);
	
		//gathers booking information
		LocalDate bookStart = LocalDate.of(2018, month, day );
		LocalDate bookEnd = bookStart.plusDays(length);
		String room1Cap = request[5].replaceAll("single","1" );
		room1Cap = room1Cap.replaceAll("double","2" );
		room1Cap = room1Cap.replaceAll("triple","3" );
		int room1Amt = Integer.parseInt(request[6]);
		
		String room2Cap = "1"; 
		int room2Amt = -99;
		//if second room being booked gather details for that as well
        if(request.length == 9){
        	room2Cap = request[7].replaceAll("single","1" );
    		room2Cap = room2Cap.replaceAll("double","2" );
    		room2Cap = room2Cap.replaceAll("triple","3" );
    	    room2Amt = Integer.parseInt(request[8]);
    	    if(room2Cap.equals(room1Cap)){
    	    	room2Amt++;
    	    }
    		//System.out.println(room2Cap);
    	    
        }
        //checks hotels that satisfy booking details
        for (Hotels freeH: Hotels.all.values()){		
        	//checks wheter enough free rooms exist in hotel
        	if(freeH.getFreeRoom(room1Cap) >= room1Amt && ((freeH.getFreeRoom(room2Cap)) >= room2Amt ) ){
        		BookingInfo info = new BookingInfo(request[1], freeH.getName(), bookStart, bookEnd);
        		if(request.length == 9){//books second room 
        			request[5] = request[7];
        			request[6] = request[8];
        			String[] request2 = new String[7];
        			System.arraycopy( request, 0, request2, 0, request2.length );
        			info = Function.book(request2);
        		}
        		//checks wheter not booked during requested dates
        		for (Room freeR: freeH.roomList.values()){
        			if(freeR.getCapacity().equals(room1Cap)){
        				if (freeR.Bookings.isEmpty()){
	    					freeR.Bookings.add(new Booking(request[1], bookStart, bookEnd));
	    					info.rooms.add(freeR.getNumber());
	    					//add to list of bookings
	    					return info;
	        			}
        			    boolean free = true;
	        			for(Booking BookingC: freeR.Bookings){
	    					if (bookStart.isAfter(BookingC.getBookS())&&bookStart.isBefore(BookingC.getBookE())){
	    						free = false;
	    					}if (bookEnd.isAfter(BookingC.getBookS())&&bookStart.isBefore(BookingC.getBookE())){
	    						free = false;
	    					}
	    					
	        			}
	        			if (free == true){
    						
    						freeR.Bookings.add(new Booking(request[1], bookStart, bookEnd));
    						info.rooms.add(freeR.getNumber());
    						
    						return info;
    					}
        			}
        		}
        	}
        }
        //if appears here it has been rejected
		return new BookingInfo(room2Cap, room2Cap, bookEnd, bookEnd);

		

		
		
	}
	/**
	 * this function creates hotels and rooms
	 * @param request 
	 */
	static void createHotel(String[] request) {
		for (Map.Entry<String, Hotels> hotel: Hotels.all.entrySet()){// for each currently known/registered hotel and check 
																	//whether hotel already exists
			if (hotel.getKey().equals(request[1])){ 				//if it does exist add room
		
				for (String room : hotel.getValue().roomList.keySet()) {
					if (room.equals(request[2])){
						return ;
					}
				}
				//System.out.println("creating room ");
				allocRoom(hotel.getValue(), request);//
				return;
			}													//if it doesn't create a new hotel and add room
		}
		
		Hotels newHotel = new Hotels(request[1]);
		allocRoom(newHotel, request);
		Hotels.all.put(request[1],newHotel);
		return;
	}
	/**
	 * this function removes bookings given a booking name
	 * @param request
	 */
	public static void removeBooking(String[] request){
		
		BookingInfo currB = HotelBookingSystem.bookings.get(request[1]);
		if (currB == null){
			System.out.println("Cancel rejected");
			return;
		}
		Hotels currH = Hotels.all.get(currB.getHotel());
		for(String room : currB.rooms){
			Room change = currH.roomList.get(room);
			ListIterator<Booking> iter = change.Bookings.listIterator();
			while(iter.hasNext()){
				if(iter.next().getName().equals(request[1])){
					iter.remove();
					
				}
			}
		}
		
		HotelBookingSystem.bookings.remove(request[1]);
	}
	
	/**this function prints out all the rooms of a hotel and all related bookings
	 * @param request
	 */
	public static void print(String[] request) {
		Hotels hotelBookings = Hotels.all.get(request[1]);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd");
		for(Room Rooms: hotelBookings.roomList.values()){
		   if (Rooms.Bookings.size()>0){
			   System.out.printf(request[1]+ " " +Rooms.getNumber());
			   Collections.sort(Rooms.Bookings);
			   for(Booking info : Rooms.Bookings){
			
				   LocalDate dateS = info.getBookS();
				   LocalDate dateE = info.getBookE();
				   System.out.printf(" "+ dateS.format(formatter));
				   System.out.printf(" "+dateE.compareTo(dateS));
			   }
			   System.out.println("");
		   }else{
			   System.out.println(request[1]+ " " +Rooms.getNumber());
		   }
		}
	}
	/**
	 * this function takes input and gets rid of comments
	 * @param sc
	 */
	static void parser(Scanner sc) {
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
      	  
      	  if(request != null && request.length>1) {
      		  Input.command(request);
      	  }
      	  
      	  
        }
	}
	/**
	 * adds requested room type to hotel
	 * @param hotel
	 * @param request
	 */
	private static void allocRoom(Hotels hotel, String[] request){  
		if(request[3].equals("1")){
			hotel.addFreeSingle(request[2]);
		}else if (request[3].equals("2")){
			hotel.addFreeDouble(request[2]);
		}else if(request[3].equals("3") ){
			hotel.addFreeTriple(request[2]);
		}
	}

	
	
}
