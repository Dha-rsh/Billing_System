package busTicket.com.ticketBooking.repository.dto;

public class Ticket {
	
	private int ticketId;
	private int busId;
	private String userEmail;
	private int seatsBooked;
	
	public Ticket(int ticketId, int busId, String userEmail, int seatsBooked) {
		super();
		this.ticketId = ticketId;
		this.busId = busId;
		this.userEmail = userEmail;
		this.seatsBooked = seatsBooked;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getSeatsBooked() {
		return seatsBooked;
	}
	public void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}
	
	
	
	
	
	
	
	
}
