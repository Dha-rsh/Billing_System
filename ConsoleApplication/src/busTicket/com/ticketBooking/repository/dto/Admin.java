package busTicket.com.ticketBooking.repository.dto;

public class Admin {
	private String name;
	private String email;
	private long phone;
	private String gender;
	private int age;
	private String password;

	public Admin() {
	}

	public Admin(String name, String email, long phone, String gender, int age, String password) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.age = age;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin{name='" + name + "', email='" + email + "', phone=" + phone +
		       ", gender='" + gender + "', age=" + age + "}";
	}
}
