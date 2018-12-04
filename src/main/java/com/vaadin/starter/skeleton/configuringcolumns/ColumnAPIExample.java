package com.vaadin.starter.skeleton.configuringcolumns;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.grid.GridSelectionModel;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.Person.Address;

@Route("ColumnAPIExample")
public class ColumnAPIExample extends VerticalLayout {

	public ColumnAPIExample() {
		
		List<Person> personList = new ArrayList<>();
		
		personList.add(new Person(100, "Elizabeth", "Blackwell", 11, new Address("12080", "Washington"), "127-942-237"));
		personList.add(new Person(101, "Mia", "Buchanan", 3, new Address("93849", "New York"), "201-793-488"));
		personList.add(new Person(102, "Samuel", "Lee", 2, new Address("86829", "New York"), "043-713-538"));
		personList.add(new Person(103, "Lydia", "Ross", 73, new Address("63521", "New York"), "150-813-6462"));
		personList.add(new Person(104, "Aaron", "Atkinson", 17, new Address("25415", "Washington"), "321-679-8544"));
		personList.add(new Person(105, "London", "Woodward", 16, new Address("95632", "New York"), "187-338-588"));
		
		Grid<Person> grid = new Grid<>();
		GridSelectionModel<Person> selectionMode = grid
		        .setSelectionMode(SelectionMode.MULTI);
		grid.setItems(personList);

		Column<Person> idColumn = grid.addColumn(Person::getId).setHeader("ID")
		        .setFlexGrow(0).setWidth("75px");
		Column<Person> nameColumn = grid.addColumn(Person -> Person.getFirstName() + " " + Person.getLastName())
		        .setHeader("First Name/Last Name").setResizable(true);

		// Setting a column-key allows fetching the column later
		grid.addColumn(Person::getAge).setHeader("Age").setKey("age");
		grid.getColumnByKey("age").setResizable(true);

		NativeButton idColumnVisibility = new NativeButton(
		        "Toggle visibility of the ID column");
		idColumnVisibility.addClickListener(
		        event -> idColumn.setVisible(!idColumn.isVisible()));

		NativeButton userReordering = new NativeButton(
		        "Toggle user reordering of columns");
		userReordering.addClickListener(event -> grid
		        .setColumnReorderingAllowed(!grid.isColumnReorderingAllowed()));

		NativeButton freezeIdColumn = new NativeButton(
		        "Toggle frozen state of ID column");
		freezeIdColumn.addClickListener(
		        event -> idColumn.setFrozen(!idColumn.isFrozen()));

		NativeButton freezeSelectionColumn = new NativeButton(
		        "Toggle frozen state of selection column");
		GridMultiSelectionModel<?> multiSlection = (GridMultiSelectionModel<?>) selectionMode;
		freezeSelectionColumn.addClickListener(
		        event -> multiSlection.setSelectionColumnFrozen(
		                !multiSlection.isSelectionColumnFrozen()));
		
		add(grid,idColumnVisibility,userReordering,freezeIdColumn,freezeSelectionColumn);

	}
}
