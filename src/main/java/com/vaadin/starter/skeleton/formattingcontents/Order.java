package com.vaadin.starter.skeleton.formattingcontents;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {

	private String name;
	private int numberOfOrder;
	private float price;
	private LocalDateTime purchaseDate;
	private LocalDate EstimatedDeliveryDate;
	private String personName;
	private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public LocalDate getEstimatedDeliveryDate() {
		return EstimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
		EstimatedDeliveryDate = estimatedDeliveryDate;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public int getNumberOfOrder() {
		return numberOfOrder;
	}

	public void setNumberOfOrder(int numberOfOrder) {
		this.numberOfOrder = numberOfOrder;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Order(String name, int numberOfOrder, float price, LocalDateTime purchaseDate,
			LocalDate estimatedDeliveryDate, String personName, Address address) {
		super();
		this.name = name;
		this.numberOfOrder = numberOfOrder;
		this.price = price;
		this.purchaseDate = purchaseDate;
		EstimatedDeliveryDate = estimatedDeliveryDate;
		this.personName = personName;
		this.address = address;
	}

	public static class Address {
		private String city;
		private String postalCode;

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getPostalCode() {
			return postalCode;
		}

		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}

		@Override
		public String toString() {
			return String.format("%s %s", city, postalCode);
		}

		public Address(String postalCode,String city) {
			super();
			this.postalCode = postalCode;
			this.city = city;
		}

	}

}
