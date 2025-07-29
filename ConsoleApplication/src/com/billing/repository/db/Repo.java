package com.billing.repository.db;

import java.util.HashMap;

import com.billing.repository.dto.Admin;
import com.billing.repository.dto.CartItem;
import com.billing.repository.dto.Customer;
import com.billing.repository.dto.Product;

public class Repo {
	private static Repo repo;
	private static Admin admin;
	private static Customer customer;
	private static Product product;
	private static CartItem cartItem;
	private HashMap<Integer,Product> productList;

	private static HashMap<Integer, Customer> customerList;
	private static HashMap<Integer, HashMap<Integer, CartItem>> cart;


	Repo() {
		    productList = new HashMap<>();
		    customerList = new HashMap<>();
		    cart=new HashMap<>();
		}

	public static Repo getInstance() {
		if (repo == null) {
			repo = new Repo();
		}
		return repo;
	}

	public Admin getAdmin() {
		return admin;
	}

	public static void setAdmin(Admin admin) {
		Repo.admin = admin;
	}

	public  Customer getCustomer() {
		return customer;
	}

	public  void setCustomer(Customer customer) {
		Repo.customer = customer;
	}
	
	
	public static HashMap<Integer, Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(int id, Customer cust) {
		customerList.put(id, cust);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		Repo.product = product;
	}

	public HashMap<Integer, Product> getProductList() {
		return productList;
	}

	public void setProductList(int id,Product product) {
		productList.put(id, product);
	}

	public HashMap<Integer, HashMap<Integer, CartItem>> getCart() {
		return cart;
	}

	public void setCart(int id,Product product,int quantity) {
		HashMap<Integer, CartItem> customerCart=cart.get(id);
		if(customerCart==null)
		{
			customerCart = new HashMap<>();
			cart.put(id, customerCart);	
		}
		int productId=product.getProductId();
		if(customerCart.containsKey(productId))
		{
			CartItem item=customerCart.get(productId);
			item.setQuantity(item.getQuantity()+quantity);
		}else
		{
		customerCart.put(productId,new CartItem(product,quantity));
		}
	}

	public static CartItem getCartItem() {
		return cartItem;
	}

	public static void setCartItem(CartItem cartItem) {
		Repo.cartItem = cartItem;
	}

}
