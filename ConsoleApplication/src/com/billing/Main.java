package com.billing;

import java.util.Scanner;

import com.billing.features.AdminView;
import com.billing.features.CustomerView;

public class Main {
	public static void main(String args[]) {
	int choice=0;
	 System.out.println("Welcome to MyBilling System");
	 
	while(choice!=3)
	{
		System.out.println("1. Admin Register & Login");
		System.out.println("2. Customer Login");
		System.out.println("3. Exit");
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your Choice: ");
	
		choice=sc.nextInt();
		sc.nextLine();
		switch(choice)
		{
		case 1:
			new AdminView().mainMenu();
			break;
		case 2:
			new CustomerView().mainMenu();
			break;
		case 3:
			System.out.println("Exiting....");
			System.exit(0);
		default:
			System.out.println("Invalid Choice");
			
		}
		
		
	}
		}
}
