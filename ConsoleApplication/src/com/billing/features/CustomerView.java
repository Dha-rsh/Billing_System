package com.billing.features;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.billing.repository.db.Repo;
import com.billing.repository.dto.CartItem;
import com.billing.repository.dto.Customer;
import com.billing.repository.dto.Product;
import com.billing.util.FileUtil;
import com.billing.util.TimeUtil;

public class CustomerView {
	private Scanner sc = new Scanner(System.in);
	private Repo repo = Repo.getInstance();
	private int id = 1;

	public void mainMenu() {
		int choice = 0;
		while (choice != 6) {
			System.out.println("1. Customer Details");
			System.out.println("2. add items to cart");
			System.out.println("3. view carts");
			System.out.println("4. Available Products");
			System.out.println("5. Generate Bill");
			System.out.println("6. exit");
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				customerDetails();
				break;
			case 2:
				addItems();
				break;
			case 3:
				viewCarts();
				break;
			case 4:
				availableProducts();
				break;
			case 5:

				showBills();
				break;
			case 6:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid choice");
			}
		}
	}

	private void showBills() {
		System.out.println("Enter Customer Id: ");
		int customerId = sc.nextInt();
		sc.nextLine();
		HashMap<Integer, HashMap<Integer, CartItem>> allCarts = repo.getCart();
		HashMap<Integer, CartItem> customer = allCarts.get(customerId);
		if (customer == null || customer.isEmpty()) {
			System.out.println("No carts here");
			return;
		}
		System.out.println("Date and Time: "+TimeUtil.getTimeStamp());
		System.out.println("----Final Bill----");
		System.out.printf("%-10s %-10s %-10s %-10s\n", "ProdID", "Name", "Qty", "Subtotal");
		double total = 0;
		for (CartItem item : customer.values()) {

			Product product = item.getProduct();
			int qty = item.getQuantity();
			double subtotal = qty*product.getPricePerUnit();
			total += subtotal;
			System.out.printf("%-10d %-10s %-10d %-10.2f\n", product.getProductId(), product.getName(), qty, total);

		}
		System.out.println("----------------------------");
		System.out.printf("Total Amount: %.2f\n", total);
		System.out.println("Thank Your for Shopping");
		FileUtil.saveBillToFile(customerId,customer,total);
	}

	private void availableProducts() {
		System.out.printf("%-10s %-10s %-10s %-10s\n","ProductId","Name","price","Stock");
		System.out.println("-------------------------------------");
		for (Map.Entry<Integer, Product> entry : repo.getProductList().entrySet()) {
			Product product = entry.getValue();
			System.out.printf("%-10d %-10s %-10.2f %-10d\n",
					product.getProductId(),
					product.getName(),
					product.getPricePerUnit(),
					product.getStock());
		}

	}

	private void viewCarts() {

		System.out.println("Enter CustomerId: ");
		int customerId = sc.nextInt();
		sc.nextLine();
		HashMap<Integer, HashMap<Integer, CartItem>> allCarts = repo.getCart();
		HashMap<Integer, CartItem> customer = allCarts.get(customerId);
		if (customer == null || customer.isEmpty()) {
			System.out.println("No carts here");
			return;
		}
		System.out.println("----Your Cart----");
		System.out.printf("%-10s %-10s %-10s %-10s\n", "ProdID", "Name", "Qty", "Subtotal");

		for (CartItem item : customer.values()) {

			Product product = item.getProduct();
			int qty = item.getQuantity();
			double subtotal = qty * product.getPricePerUnit();
			System.out.printf("%-10d %-10s %-10d %-10.2f\n", product.getProductId(), product.getName(), qty, subtotal);
		}

	}

	private void addItems() {
		System.out.println("Enter Customer Id: ");
		int customerId = sc.nextInt();
		System.out.println("Enter Product Id: ");
		int productId = sc.nextInt();
		System.out.println("Enter quantity: ");
		int quantity = sc.nextInt();

		Product product = repo.getProductList().get(productId);
		if (product == null) {
			System.out.println("Invalid product Id");
			return;
		}
		
		if(product.getStock()<quantity)
		{
			System.out.println("No Stocks there");
			return;
		}
		product.setStock(product.getStock()-quantity);
		repo.setCart(customerId, product, quantity);
		System.out.println("Item added Cart successfully");

	}

	private void customerDetails() {

		String name;
		do {
			System.out.println("Enter the name: ");
			name = sc.nextLine();
		} while (!isValidName(name));

		String phone;
		do {
			System.out.println("Enter the Phone Number: ");
			phone = sc.nextLine();
		} while (!isValidPhone(phone));
		repo.setCustomerList(id++, new Customer(name, phone));
		System.out.println("Customer details added");

	}

	private boolean isValidPhone(String phone) {
		return phone.length() == 10;
	}

	private boolean isValidName(String name) {
		for (char ch : name.toCharArray()) {
			if (!Character.isLetter(ch)) {
				return false;
			}
		}
		return name != null && !name.isEmpty();
	}
}
