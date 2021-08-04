package menus;
import model.*;
import service.*;
import api.*;
import java.util.*;
import model.MainModel.RoomType;


public class AdminMenu {
	
	AdminResource adminresource = AdminResource.getInstance();
	
	public void start() {
		boolean keepRunning = true;
		
		try(Scanner scanner = new Scanner(System.in)){
			
			while(keepRunning) {
				try {
					System.out.println("1. See all Customers");
					System.out.println("2. See all Rooms");
					System.out.println("3. See all Reservations");
					System.out.println("4. Add a Room");
					System.out.println("5. Back to Main Menu");
					System.out.println("Please select a number for the menu option");
					int selection = Integer.parseInt(scanner.nextLine());
					switch(selection) {
					
					case 1  :
							Collection<Customer> allCus = new ArrayList<>();
							allCus = adminresource.getAllCustomers();
							for(Customer cus : allCus) {
								System.out.println(cus);
							}
							break;
						
					case 2 :
							Collection<IRoom> allRooms = new ArrayList<>();
							allRooms = adminresource.getAllRooms();
							for(IRoom room : allRooms) {
								System.out.println(room);
							}
							break;
						
					case 3 : 
							adminresource.displayAllReservations();
							break;
						
					case 4 : 
							System.out.println("Add a room");
							//List<IRoom> rooms = new ArrayList<>();							
							Scanner input = new Scanner(System.in);
							RoomType roomType = null;
							String addAnother = "";
							int type = 0;
							do{
								System.out.println("Enter room number");
								String roomNumber = input.nextLine();
								System.out.println("Enter price per night");
								Double roomPrice = input.nextDouble();
								do {
									System.out.println("Enter room type: 1-single bed  2-double bed");
									type = input.nextInt();
									if(type == 1) {
										roomType = RoomType.SINGLE;
									}
									else if(type == 2) {
										roomType = RoomType.DOUBLE;
									}
									else {
										System.out.println("Invalid input");
									}
								}while(type != 1 && type != 2);
								IRoom room = new Room(roomNumber, roomPrice, roomType);
								List<IRoom> roomList = new ArrayList<>();
								roomList.add(room);
								adminresource.addRoom(roomList);
								do{
									System.out.println("Would you like to add another room? y/n");																		
									addAnother = input.nextLine();
								}while(!addAnother.equals("y") && !addAnother.equals("n"));
							}while(!addAnother.equals("n"));
							break;
						
					case 5 :
							MainMenu mainMenu = new MainMenu();
							mainMenu.start();
							
						
						default :
							System.out.println("Please enter a number between 1 and 5");
							break;
						
					}
				}
				finally {}
			}
		}
	}
	
	public void addARoom() {
		Scanner input = new Scanner(System.in);
		RoomType roomType = null;
		String addAnother = "";
		int type = 0;
		while(addAnother.equals("y")) {
			System.out.println("Enter room number");
			String roomNumber = input.nextLine();
			System.out.println("Enter price per night");
			Double roomPrice = input.nextDouble();
			while(type != 1 && type != 2) {
				System.out.println("Enter room type: 1-single bed  2-double bed");
				type = input.nextInt();
				if(type == 1) {
					roomType = RoomType.SINGLE;
				}
				else if(type == 2) {
					roomType = RoomType.DOUBLE;
				}
				else {
					System.out.println("Invalid input");
				}
			}
			IRoom room = new Room(roomNumber, roomPrice, roomType);
			List<IRoom> rooms = new ArrayList<>();
			rooms.add(room);
			adminresource.addRoom(rooms);
			while(!addAnother.equals("y") && !addAnother.equals("n")) {
				System.out.println("Would you like to add another room? y/n");
				addAnother = input.nextLine();
			}
		}
	}
	
	

}
