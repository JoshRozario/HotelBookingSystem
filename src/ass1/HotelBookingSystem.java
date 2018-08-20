package ass1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class HotelBookingSystem {
	public static Map<String,BookingInfo> bookings = new LinkedHashMap<String, BookingInfo>();
	
	public static void main(String args[]) {
		  Scanner sc = null;
	      try
	      {
	          sc = new Scanner(new File("test.txt"));    // args[0] is the first command line argument
	          Function.process(sc);
	      }
	      catch (FileNotFoundException e)
	      {
	          System.out.println(e.getMessage());
	      }
	      finally
	      {
	          if (sc != null) sc.close();
	      }
		
		
	}
	
}


