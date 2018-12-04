package com.vaadin.starter.skeleton.assigningdata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.MaritalStatus;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.Person.Address;

@Route("AssigningArrayData")
public class AssigningArrayData extends VerticalLayout {

	public AssigningArrayData() {

		List<Person> personList = new ArrayList<>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		personList
				.add(new Person(100, "Mickael", "Blackwell", 68, new Address("12080", "Washington"),"127-942-237",MaritalStatus.Married, LocalDate.parse("05/10/1950", formatter)));
		personList.add(new Person(101, "Peter", "Buchanan", 38, new Address("93849", "New York"), "201-793-488",MaritalStatus.Single,LocalDate.parse("09/10/1980", formatter)));
		personList.add(new Person(102, "Samuel", "Lee", 53, new Address("86829", "New York"), "043-713-538",MaritalStatus.Married,LocalDate.parse("03/28/1965", formatter)));
		personList.add(new Person(103, "Anton", "Ross", 37, new Address("63521", "New York"), "150-813-6462",MaritalStatus.Single,LocalDate.parse("07/22/1981", formatter)));
		personList.add(new Person(104, "Aaron", "Atkinson", 18, new Address("25415", "Washington"), "321-679-8544",MaritalStatus.Single,LocalDate.parse("05/10/2000", formatter)));
		personList.add(new Person(105, "Jack", "Woodward", 28, new Address("95632", "New York"), "187-338-588",MaritalStatus.Married,LocalDate.parse("02/10/1990", formatter)));
		
		// Providing a bean-type generates columns for all of it's properties
		Grid<Person> grid = new Grid<>();
		grid.setItems(personList);

		Column<Person> firstNameColumn = grid.addColumn(Person::getFirstName).setHeader("First Name");
		Column<Person> lastNameColumn = grid.addColumn(Person::getLastName).setHeader("Last Name");
		Column<Person> ageColumn = grid.addColumn(Person::getAge).setHeader("Age");

		ListDataProvider<Person> dataProvider = (ListDataProvider<Person>) grid.getDataProvider();
		int id = 400;
		
		List<Person> personListForAdding = new ArrayList<>();		
		
		Button addButton = new Button("Add Item", event -> {
			
			personList.add(new Person(106, "X", "Y", 16, new Address("95632", "New York"), "187-338-588"));
			dataProvider.refreshAll();

		});

		Button removeButton = new Button("Remove", event -> {

			personList.remove(personList.size() - 1);
			dataProvider.getItems();
			dataProvider.refreshAll();
		});

		HeaderRow headerRow = grid.appendHeaderRow();
		headerRow.getCell(firstNameColumn).setComponent(addButton);
		headerRow.getCell(lastNameColumn).setComponent(removeButton);

		add(grid, addButton, removeButton);

	}

}
