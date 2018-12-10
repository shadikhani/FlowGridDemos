package com.vaadin.starter.skeleton.configuringcolumns;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.basicusage.PersonService;

@Route("FrozenColumns")
public class FrozenColumns extends VerticalLayout {

	public FrozenColumns() {
		
		PersonService personService = new PersonService();
		List<Person> personList = personService.fetchAll();

		Grid<Person> grid = new Grid<>();

		grid.setItems(personList);
		
		grid.addColumn(Person::getId).setHeader("ID")
		        .setFlexGrow(0).setWidth("75px");
		grid.addColumn(Person::getFirstName).setHeader("First Name");
		grid.addColumn(Person::getLastName).setHeader("Last Name");
		grid.addColumn(Person::getPhoneNumber).setHeader("Phone Number").setWidth("800px");
		grid.addColumn(Person::getAddress).setHeader("Adress").setWidth("800px");
		grid.addColumn(Person::getMaritalStatus).setHeader("Marital Status").setWidth("800px");
		grid.addColumn(Person::getBirthdate).setHeader("Birth Date").setWidth("800px");

		grid.setColumnReorderingAllowed(true);
		GridMultiSelectionModel<?> multiSlection = (GridMultiSelectionModel<?>) grid
				.setSelectionMode(SelectionMode.MULTI);
		
		//Freezing the selection column only
		multiSlection.setSelectionColumnFrozen(true);
		
		add(grid);
		
		Grid<Person> secondGrid = new Grid<>();
		secondGrid.setItems(personList);
		
		//Freezing any column
		secondGrid.addColumn(Person::getId).setHeader("ID")
		        .setFlexGrow(0).setWidth("75px").setFrozen(true);
		secondGrid.addColumn(Person::getFirstName).setHeader("First Name").setFrozen(true);
		secondGrid.addColumn(Person::getLastName).setHeader("Last Name").setFrozen(true);
		secondGrid.addColumn(Person::getPhoneNumber).setHeader("Phone Number").setWidth("800px");
		secondGrid.addColumn(Person::getAddress).setHeader("Adress").setWidth("800px");
		secondGrid.addColumn(Person::getMaritalStatus).setHeader("Marital Status").setWidth("800px");
		secondGrid.addColumn(Person::getBirthdate).setHeader("Birth Date").setWidth("800px");
		
		add(secondGrid);	

	}

}
