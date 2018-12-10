package com.vaadin.starter.skeleton.configuringcolumns;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridSelectionModel;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.basicusage.PersonService;

@Route("ColumnAPIExample")
public class ColumnAPIExample extends VerticalLayout {

	public ColumnAPIExample() {
		
		PersonService personService = new PersonService();
		List<Person> personList = personService.fetchAll();
		
		Grid<Person> grid = new Grid<>();
		GridSelectionModel<Person> selectionMode = grid
		        .setSelectionMode(SelectionMode.MULTI);
		grid.setItems(personList);

		Column<Person> idColumn = grid.addColumn(Person::getId).setHeader("ID")
		        .setFlexGrow(0).setWidth("75px");
		
		// Combination of properties
		Column<Person> nameColumn = grid.addColumn(Person -> Person.getFirstName() + " " + Person.getLastName())
		        .setHeader("Full name").setResizable(true);

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
		
		add(grid,idColumnVisibility,userReordering);

	}
}
