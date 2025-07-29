package busTicket.com.ticketBooking.features;

import java.util.Scanner;

import busTicket.com.ticketBooking.repository.db.Repository;
import busTicket.com.ticketBooking.repository.dto.Bus;
import busTicket.com.ticketBooking.repository.dto.Ticket;
import busTicket.com.ticketBooking.repository.dto.User;

public class UserView {
	private static Scanner sc = new Scanner(System.in);
	private static Repository repo = Repository.getInstance();
	private static int id = 1;

	public void mainMenu() {
		int choice = 0;

		while (choice != 5) {
			System.out.println("1. Register & Login");
			System.out.println("2. Book The Ticket ");
			System.out.println("3. Show History");
			System.out.println("4. Cancellation");
			System.out.println("5. Exit");
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				Register();
				login();
				break;
			case 2:
				bookTicket();
				break;
			case 3:
				showHistory();
				break;
			case 4:
				cancel();
				break;
			case 5:
				break;
			default:
				System.out.println("Enter the number between 1-5");
			}

		}
	}

	private static void Register() {
		String name;
		String email;
		long phone;
		String gender;
		int age;
		String password;

		do {
			System.out.print("Enter your name: ");
			name = sc.nextLine();
		} while (nameValidate(name));

		do {
			System.out.print("Enter your email: ");
			email = sc.nextLine();
		} while (emailValidate(email));

		do {
			System.out.print("Enter your phone number: ");
			while (!sc.hasNextLong()) {
				System.out.println("Invalid input. Please enter digits only.");
				sc.next();
			}
			phone = sc.nextLong();
			sc.nextLine();
		} while (phoneValidate(phone));

		do {
			System.out.print("Enter your gender (Male/Female/Other): ");
			gender = sc.nextLine();
		} while (genderValidate(gender));

		do {
			System.out.print("Enter your age: ");
			while (!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number.");
				sc.next();
			}
			age = sc.nextInt();
			sc.nextLine();
		} while (ageValidate(age));

		do {
			System.out.print("Enter your password: ");
			password = sc.nextLine();
		} while (passwordValidate(password));

		System.out.println("\nRegistration successful!");
		User user = new User(name, email, phone, gender, age, password);
		repo.setUser(user);

		repo.setUserList(id++, user);

	}

	private static boolean nameValidate(String name) {
		return name == null || name.trim().isEmpty();
	}

	private static boolean emailValidate(String email) {
		return email == null || !email.contains("@") || !email.contains(".");
	}

	private static boolean phoneValidate(long phone) {
		return String.valueOf(phone).length() != 10;
	}

	private static boolean genderValidate(String gender) {
		return !(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")
				|| gender.equalsIgnoreCase("Other"));
	}

	private static boolean ageValidate(int age) {
		return age < 1 || age > 120;
	}

	private static boolean passwordValidate(String password) {
		return password == null || password.length() < 6;
	}

	private static void cancel() {
		if(repo.getTicket().isEmpty())
		{
			System.out.println("No Tickets Available");
			return;
		}
		System.out.println("Enter ticket id: ");
		int ticketId=sc.nextInt();
		
		if(!repo.getTicket().containsKey(ticketId))
		{
			System.out.println("Ticket Invalid Id");
			return;
		}
		Ticket ticket = repo.getTicket().get(ticketId);

		if (!ticket.getUserEmail().equals(repo.getUser().getEmail())) {
			System.out.println("You are not authorized to cancel this ticket.");
			return;
		}
		Bus bus = repo.getBusList().get(ticket.getBusId());
		if (bus != null) {
			bus.cancelSeat(ticket.getSeatsBooked());
		}
		repo.getTicket().remove(ticketId);
		
				
	}

	private static void showHistory() {
		System.out.println("Enter user email: ");
		
      String email=sc.nextLine();
      boolean found = false;
      for(Ticket ticket:repo.getTicket().values())
      {
    	  if(ticket.getUserEmail().equals(email))
    	  {
    		  System.out.println(ticket);
    		  found=true;
    	  }
    		 
        if(found)
        {
        System.out.println("No user found");
   		  return;
        }
      }
	}

	private static void bookTicket() {
		if (repo.getBusList().isEmpty()) {
			System.out.println("NO buses available");
			return;
		}
		System.out.println("\n Available Buses");

		for (Bus bus : repo.getBusList().values()) {
			System.out.println(bus);
		}

		System.out.println("Enter the Bus Id to book: ");
		int busId = sc.nextInt();
		if (!repo.getBusList().containsKey(busId)) {
			System.out.println("Invalid ID");
		}

		Bus selected = repo.getBusList().get(busId);
		System.out.println("Enter number of seats to book: ");
		int seats = sc.nextInt();
		if (selected.getAvailableSeats() < seats) {
			System.out.println("Not enough spaces");
			return;
		}
		selected.bookSeats(seats);

		int ticketId = repo.getTicket().size() + 1;
		Ticket ticket = new Ticket(ticketId, busId, repo.getUser().getEmail(), seats);
		repo.setTicket(ticketId, ticket);
		System.out.println("Ticket Booked Successfully");
		System.out.println(ticket);
	}

	private static void login() {
		String mail, password;
		boolean found = false;

		System.out.print("Enter your email: ");
		mail = sc.nextLine();

		System.out.print("Enter your password: ");
		password = sc.nextLine();

		for (User u : repo.getUserList().values()) {
			if (u.getEmail().equals(mail) && u.getPassword().equals(password)) {
				repo.setUser(u); // set the logged-in user
				found = true;
				break;
			}
		}

		if (found) {
			System.out.println("Login successful!");
		} else {
			System.out.println("Invalid email or password.");
		}

		repo.printAllUsers();
	}
}
