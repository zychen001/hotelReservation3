package menus;
import java.text.*;
import java.util.*;
import api.*;
import model.*;
import service.*;


public class MainMenu {
	public void start() {
		boolean keepRunning = true;
		
		HotelResource hotelresource = HotelResource.getInstance();
		ReservationService reservationservice = ReservationService.getInstance();
		CustomerService customerservice = CustomerService.getInstance();
		
		
		try (Scanner scanner = new Scanner(System.in)){
			
			while(keepRunning) {
				try {
					System.out.println("1. Find and reserve a room");
					System.out.println("2. See my reservations");
					System.out.println("3. Create an account");
					System.out.println("4. Admin");
					System.out.println("5. Exit");
					System.out.println("Please select a number for the menu option");
					int selection = Integer.parseInt(scanner.nextLine());
					int stopRun = 0;
					
					switch(selection) { 
						
					case 1 :	
							//input checkIn and checkOut date
							System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2020");
							String checkInString = scanner.nextLine();
							SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
							Date checkInDate = formatter.parse(checkInString);
							System.out.println("Enter CheckOut Date mm/dd/yyyy example 02/01/2020");
							String checkOutString = scanner.nextLine();
							Date checkOutDate = formatter.parse(checkOutString);
							Collection<IRoom> availableRoom = hotelresource.findARoom(checkInDate, checkOutDate);
							//print all available room
							for(IRoom room : availableRoom) {
								System.out.println(room);
							}
							if(availableRoom.size() == 0) {
								
								Calendar calendar1 = new GregorianCalendar();
								Calendar calendar2 = new GregorianCalendar();
								calendar1.setTime(checkInDate);
								calendar2.setTime(checkOutDate);
								calendar1.add(calendar1.DATE, 7);
								calendar2.add(calendar2.DATE, 7);
								checkInDate = calendar1.getTime();
								checkOutDate = calendar2.getTime();
																
								availableRoom = hotelresource.findARoom(checkInDate, checkOutDate);
								
								if(availableRoom.size() == 0) {
									System.out.println("Sorry, no rooms are found, please choose another date");
									break;
								}
								
								System.out.println("Sorry, there are no rooms available for your chosen date, "
										+ "we recommand you to delay your checkInDate/checkOutDate for 7 days and check the following rooms,"
										+ " select 'y' if you accept");
								
								for(IRoom rom : availableRoom) {
									System.out.println(rom);
																	
								}
								
								
							}
							System.out.println("Would you like to book a room? y/n");
							//select y: continue  select n: return to the main menu
							String bookRoom = scanner.nextLine();
							
							if(!bookRoom.equals("y") && !bookRoom.equals("n")) {
								throw new IllegalArgumentException("Please enter 'y' or 'n'");
							}
							
							if(bookRoom.equals("n") || bookRoom.equals("N")) {
								break;
							}
							System.out.println("Do you have an account with us? y/n");
							//select y: continue  select n: create an account
							String anAccount = scanner.nextLine();
							
							if(!anAccount.equals("y") && !anAccount.equals("n")) {
								throw new IllegalArgumentException("Please enter 'y' or 'n'");
							}
							
							//create an account
							if(anAccount.equals("n") || anAccount.equals("N")) {
								System.out.println("Please enter your email");
								String email = scanner.nextLine();
								System.out.println("Please enter your firstName");
								String firstName = scanner.nextLine();
								System.out.println("Please enter your lastName");
								String lastName = scanner.nextLine();
								hotelresource.createACustomer(email, firstName, lastName);
							} 
							
							
							System.out.println("Enter Email Format: name@domain.com");
							String customerEmail = scanner.nextLine();
							Customer customer = customerservice.getCustomer(customerEmail);
							System.out.println("What room number would you like to reserve");
							String roomNumber = scanner.nextLine();
							IRoom roomReser = reservationservice.getARoom(roomNumber);
							Reservation reservation = reservationservice.reserveARoom(customer, roomReser, checkInDate, checkOutDate);
							System.out.println(reservation);
							break;
						
						
					case 2 :
							System.out.println("Please enter your email");
							String cusEmail = scanner.nextLine();
							Collection<Reservation> customerReserCollect = new ArrayList<>();
							customerReserCollect = hotelresource.getCustomersReservations(cusEmail);
							System.out.println(customerReserCollect);
							break;
						
						
					case 3 :
							System.out.println("Please enter your email");
							String email = scanner.nextLine();
							System.out.println("Please enter your firstName");
							String firstName = scanner.nextLine();
							System.out.println("Please enter your lastName");
							String lastName = scanner.nextLine();
							hotelresource.createACustomer(email, firstName, lastName);
							System.out.println("Your account has successfully created");
							System.out.println(hotelresource.getCustomer(email));
							break;
						
					case 4 :
							AdminMenu adminmenu = new AdminMenu();
							adminmenu.start();
							break;
						
					case 5 : 
							System.exit(1);
							
							
						
						default : 
							System.out.println("Please enter a number between 1 to 5");
						
					}
					
				}
				
				catch(Exception ex) {
					System.out.println(ex.getLocalizedMessage());
				}
				finally {}
			}
		
		}
	}
	
	public static void main(String[] args) {
		MainMenu mainMenu = new MainMenu();
		mainMenu.start();
	}
	
}


