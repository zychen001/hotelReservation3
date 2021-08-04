package service;
import model.*;
import service.CustomerService;
import java.util.*;


public class ReservationService {
	private static ReservationService reservationservice;
	Map<IRoom,Reservation> reservations = new HashMap<>();
	public List<IRoom> rooms = new ArrayList<>();
	public List<Reservation> reserList = new ArrayList<>();
	public Collection<IRoom> availableRoom = new LinkedHashSet<>();
	
	public static ReservationService getInstance() {
		if(reservationservice == null) {
			reservationservice = new ReservationService();
		}
		return reservationservice;
	}
	
	public void addRoom(IRoom room) {
		rooms.add(room);
	}
	
	public IRoom getARoom(String roomNumber) {
		for(IRoom room : rooms) {
			if(roomNumber.equals(room.getRoomNumber())) {
				return room;
			}
		}
		System.out.println("This is not one of our room");
		return null;
	}
	
	public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
		Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
		System.out.println("This room has been reserved.");

		reserList.add(reservation);

		return reservation;
	}
	
	public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
		Collection<IRoom> availableRooms = new HashSet<>();
		
		
		
		if(reserList.size() == 0) {
			availableRooms = rooms;
			return availableRooms;
		}
		else {
			for(Reservation res : reserList) {
				for (IRoom rom : rooms) {
                    if ((rom.getRoomNumber().equals(res.room.getRoomNumber()))
                            && ((checkInDate.before(res.getCheckInDate()) && checkOutDate.before(res.getCheckInDate()))
                            || (checkInDate.after(res.getCheckOutDate()) && checkOutDate.after(res.getCheckOutDate())))
                    || (!res.room.getRoomNumber().contains(rom.getRoomNumber()))) {
                        availableRooms.add(rom);
                    } else if (rom.getRoomNumber().equals(res.room.getRoomNumber())) {
                        availableRooms.remove(rom);
                    }
                }
			}
		}

		return availableRooms;
	}
	
	public Collection<Reservation> getCustomersReservation(Customer customer){
		Collection<Reservation> customerReserCollect = new ArrayList<>();
		for(Reservation cusReser : reserList) {
			if(cusReser.customer.equals(customer)) {
				customerReserCollect.add(cusReser);
			}
		}
		return customerReserCollect;
	}
	
	public void printAllReservation() {
		for(Reservation reser : reserList) {
			System.out.println(reser);
			
		}
	}
	
	
	

}
	
	


