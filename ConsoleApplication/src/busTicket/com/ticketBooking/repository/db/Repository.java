package busTicket.com.ticketBooking.repository.db;

import java.util.HashMap;

import busTicket.com.ticketBooking.repository.dto.Admin;
import busTicket.com.ticketBooking.repository.dto.Bus;
import busTicket.com.ticketBooking.repository.dto.Ticket;
import busTicket.com.ticketBooking.repository.dto.User;

public class Repository {
	private static Repository repo;
	private Admin admin;
	private User user;
	private HashMap<Integer,User> userList=new HashMap<>();
	private HashMap<Integer,Admin> adminList=new HashMap<>();
	private HashMap<Integer,Bus> busList=new HashMap<>();
	private HashMap<Integer,Ticket> ticketList=new HashMap<>();
	private HashMap<Integer,String> history=new HashMap<>();
	private static int ticketCount=1;
	
	public static Repository getInstance() {
		if (repo == null) {
			repo = new Repository();
		}
		return repo;

	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public HashMap<Integer, User> getUserList() {
		return userList;
	}

	public void setUserList(int id,User user) {
		userList.put(id, user);
	}

	public HashMap<Integer, Admin> getAdminList() {
		return adminList;
	}

	public void setAdminList(int id,Admin admin) {
		adminList.put(id, admin);
	}
	public HashMap<Integer, Bus> getBusList() {
		return busList;
	}

	public void setBus(int id, Bus bus) {
		busList.put(id, bus);
	}
	public HashMap<Integer,Ticket> getTicket()
	{
		return ticketList;
	}
	public void setTicket(int id,Ticket ticket)
	{
		ticketList.put(id,ticket);
	}
	
	public void printAllUsers() {
		for (User u : userList.values()) {
			System.out.println(u);
		}
	}

	public void printAllAdmins() {
		for (Admin a : adminList.values()) {
			System.out.println(a);
		}
	}



}
