package com.billing.features;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.billing.repository.db.Repo;
import com.billing.repository.dto.Admin;
import com.billing.repository.dto.Product;

public class AdminView {
	private static Scanner sc = new Scanner(System.in);
	private static Repo repo = Repo.getInstance();
	private boolean isLogin=false;
	public void mainMenu() {
		int choice = 0;
		while (choice != 7) {
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Add Product");
			System.out.println("4. Modify Product");
			System.out.println("5. Delete Product");
			System.out.println("6. View Product");
			System.out.println("7. logout");
			System.out.println("Enter your Choice: ");

			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				register();
				break;
			case 2:
				login();
				break;
			case 3:
				addProduct();
				break;
			case 4:
				modifyProduct();
				break;
			case 5:
				deleteProduct();
				break;

			case 6:
				viewProduct();
				break;
			case 7:
				System.out.println("Logging out...");
				break;
			default:
				System.out.println("Invalid choice");

			}
		}
	}

	private void viewProduct() {
		if(!isLogin) {
			System.out.println("Login First");
			return;
		}
		if (repo.getProductList().isEmpty()) {
			System.out.println("No products available.");
			return;
		}

		System.out.printf("%-10s %-10s %-10s %-10s\n","ProductId","Name","price","Stock");
		System.out.println("------------------------------------------------------");
		for (Map.Entry<Integer, Product> entry : repo.getProductList().entrySet()) {
			Product product = entry.getValue();
			System.out.printf("%-10d %-10s %-10.2f %-10s \n",
					product.getProductId(),
					product.getName(),
					product.getPricePerUnit()
					,product.getStock());
		}

	}

	private void deleteProduct() {
		if(!isLogin) {
			System.out.println("Login First");
			return;
		}
		int productId;
		System.out.println("Enter product id to delete");
		productId = sc.nextInt();
		sc.nextLine();

		Product product = repo.getProductList().get(productId);
		if (product == null) {
			System.out.println("the product nont found");
			return;
		}
		repo.getProductList().remove(productId);

		System.out.println("The product deleted");

	}

	private void modifyProduct() {
		if(!isLogin) {
			System.out.println("Login First");
			return;
		}
		System.out.println("Enter the product id to modify: ");
		int productId = sc.nextInt();
		sc.nextLine();

		Product product = repo.getProductList().get(productId);
		if (product == null) {
			System.out.println("The product not availbale");
			return;
		}

		System.out.println("Enter the name: ");
		String name = sc.nextLine();
		System.out.println("Enter the price: ");
		double price = sc.nextDouble();
		System.out.println("Enter the stock: ");
		int stock=sc.nextInt();
		product.setName(name);
		product.setPricePerUnit(price);
		product.setStock(stock);

		System.out.println("Product updated sucessfully");

	}

	private void addProduct() {

		int productId;
		String name;
		double price;
		int stock;
		if(!isLogin) {
			System.out.println("Login First");
			return;
		}
		System.out.println("Enter the product id: ");
		productId = sc.nextInt();
		sc.nextLine();
		if (repo.getProductList() != null && repo.getProductList().containsKey(productId)) {
			System.out.println("Product ID already exists.");
			return;
		}

		System.out.println("Enter the name: ");
		name = sc.nextLine();
		System.out.println("Enter the price: ");
		price = sc.nextDouble();
		System.out.println("Enter the stock: ");
		stock=sc.nextInt();

		Product product = new Product(productId, name, price,stock);
		repo.setProductList(productId, product);
		System.out.println("Product Added Successfully");

	}

	private void login() {
		Admin admin=repo.getAdmin();
		if (admin == null) {
			System.out.println("Register First");
			return;
		}
		String name;
		String password;

		System.out.println("Enter the name: ");
		name = sc.nextLine();
		if (!admin.getName().equals(name)) {
			System.out.println("Invalid name.");
			return;

		}

		System.out.println("Enter the password: ");
		password = sc.nextLine();

		if (!admin.getPassword().equals(password)) {
			System.out.println("Invalid password.");
			return;
		}
		isLogin=true;

		System.out.println("login Succesfully..");

	}

	public static void register() {
		String name;
		String password;

		do {
			System.out.println("Enter the name: ");
			name = sc.nextLine();

		} while (!isValidName(name));

		do {
			System.out.println("Enter the password");
			password = sc.nextLine();
		} while (!isValidPass(password));

		Repo.setAdmin(new Admin(name, password));
		System.out.println("Registered Succesfully..");

	}

	private static boolean isValidPass(String pass) {
		return pass.length() > 3 && pass.length() < 9;
	}

	public static boolean isValidName(String name) {
		for (char ch : name.toCharArray()) {
			if (!Character.isLetter(ch)) {
				return false;
			}
		}
		return name != null && !name.isEmpty();

	}
}
