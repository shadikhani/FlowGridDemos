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
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Item;

@Route("FormattingContents")
public class FormattingContents extends VerticalLayout {

	public FormattingContents() {

		List<Item> itemList = new ArrayList<>();
		String str = "2016-03-04 11:30:40";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

		LocalDate localDate = LocalDate.parse(str, formatter);

		itemList.add(new Item("Car", 250, dateTime, localDate));
		itemList.add(new Item("Flower", 10, dateTime, localDate));
		itemList.add(new Item("Book", 210, dateTime, localDate));
		itemList.add(new Item("Games", 250, dateTime, localDate));

		Grid<Item> grid = new Grid<>();
		grid.setItems(itemList);

		grid.addColumn(Item::getName).setHeader("Name");

		// NumberRenderer to render numbers in general
		grid.addColumn(new NumberRenderer<>(Item::getPrice, "$ %(,.2f",
		        Locale.US, "$ 0.00")).setHeader("Price");

		// LocalDateTimeRenderer for date and time
		grid.addColumn(new LocalDateTimeRenderer<>(Item::getPurchaseDate,
		        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT,
		                FormatStyle.MEDIUM)))
		        .setHeader("Purchase date and time").setFlexGrow(2);

		// LocalDateRenderer for dates
		grid.addColumn(new LocalDateRenderer<>(Item::getEstimatedDeliveryDate,
		        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)))
		        .setHeader("Estimated delivery date");

		// NativeButtonRenderer for an easy clickable button,
		// without creating a component
		grid.addColumn(new NativeButtonRenderer<>("Remove", item -> {
		    ListDataProvider<Item> dataProvider = (ListDataProvider<Item>) grid
		            .getDataProvider();
		    dataProvider.getItems().remove(item);
		    dataProvider.refreshAll();
		})).setWidth("100px").setFlexGrow(0);

		add(grid);

	}

}
