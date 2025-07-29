package busTicket.com.ticketBooking.repository.dto;

public class Bus {
	private int busNo;
	private String startingPoint;
	private String EndingPoint;
	private String dateTime;
	private int totalSeats;
	private int availableSeats=totalSeats;

	public Bus(int busNo, String startingPoint, String endingPoint, String dateTime, int totalSeats) {
		super();
		this.busNo = busNo;
		this.startingPoint = startingPoint;
		EndingPoint = endingPoint;
		this.dateTime = dateTime;
		this.totalSeats = totalSeats;
	}

	public int getBusNo() {
		return busNo;
	}

	public void setBusNo(int busNo) {
		this.busNo = busNo;
	}

	public String getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(String startingPoint) {
		this.startingPoint = startingPoint;
	}

	public String getEndingPoint() {
		return EndingPoint;
	}

	public void setEndingPoint(String endingPoint) {
		EndingPoint = endingPoint;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getAvailableSeats() {
	       return availableSeats;
	}

	public void bookSeats(int seats) {
		availableSeats= availableSeats-seats;
		
	}

	public void cancelSeat(int count) {
		availableSeats+=count;
		
	}
}
