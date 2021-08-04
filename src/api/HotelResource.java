package api;
import java.util.*;
import service.*;
import model.*;

public class HotelResource {
	private static HotelResource hotelresource;
	
	CustomerService customerservice = CustomerService.getInstance();
	ReservationService reservationservice = ReservationService.getInstance();
	
	public static HotelResource getInstance() {
		if(hotelresource == null) {
			hotelresource = new HotelResource();
		}
		return hotelresource;
	}
	
	public Customer getCustomer(String  email) {
		Collection<Customer> cusCollect = customerservice.getAllCustomer();
		for(Customer cus : cusCollect) {
			if(cus.email.equals(email)) {
				return cus;
			}
		}
		return null;
	}
	
	public void createACustomer(String email, String firstName, String lastName){
		customerservice.addCustomer(email, firstName, lastName);
	}
	
	public IRoom getRoom(String roomNumber) {
		return reservationservice.getARoom(roomNumber);
	}
	
	public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
		Customer cus = getCustomer(customerEmail);
		return reservationservice.reserveARoom(cus, room, checkInDate, checkOutDate);
	}
		
	public Collection<Reservation> getCustomersReservations(String customerEmail){
		Customer cus = getCustomer(customerEmail);
		return reservationservice.getCustomersReservation(cus);
	}
	
	public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
		return reservationservice.findRooms(checkIn, checkOut);
	}
	
	
	
}
