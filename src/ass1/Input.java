package ass1;

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

	void command(String[] request){								//below are index numbers and respective meaning for each command
		
		if (request[0].equals("Booking")){							//0=command 1=bookingName 2-4=date 5-6=room booking
			//Enter code				  							//7-8(possible)=second room booking
			
		}else if (request[0].equals("Hotel")){						//0=command 1=Hotel 2=roomNumb 3=capacity
			for (Hotels hotel: Hotels.all){ 						// for each currently known/registered hotel and check 
																	//whether hotel already exists
				if (hotel.name.equals(request[1])){ 				//if it does exist add room
					for (Room room : hotel.roomList) {
						if (room.number.equals(request[2])){
							System.out.println("room already exists maybe?");
							return ;//MIGHT BE WRONG YOU MIGHT WANNA CHANGE
						}
					}
					System.out.println("added new room to hotel: "+ request[1]);
					hotel.roomList.add(new Room(request[2],(Integer.parseInt(request[3]))));
					return;
				}												//if it doesn't create a new one and add room
				System.out.println("loop didn do nuffin");
				//return;
			}
			System.out.println("adding new hotel: "+ request[1]);
			Hotels newHotel = new Hotels();
			newHotel.name = request[1];
			newHotel.roomList.add(new Room(request[2],(Integer.parseInt(request[3]))));
			Hotels.all.add(newHotel);
			return;
		}else if (request[0].equals("Change")){//0=command 1=bookingName 2-4=date 5-6=room booking 
			//Enter code
			 
		}else if (request[0].equals("Cancel")){//0=command 1=bookingName
			//Enter code
			
		}else if (request[0].equals("Print")){//0=command 1=Hotel
			//Enter code
		}else{
			//Enter code
		}
		
	}

}
