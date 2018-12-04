package com.vaadin.starter.skeleton.sorting;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.MaritalStatus;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.Person.Address;

@Route("GridWithSortableColumns")
public class GridWithSortableColumns extends VerticalLayout{
	
	
	public GridWithSortableColumns() {
		List<Person> personList = new ArrayList<>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		personList
				.add(new Person(100, "Mickael", "Blackwell", 68, new Address("12080", "Washington"),"127-942-237",MaritalStatus.Married, LocalDate.parse("05/10/1950", formatter)));
		personList.add(new Person(101, "Peter", "Buchanan", 38, new Address("93849", "New York"), "201-793-488",MaritalStatus.Single,LocalDate.parse("09/10/1980", formatter)));
		personList.add(new Person(102, "Samuel", "Lee", 53, new Address("86829", "New York"), "043-713-538",MaritalStatus.Married,LocalDate.parse("03/28/1965", formatter)));
		personList.add(new Person(103, "Anton", "Ross", 37, new Address("63521", "New York"), "150-813-6462",MaritalStatus.Single,LocalDate.parse("07/22/1981", formatter)));
		personList.add(new Person(104, "Aaron", "Atkinson", 18, new Address("25415", "Washington"), "321-679-8544",MaritalStatus.Single,LocalDate.parse("05/10/2000", formatter)));
		personList.add(new Person(105, "Jack", "Woodward", 28, new Address("95632", "New York"), "187-338-588",MaritalStatus.Married,LocalDate.parse("02/10/1990", formatter)));
		
		Grid<Person> grid = new Grid<>(Person.class);
		grid.setItems(personList);
		grid.setSelectionMode(SelectionMode.NONE);

		grid.addColumn(Person::getFirstName, "first name").setHeader("First Name");
		grid.addColumn(Person::getAge, "age").setHeader("Age");
		grid.removeColumnByKey("id");

		grid.addColumn(TemplateRenderer.<Person> of(
		        "<div>[[item.street]], number [[item.number]]<br><small>[[item.postalCode]]</small></div>")
		        .withProperty("street",
		                person -> person.getAddress().getStreet())
		        .withProperty("number",
		                person -> person.getAddress().getNumber())
		        .withProperty("postalCode",
		                person -> person.getAddress().getPostalCode()),
		        "street", "number").setHeader("Address");

		Checkbox multiSort = new Checkbox("Multiple column sorting enabled");
		Label messageDiv= new Label();
		multiSort.addValueChangeListener(
		        event -> grid.setMultiSort(event.getValue()));

		// you can set the sort order from server-side with the grid.sort method
		NativeButton invertAllSortings = new NativeButton(
		        "Invert all sort directions", event -> {
		            List<GridSortOrder<Person>> orderList = grid.getSortOrder();
		            List<GridSortOrder<Person>> newOrderList = new ArrayList<>(
		                    orderList.size());
		            for (GridSortOrder<Person> sort : orderList) {
		                newOrderList.add(new GridSortOrder<>(sort.getSorted(),
		                        sort.getDirection().getOpposite()));
		            }
		            grid.sort(newOrderList);
		        });

		NativeButton resetAllSortings = new NativeButton("Reset all sortings",
		        event -> grid.sort(null));
		
		add(grid,multiSort,messageDiv,invertAllSortings,resetAllSortings);
	}

}
