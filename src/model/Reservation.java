package model;
import java.util.*;
import java.util.regex.Pattern;

public class Reservation {
	public Customer customer;
	public IRoom room;
	public Date checkInDate;
	public Date checkOutDate;
	
	
	
	public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
		
		this.customer = customer;
		this.room = room;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}
	
	public Date getCheckInDate() {
		return checkInDate;
	}
	
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	
	@Override
	public String toString() {
		return "Customer: " + customer + ". Room: " + room + "CheckInDate: " + checkInDate + 
				". CheckOutDate: " + checkOutDate;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(!(o instanceof Reservation)) {
			return false;
		}
		Reservation reservation = (Reservation) o;
		return Objects.equals(this.customer, reservation.customer) && Objects.equals(this.room, reservation.room)
				&& Objects.equals(this.getCheckInDate(), reservation.getCheckInDate()) && Objects.equals(this.getCheckOutDate(), reservation.getCheckOutDate());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(customer, room, checkInDate, checkOutDate);
	}

}
