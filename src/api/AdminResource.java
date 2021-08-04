package api;
import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.*;

public class AdminResource {
	private static AdminResource adminresource;
	
	CustomerService customerservice = CustomerService.getInstance();
	ReservationService reservationservice = ReservationService.getInstance();
	
	public static AdminResource getInstance() {
		if(adminresource == null) {
			adminresource = new AdminResource();
		}
		return adminresource;
	}
	
	public Customer getCustomer(String email) {
		return customerservice.getCustomer(email);
	}
	
	public void addRoom(List<IRoom> rooms) {
		reservationservice.rooms.addAll(rooms);
	}
	
	public Collection<IRoom> getAllRooms(){
		return reservationservice.rooms;
	}
	
	public Collection<Customer> getAllCustomers(){
		return customerservice.cusList;
	}
	
	public void displayAllReservations() {
		for(Reservation reser : reservationservice.reserList) {
			System.out.println(reser);
		}
	}

}
