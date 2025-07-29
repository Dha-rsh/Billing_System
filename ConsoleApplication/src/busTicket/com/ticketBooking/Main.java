package busTicket.com.ticketBooking;

import java.util.Scanner;

public class Main {
	private final static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Welcome to TNSTC ");
		int choice = 0;
		while (choice != 3) {
			System.out.println("1.Admin Register & Login");
			System.out.println("2.User Register & Login");
			System.out.println("3.Exit");
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				new busTicket.com.ticketBooking.features.AdminView().mainMenu();
				break;
			case 2:
				
				new busTicket.com.ticketBooking.features.UserView().mainMenu();
				break;
			case 3:
				System.out.println("Exiting....");
				System.exit(0);
			}
		}
	}
}
