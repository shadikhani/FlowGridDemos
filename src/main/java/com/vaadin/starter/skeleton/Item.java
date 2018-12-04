package com.vaadin.starter.skeleton;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Item {

	private String name;
	private float price;
	private LocalDateTime purchaseDate;
	private LocalDate EstimatedDeliveryDate;

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

	public Item(String name, float price, LocalDateTime purchaseDate, LocalDate estimatedDeliveryDate) {
		super();
		this.name = name;
		this.price = price;
		this.purchaseDate = purchaseDate;
		EstimatedDeliveryDate = estimatedDeliveryDate;
	}

	@Override
	public String toString() {
		return getName();
	}

}
