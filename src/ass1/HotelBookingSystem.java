package ass1;

import java.io.File;
import java.io.FileNotFoundException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.Scanner;

public class HotelBookingSystem {
	public static ArrayList<String> bookings = new ArrayList<String>();
	
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
		
		/**System.out.println(Hotels.all.isEmpty());
	  	for(Hotels loop : Hotels.all) {
	  		System.out.println(loop.getFreeDouble());
	  		
		}
		**/
	}
	
}


