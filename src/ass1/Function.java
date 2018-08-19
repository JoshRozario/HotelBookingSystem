package ass1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;


public class Function {

	/**
	 * @param request
	 * @return 
	 */
	static BookingInfo book(String[] request) {
		//Enter code				  							//7-8(possible)=second room booking
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMM").withLocale(Locale.ENGLISH);
		TemporalAccessor accessor = parser.parse(request[2]);
		int month = accessor.get(ChronoField.MONTH_OF_YEAR);
		int day = Integer.parseInt(request[3]);
		int length = Integer.parseInt(request[4]);
	
		
		LocalDate bookStart = LocalDate.of(2018, month, day );
		LocalDate bookEnd = bookStart.plusDays(length);
		String room1Cap = request[5].replaceAll("single","1" );
		room1Cap = room1Cap.replaceAll("double","2" );
		room1Cap = room1Cap.replaceAll("triple","3" );
		int room1Amt = Integer.parseInt(request[6]);
		
		String room2Cap = "1"; //placeholder
		int room2Amt = -99;
		//System.out.println(room1Cap);
        if(request.length == 9){
        	room2Cap = request[7].replaceAll("single","1" );
    		room2Cap = room2Cap.replaceAll("double","2" );
    		room2Cap = room2Cap.replaceAll("triple","3" );
    	    room2Amt = Integer.parseInt(request[8]);
    	    
    		//System.out.println(room2Cap);
        }
        for (Hotels freeH: Hotels.all.values()){			//ADD WAY TO ADD SECOND ROOM
        	if(freeH.getFreeRoom(room1Cap) >= room1Amt && ((freeH.getFreeRoom(room2Cap)-1) >= room2Amt ) ){
        		BookingInfo info = new BookingInfo(request[1], freeH.name);
        		if(request.length == 9){
        			request[5] = request[7];
        			request[6] = request[8];
        			String[] request2 = new String[7];
        			System.arraycopy( request, 0, request2, 0, request2.length );
        			info = Function.book(request2);
        		}
        		for (Room freeR: freeH.roomList.values()){
        			if(freeR.capacity.equals(room1Cap)){
        				if (freeR.Bookings.isEmpty()){
	    					//System.out.println("Booking " + request[1] +" "+ freeH.name + " " + freeR.number);
	    					freeR.Bookings.add(new Booking(request[1], bookStart, bookEnd));
	    					info.rooms.add(freeR.number);
	    					//add to list of bookings
	    					return info;//maybe remove if second number is there
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
	    						info.rooms.add(freeR.number);
	    						return info;
	    					}
	    					
	        			}
        			}
        		}
        	}
        }
        //System.out.println("rejected");
		return new BookingInfo(room2Cap, room2Cap);

		

		
		
	}
	/**
	 * @param request
	 */
	static void createHotel(String[] request) {
		for (Map.Entry<String, Hotels> hotel: Hotels.all.entrySet()){ 						// for each currently known/registered hotel and check 
			//System.out.println("Searching for hotel");			//whether hotel already exists
			if (hotel.getKey().equals(request[1])){ 				//if it does exist add room
				//System.out.println("Hotel exists");
				for (String room : hotel.getValue().roomList.keySet()) {
					if (room.equals(request[2])){
					//	System.out.println("room already exists maybe?");
						return ;//MIGHT BE WRONG YOU MIGHT WANNA CHANGE
					}
				}
				//System.out.println("added new room to hotel: "+ request[1]);
				allocRoom(hotel.getValue(), request);
				return;
			}													//if it doesn't create a new one and add room
			//System.out.println("Didn't find hotel");
		}
		//System.out.println("adding new hotel: "+ request[1]);
		Hotels newHotel = new Hotels(request[1]);
		allocRoom(newHotel, request);
		Hotels.all.put(request[1],newHotel);
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
	
	private static void allocRoom(Hotels hotel, String[] request){  
		if(request[3].equals("1")){
			hotel.addFreeSingle(request[2]);
		}else if (request[3].equals("2")){
			hotel.addFreeDouble(request[2]);
		}else if(request[3].equals("3") ){
			hotel.addFreeTriple(request[3]);
		}
	}
	
	private static void checkSpace(int size,Hotels hotel){
		
	}
	
	
}
