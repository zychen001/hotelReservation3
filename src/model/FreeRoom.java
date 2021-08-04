package model;
import model.MainModel.RoomType;

public class FreeRoom extends Room{
	public FreeRoom(String roomNumber, Double price, RoomType enumeration) {
		super(roomNumber, 0.0, enumeration);
	}
	
	@Override 
	public String toString() {
		return "Free Room: Room number is " + getRoomNumber() +". " + getRoomType() + " bed." +
	" Room price: " + getRoomPrice() + ".";
		
	}
	

}
