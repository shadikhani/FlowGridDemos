package com.vaadin.starter.skeleton.selection;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.MaritalStatus;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.Person.Address;

@Route("GridMultiSelection")
public class GridMultiSelection extends VerticalLayout{

	public GridMultiSelection() {
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

		grid.setSelectionMode(SelectionMode.MULTI);

		Label messageDiv= new Label();
		grid.asMultiSelect()
		        .addSelectionListener(event -> messageDiv.setText(String.format(
		                "Selection changed from %s to %s, selection is from client: %s",
		                event.getOldValue(), event.getValue(),
		                event.isFromClient())));

		// You can pre-select items
		grid.asMultiSelect().select(personList.get(0), personList.get(1));

		NativeButton selectBtn = new NativeButton("Select first five persons");
		selectBtn.addClickListener(event -> grid.asMultiSelect()
		        .select(personList.subList(0, 5).toArray(new Person[5])));
		NativeButton deselectBtn = new NativeButton("Deselect all");
		deselectBtn
		        .addClickListener(event -> grid.asMultiSelect().deselectAll());
		NativeButton selectAllBtn = new NativeButton("Select all");
		selectAllBtn.addClickListener(
		        event -> ((GridMultiSelectionModel<Person>) grid
		                .getSelectionModel()).selectAll());
		
		
		
		add(grid,messageDiv);
		
	}

}