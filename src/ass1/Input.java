package ass1;

public class Input {
	
	String[] parse(String input){
		String[] parts = input.split("\\s+");
		return parts;
		
	}

	Object command(String[] request){//below are index numbers and respective meaning
		
		if (request[0].equals("Booking")){//0=command 1=bookingName 2-4=date 5-6=room booking
			return request[0];			  //7-8(possible)=second room booking
			
		}else if (request[0].equals("Hotel")){//0=command 1=Hotel 2=roomNumb 3=capacity
			for (Hotels hotel: Hotels.all){
				if (hotel.name.equals(request[0])){
					for()
					
				}else{
					Hotels newHotel = new Hotels();
					newHotel.name = request[1];
					newHotel.roomList.add(new Room(request[2],(Integer.parseInt(request[3]))));
				}
			}
			
		}else if (request[0].equals("Change")){//0=command 1=bookingName 2-4=date 5-6=room booking 
			return request[0];
			 
		}else if (request[0].equals("Cancel")){//0=command 1=bookingName
			return request[0];
			
		}else if (request[0].equals("Print")){//0=command 1=Hotel
			return request[0];
			
		}else{
			return("Incorrect command entered");
		}
	}

}
