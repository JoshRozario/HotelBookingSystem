package ass1;



import java.util.LinkedHashMap;
import java.util.Map;
	

public class Hotels {
	public static Map<String,Hotels> all = new LinkedHashMap<String, Hotels>();
	
	public Map<String,Room> roomList = new LinkedHashMap<String,Room>();
    private String name;
    private int freeSingle = 0;
    private int freeDouble = 0;
    private int freeTriple = 0;
    
    
	public Hotels(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getFreeRoom(String size) {
		if (size.equals("1")){
			return freeSingle;
		}
		if (size.equals("2")){
			return freeDouble;
		}
		if (size.equals("3")){
			return freeTriple;
		}
		return 0;
	}

	public void addFreeSingle(String number) {
		
		this.roomList.put(number, new Room(number,"1"));
		this.freeSingle++;
	}


	public void addFreeDouble(String number) {
		
		this.roomList.put(number, new Room(number,"2"));
		this.freeDouble++;
	}



	public void addFreeTriple(String number) {
		
		this.roomList.put(number, new Room(number,"3"));
		this.freeTriple++;
	}
    
    
    

}
