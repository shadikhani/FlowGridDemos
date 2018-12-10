package com.vaadin.starter.skeleton.configuringcolumns;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.basicusage.PersonService;

@Route("ConfiguringColumns")
public class ConfiguringColumns extends VerticalLayout {

	public ConfiguringColumns() {
		
		PersonService personService = new PersonService();
		List<Person> personList = personService.fetchAll();

		// Providing a bean-type generates columns for all of it's properties
		Grid<Person> grid = new Grid<>(Person.class);
		
		// Property-names are automatically set as keys
		// You can remove undesired columns by using the key
		grid.removeColumnByKey("id");
		
		grid.setColumns("firstName", "lastName", "age", "address", "phoneNumber");

		grid.setItems(personList);

		// Columns for sub-properties can be added easily
		grid.addColumn("address.postalCode");

		add(grid);

	}

}
