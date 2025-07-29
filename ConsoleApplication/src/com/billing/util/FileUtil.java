package com.billing.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.billing.repository.dto.CartItem;
import com.billing.repository.dto.Product;

public class FileUtil {
	public static void saveBillToFile(int customerId, HashMap<Integer, CartItem> customerCart, double total) {
		String fileName = "bill" + customerId + ".txt";
		String dateAndTime=TimeUtil.getTimeStamp();
		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName);
			writer.println("Date and time: "+dateAndTime);
			writer.println("----Final Bill----");
			writer.printf("%-10s %-15s %-10s %-10s\n", "ProdId", "Name", "Qty", "Subtotal");
			for (CartItem item : customerCart.values()) {
				Product product = item.getProduct();
				int qty = item.getQuantity();
				double subtotal=qty*product.getPricePerUnit();
				writer.printf("%-10d %-15s %-10d %-10.2f\n", product.getProductId(), product.getName(), qty, subtotal);
				
			}
			writer.println("----------------------------");
			writer.printf("Total Amount: %.2f\n", total);
			writer.println("Thank You for Shopping!");
			writer.close();
			System.out.println("Bill saved as: " + fileName);
		} catch (FileNotFoundException e) {

			System.out.println("failed"+e.getMessage());
		}

	}
}
