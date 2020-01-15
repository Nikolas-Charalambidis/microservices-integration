package cz.vse.chan01.mi.model.customer;

import java.io.Serializable;
import java.time.LocalDate;

public class Customer implements Serializable {

	private long customerId;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String sex;
	private String email;

	public Customer() {
	}

	public Customer(final long customerId, final String firstName, final String lastName, final LocalDate birthDate,
		final String sex,
		final String email) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.sex = sex;
		this.email = email;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(final LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(final String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer{" +
			"customerId=" + customerId +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", birthDate=" + birthDate +
			", sex='" + sex + '\'' +
			", email='" + email + '\'' +
			'}';
	}
}
