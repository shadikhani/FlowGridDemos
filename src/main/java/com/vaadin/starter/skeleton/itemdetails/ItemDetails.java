package com.vaadin.starter.skeleton.itemdetails;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.MaritalStatus;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.Person.Address;

@Route("ItemDetails")
public class ItemDetails extends VerticalLayout {

	public ItemDetails() {
		List<Person> personList = new ArrayList<>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		personList
				.add(new Person(100, "Elizabeth", "Blackwell", 11, new Address("12080", "Washington"),"127-942-237",MaritalStatus.Married, LocalDate.parse("05/10/1910", formatter)));
		personList.add(new Person(101, "Mia", "Buchanan", 3, new Address("93849", "New York"), "201-793-488",MaritalStatus.Single,LocalDate.parse("09/10/1980", formatter)));
		personList.add(new Person(102, "Samuel", "Lee", 2, new Address("86829", "New York"), "043-713-538",MaritalStatus.Married,LocalDate.parse("03/28/1910", formatter)));
		personList.add(new Person(103, "Lydia", "Ross", 73, new Address("63521", "New York"), "150-813-6462",MaritalStatus.Single,LocalDate.parse("07/22/1981", formatter)));
		personList.add(new Person(104, "Aaron", "Atkinson", 17, new Address("25415", "Washington"), "321-679-8544",MaritalStatus.Married,LocalDate.parse("05/10/1910", formatter)));
		personList.add(new Person(105, "London", "Woodward", 16, new Address("95632", "New York"), "187-338-588",MaritalStatus.Married,LocalDate.parse("02/10/1910", formatter)));
		
		Grid<Person> grid = new Grid<>();
		
		grid.setItems(personList);

		grid.addColumn(Person::getFirstName).setHeader("First Name");
		grid.addColumn(Person::getAge).setHeader("Age");

		grid.setSelectionMode(SelectionMode.NONE);

		// You can use any renderer for the item details. By default, the
		// details are opened and closed by clicking the rows.
		grid.setItemDetailsRenderer(TemplateRenderer.<Person> of(
		        "<div style='border: 1px solid gray; padding: 10px; width: 100%; box-sizing: border-box;'>"
		                + "<div>Hi! My name is <b>[[item.firstname]]!</b></div>"
		                + "<div><img style='height: 80px; width: 80px;' src='[[item.image]]'/></div>"
		                + "</div>")
		        .withProperty("firstname", Person::getFirstName)
		        .withProperty("lastname", Person::getLastName)
		        .withProperty("address", Person::getAddress)
		        .withProperty("image",  Person::getImage)
		        .withEventHandler("handleClick", person -> {
		            grid.getDataProvider().refreshItem(person);
		        }));

		add(grid);

	}
}
