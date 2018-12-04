package com.vaadin.starter.skeleton.basicusage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.MaritalStatus;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.Person.Address;

@Route("BasicGrid")
public class BasicGrid extends VerticalLayout {

	public BasicGrid() {

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
		
		//The Grid<>(Person.class) sorts the properties and in order to reorder the properties we use the 'setColumns' method.
		grid.setColumns("id","firstName","lastName","age","address","phoneNumber","maritalStatus","birthdate");
		
		grid.removeColumnByKey("id");		
		
		add(grid);

	}

}
