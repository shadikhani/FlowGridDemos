package com.vaadin.starter.skeleton;

import java.time.LocalDate;

public class Person {
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private Address address;
	private String phoneNumber;
	private MaritalStatus maritalStatus;
	private LocalDate birthdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getImage() {
		return "https://randomuser.me/api/portraits/men/" + getId() + ".jpg";
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Person)) {
			return false;
		}
		Person other = (Person) obj;
		return id == other.id;
	}

	public Person(int id, String firstName, String lastName, int age, Address address, String phoneNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public Person(int id, String firstName, String lastName, int age, Address address, String phoneNumber,
			MaritalStatus maritalStatus, LocalDate birthDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.maritalStatus = maritalStatus;
		this.birthdate = birthDate;
	}

	@Override
	public String toString() {
		return firstName;
	}

	public static class Address {
		private String street;
		private int number;
		private String postalCode;
		private String city;

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public String getPostalCode() {
			return postalCode;
		}

		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		@Override
		public String toString() {
			return String.format("%s %s", postalCode,city);
		}

		public Address(String street, int number, String postalCode) {
			super();
			this.street = street;
			this.number = number;
			this.postalCode = postalCode;
		}

		public Address(String postalCode, String city) {
			super();
			this.postalCode = postalCode;
			this.city = city;
		}

	}
}