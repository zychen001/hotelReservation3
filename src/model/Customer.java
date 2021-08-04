package model;
import java.util.regex.Pattern;

public class Customer {
	public String firstName;
	public String lastName;
	public String email;
	
	public final String emailRegex = "^(.+)@(.+).com$";
	public final Pattern pattern = Pattern.compile(emailRegex);
	
	public Customer(String firstName, String lastName, String email){
		if(!pattern.matches(emailRegex, email)) {
			throw new IllegalArgumentException("Error, Invalid email");
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Name: " + firstName + " " + lastName + ". Email: " + email + ".";
	}
	

	
	

}
