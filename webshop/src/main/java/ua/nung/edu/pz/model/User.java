package ua.nung.edu.pz.model;

import java.util.Objects;

public class User {
	private String email;
	private String password;
	private String phone;
	private String city;
	private String street;

	public User() {
	}

	public User(String username, String password, String phone, String city, String street) {
		this.email = username;
		this.password = password;
		this.phone = phone;
		this.city = city;
		this.street = street;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String username) {
		this.email = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "User{" +
				"Username='" + email + '\'' +
				", Password='" + password + '\'' +
				", Phone='" + phone + '\'' +
				", City='" + city + '\'' +
				", Street='" + street + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User user = (User) o;
		return Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword())
				&& Objects.equals(getPhone(), user.getPhone()) && Objects.equals(getCity(), user.getCity())
				&& Objects.equals(getStreet(), user.getStreet());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getEmail(), getPassword(), getPhone(), getCity(), getStreet());
	}
}
