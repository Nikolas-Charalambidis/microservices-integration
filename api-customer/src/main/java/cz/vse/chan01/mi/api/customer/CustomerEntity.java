package cz.vse.chan01.mi.api.customer;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity {

	@Id
	@GeneratedValue
	@Column(name="customer_id")
	private Long customerId;

	@Column(name="name")
	private String name;

	@Column(name="surname")
	private String surname;

	@Column(name="email")
	private String email;

	@Column(name="date_birth")
	private LocalDate dateBirth;

	public CustomerEntity() {}

	public CustomerEntity(final String name, final String surname, final String email, final LocalDate dateBirth) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.dateBirth = dateBirth;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public LocalDate getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(final LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof CustomerEntity)) {
			return false;
		}
		final CustomerEntity that = (CustomerEntity) o;
		return Objects.equals(getCustomerId(), that.getCustomerId()) &&
			Objects.equals(getName(), that.getName()) &&
			Objects.equals(getSurname(), that.getSurname()) &&
			Objects.equals(getDateBirth(), that.getDateBirth());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCustomerId(), getName(), getSurname(), getDateBirth());
	}
	//getters, setters, toString, hashCode, equals
}
