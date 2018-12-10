package com.vaadin.starter.skeleton.formattingcontents;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.formattingcontents.Order.Address;

@Route("RenderersAndTemplates")
public class RenderersAndTemplates extends VerticalLayout {

	public RenderersAndTemplates() {

		List<Order> orderList = new ArrayList<>();

		String str = "2016-03-04 11:30:40";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		LocalDate localDate = LocalDate.parse(str, formatter);

		orderList.add(new Order("Tshit", 2, 20, dateTime, localDate, "Mickael", new Address("12080", "Washington")));
		orderList.add(new Order("Pant", 2, 70, dateTime, localDate, "Peter", new Address("93849", "New York")));
		orderList.add(new Order("Bag", 1, 60, dateTime, localDate, "Samuel", new Address("86829", "New York")));

		Grid<Order> grid = new Grid<>();
		grid.setItems(orderList);

		grid.addColumn(Order::getName).setHeader("Buyer");
		
		NumberFormat moneyFormat = NumberFormat.getCurrencyInstance(Locale.US);

		// You can also set complex objects directly. Internal properties of the
		// bean are accessible in the template.
		grid.addColumn(TemplateRenderer.<Order>of(
				"<div>[[item.name]],[[item.price]] <br> purchased on: <small>[[item.purchasedate]]</small></div>")
				.withProperty("name", Order::getName)
				// NumberRenderer to render numbers in general
				.withProperty("price", order -> moneyFormat.format(order.getPrice()))
				.withProperty("purchasedate", order -> order.getPurchaseDate().toString())).setHeader("Purchase");

		grid.addColumn(TemplateRenderer.<Order>of(
				"<div>Estimated delivery date: <small>[[item.estimatedDeliveryDate]]<small> <br>to: <small>[[item.address.street]],[[item.address.postalCode]]</small> </div>")
				.withProperty("estimatedDeliveryDate", order -> order.getPurchaseDate().toString())
				.withProperty("address", order -> order.getAddress())).setHeader("Delivery");
		
		add(grid);
	}

}
