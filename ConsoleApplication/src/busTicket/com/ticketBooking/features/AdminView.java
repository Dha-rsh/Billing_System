package busTicket.com.ticketBooking.features;

import java.util.HashMap;
import java.util.Scanner;

import busTicket.com.ticketBooking.repository.db.Repository;
import busTicket.com.ticketBooking.repository.dto.Admin;
import busTicket.com.ticketBooking.repository.dto.Bus;
import busTicket.com.ticketBooking.repository.dto.User;

public class AdminView {

	private static Scanner sc = new Scanner(System.in);
	private static Repository repo = Repository.getInstance();
	private static int id = 1;

	public void mainMenu() {
		int choice = 0;

		while (choice != 6) {
			System.out.println("1. Register ");
			System.out.println("2. Login ");
			System.out.println("3. Add Buses ");
			System.out.println("4. Show Buses");
			System.out.println("5. Modify Bus");
			System.out.println("6. Logout/ Exit");
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				Register();
				break;
			case 2:
				login();
				break;
			case 3:
				AddBuses();
				break;
			case 4:
				showBuses();
				break;
			case 5:
				ModifyBus();
				break;
			case 6:
				logout();
				break;
			default:
				System.out.println("Enter the number between 1-5");
			}

		}
	}

	private void logout() {
		if (repo.getAdmin() != null) {
			System.out.println("logged out: " + repo.getAdmin().getName());
			repo.setAdmin(null);
		} else {
			System.out.println("no admin is here");
		}

	}

	private void ModifyBus() {
		if (repo.getAdmin() == null) {
			System.out.println("Access denied. You must login first.");
			return;
		}

		System.out.println("Enter the busNo: ");
		int busNo = sc.nextInt();
		sc.nextLine();
		Bus bus = repo.getBusList().get(busNo);
		if (bus == null) {
			System.out.println("Bus not found");
			return;
		}
		System.out.println("Modify Source (current: " + bus.getStartingPoint() + "): ");
		String newStart = sc.nextLine();
		System.out.println("Modify Destination (current: " + bus.getEndingPoint() + "): ");
		String newEnd = sc.nextLine();
		System.out.println("Modify Date (current: " + bus.getDateTime() + "): ");
		String newDate = sc.nextLine();
		System.out.println("Modify Seats (current: " + bus.getTotalSeats() + "): ");
		int newSeats = sc.nextInt();
		sc.nextLine();

		bus.setStartingPoint(newStart);
		bus.setEndingPoint(newEnd);
		bus.setDateTime(newDate);
		bus.setTotalSeats(newSeats);

		System.out.println("Bus details updated!");

	}

	private void showBuses() {
		if (repo.getAdmin() == null) {
			System.out.println("Access denied. You must login first.");
			return;
		}

		HashMap<Integer, Bus> buses = repo.getBusList();

		if (buses.isEmpty()) {
			System.out.println("No buses available");
			return;
		}
		System.out.println(" Available Buses:");
		System.out.println("---------------------------");
		for (HashMap.Entry<Integer, Bus> entry : buses.entrySet()) {
			Bus bus = entry.getValue();
			System.out.println("Bus No: " + bus.getBusNo());
			System.out.println("Route: " + bus.getStartingPoint() + " to " + bus.getEndingPoint());
			System.out.println("Date: " + bus.getDateTime());
			System.out.println("Seats: " + bus.getTotalSeats());
			System.out.println("---------------------------");
		}

	}

	private void AddBuses() {
		if (repo.getAdmin() == null) {
			System.out.println("Access denied. You must login first.");
			return;
		}

		int busNo;
		String start, end;
		String date;
		int seat;

		System.out.println("Enter the busNo: ");
		busNo = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the source point: ");
		start = sc.nextLine();

		System.out.println("Enter the destination point: ");
		end = sc.nextLine();

		System.out.println("Enter the date of jouney: ");
		date = sc.nextLine();

		System.out.println("Enter the total seats: ");
		seat = sc.nextInt();

		Bus bus = new Bus(busNo, start, end, date, seat);
		repo.setBus(busNo, bus);

	}

	private void login() {
		if (repo.getAdminList().isEmpty()) {
			System.out.println("register first");
			return;
		}
		String mail, password;
		boolean found = false;

		System.out.print("Enter your email: ");
		mail = sc.nextLine();

		System.out.print("Enter your password: ");
		password = sc.nextLine();

		for (Admin u : repo.getAdminList().values()) {
			if (u.getEmail().equals(mail) && u.getPassword().equals(password)) {
				repo.setAdmin(u);
				System.out.println("Login successful!");
				found = true;
				break;
			}
		}

		if (!found) {
			System.out.println("Invalid email or password.");
		}
	}

	private void Register() {
		if(repo.getAdmin()!=null)
		{
			System.out.println("already admin exits");
		}
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

		Admin admin = new Admin(name, email, phone, gender, age, password);
		repo.setAdminList(id++, admin);
		System.out.println("\nRegistration successful!");

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

}
