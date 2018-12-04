package com.vaadin.starter.skeleton.formattingcontents;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.formattingcontents.Order.Address;

@Route("RenderersAndTemplates")
public class RenderersAndTemplates extends VerticalLayout{
	
	public RenderersAndTemplates() {
		
		List<Order> orderList= new ArrayList<>();
		
		String str = "2016-03-04 11:30:40";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		LocalDate localDate = LocalDate.parse(str, formatter);
		
		orderList.add(new Order("Tshit", 2,20,dateTime,localDate,"Shadi",new Address("Iran", 1, "2145")));
		orderList.add(new Order("Pant", 2,70,dateTime,localDate,"Shervin",new Address("Iran", 2, "2020")));
		orderList.add(new Order("Bag", 1,60,dateTime,localDate,"Shahram",new Address("Iran", 3, "7070")));
		
		Grid<Order> grid = new Grid<>();
		grid.setItems(orderList);

		grid.addColumn(Order::getName).setHeader("Name");

		// NumberRenderer to render numbers in general
		grid.addColumn(new NumberRenderer<>(Order::getPrice, "$ %(,.2f",
		        Locale.US, "$ 0.00")).setHeader("Price");

		// LocalDateTimeRenderer for date and time
		grid.addColumn(new LocalDateTimeRenderer<>(Order::getPurchaseDate,
		        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT,
		                FormatStyle.MEDIUM)))
		        .setHeader("Purchase date and time").setFlexGrow(2);

		// LocalDateRenderer for dates
		grid.addColumn(new LocalDateRenderer<>(Order::getEstimatedDeliveryDate,
		        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)))
		        .setHeader("Estimated delivery date");
		
		// You can also set complex objects directly. Internal properties of the
		// bean are accessible in the template.
		grid.addColumn(TemplateRenderer.<Order> of(
		        "<div>[[item.address.street]], number [[item.address.number]]<br><small>[[item.address.postalCode]]</small></div>")
		        .withProperty("address", Order::getAddress))
		        .setHeader("Address");

		// NativeButtonRenderer for an easy clickable button,
		// without creating a component
		grid.addColumn(new NativeButtonRenderer<>("Remove", item -> {
		    ListDataProvider<Order> dataProvider = (ListDataProvider<Order>) grid
		            .getDataProvider();
		    dataProvider.getItems().remove(item);
		    dataProvider.refreshAll();
		})).setWidth("100px").setFlexGrow(0);

		add(grid);
	}

}
