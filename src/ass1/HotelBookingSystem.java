package ass1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HotelBookingSystem {
	public static ArrayList<Hotels> hotelList = new ArrayList<Hotels>();
	
	public static void main(String args[]){
		 
		  Input convert = new Input();
		  Scanner sc = null;
	      try
	      {
	    	  String[] request;// 
	          sc = new Scanner(new File("test.txt"));    // args[0] is the first command line argument
	          while(sc.hasNextLine()){
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
	        	  
	        	  
	        	  
	          }
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
