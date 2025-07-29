package expenseTracker;

import java.util.HashMap;
import java.util.Map.Entry;

import expenseTracker.repository.db.Repository;
import expenseTracker.repository.dto.User;

import java.util.Scanner;

public class Main {
	private static Scanner sc = new Scanner(System.in);
    private static Repository repo = Repository.getInstance();
	private static HashMap<String, User> users = new HashMap<>();
    private static int id=1;
	public static void main(String[] args) {
		System.out.println("Welcome to the Expense Tracker");
		int choice = 0;
		while (choice != 3) {
			System.out.println("1.User Register and Login");
			System.out.println("2.Expense Manager");
			System.out.println("3.logout");
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				userRegister();
				break;
			case 2:
				expenseManager();
				break;
			case 3:
				System.out.println("Exiting....");
				System.exit(0);
			default:
				System.out.println("invalid option");
			}
		}

	}

	private static void expenseManager() {
		if(repo.getUser()==null)
		{
			System.out.println("User not exists");
			System.out.println("register here");
			userRegister();
		}
		int choice = 0;
		while (choice != 6) {
			System.out.println("1.Add Expense");
			System.out.println("2.View All Expenses");
			System.out.println("3.Total Expenses");
			System.out.println("4.Set Budget");
			System.out.println("5.Budget Alert");
			System.out.println("6.Exit");
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				addExpense();
				break;
			case 2:
				viewAllExpense();
				break;
			case 3:
				totalExpense();
				break;
			case 4:
				setBudget();
				break;
			case 5:
				budgetAlert();
				break;
			case 6:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Enter the number between 1-7 \nInvalid choice");

			}
		}
	}

	private static void budgetAlert() {

	}

	private static void setBudget() {

	}

	private static void totalExpense() {
		/*
		 *  System.out.println("Enter the userName: "); 
		 *  String name =sc.nextLine();
		 */
		int total = 0;
		for (Entry<String, Integer> mp : repo.getUser().getExpense().entrySet()) {
			total += mp.getValue();
		}
		System.out.println(repo.getUser().getName() + " total Expenses: " + total);

	}

	private static void viewAllExpense() {
		/*
		 * System.out.println("Enter the user Name: "); String name = sc.nextLine(); if
		 * (!users.containsKey(name)) { System.out.println("user does not exits");
		 * return; }
		 */
		User user = repo.getUser();
		if(user.getExpense().isEmpty())
		{
			System.out.println("No Expense Here");
		}
		for(Entry<String, Integer> map:user.getExpense().entrySet())
		{
			System.out.println("Expense name: "+ map.getKey()+" with Amount : "+map.getValue());
		}

	}

	private static void addExpense() {
	
		User user=repo.getUser();


		System.out.print("enter Expense Name:");
		String expensename = sc.nextLine();
		System.out.print("Enter Expense amount: ");
		int amount = sc.nextInt();

		user.addUserExpense(expensename, amount);
		System.out.println("expense added");

	}

	private static void userRegister() {
		String name;
		String password;
		do {
			System.out.println("Enter your Name: ");
			name = sc.nextLine();
			System.out.println("Enter password: ");
			password = sc.nextLine();
			
		} while (!valid(name, password));
		
		repo.setUser(new User(id, name,password));
		System.out.println(name + ", Register Succesfully");
		login(password);

	}

	private static void login(String password) {
		String pass;
		do
		{
			System.out.println("Enter the password for Login: ");
            pass=sc.nextLine();
		}while(!password.equals(pass));
		System.out.println("Login Successfully");
		
	}

	private static boolean valid(String name, String password) {
		for (char ch : name.toCharArray()) {
			if (!Character.isLetter(ch)) {
				return false;
			}

		}

		if (password.length() < 3 || password.length() > 8) {
			return false;
		}

		return true;

	}

}
