package model;
//import enums
import model.MainModel.RoomType;

public interface IRoom {
	//methods
	public String getRoomNumber();
	public Double getRoomPrice();
	public RoomType getRoomType();
	public boolean isFree();
	
	
	
	
	

}
