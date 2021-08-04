package service;
import java.util.*;
import model.Customer;

public class CustomerService {
	private static CustomerService customerservice;
	public Map<String, Customer> mapOfCustomer;
	public ArrayList<Customer> cusList = new ArrayList<Customer>();
	
	
	
	public static CustomerService getInstance() {
		if(customerservice == null) {
			customerservice = new CustomerService();
		}
		return customerservice;
	}
	
	
	public CustomerService() {
		this.mapOfCustomer = new HashMap<>();
		this.cusList = new ArrayList<>();
	}
	
	
	
	
	
	public void addCustomer(String email, String firstName, String lastName) {
		Customer customer = new Customer(firstName, lastName, email);
		if(!mapOfCustomer.containsKey(email)) {
			mapOfCustomer.put(email, customer);
			cusList.add(customer);
		}
		else {
			System.out.println("This email account had been used.");
		}
	}
		
		
	public Customer getCustomer(String customerEmail) {
		return mapOfCustomer.get(customerEmail);
	}
	
	public Collection<Customer> getAllCustomer(){
		return cusList;
	}
	
	
	
}
