package com.vaadin.starter.skeleton.assigningdata;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.FooterRow;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.Person.Address;
import com.vaadin.starter.skeleton.basicusage.PersonService;

@Route("DynamicHeight")
public class DynamicHeight extends VerticalLayout{
	
	public DynamicHeight() {

		PersonService personService = new PersonService();
		List<Person> personList = personService.fetchAll();

		// Providing a bean-type generates columns for all of it's properties
		Grid<Person> grid = new Grid<>();
		
		// When using heightByRows, all items are fetched and
		// Grid uses all the space needed to render everything.
		grid.setHeightByRows(true);
		
		grid.setItems(personList);
		
		Column<Person> firstNameColumn = grid.addColumn(Person::getFirstName).setHeader("First Name");
		Column<Person> lastNameColumn = grid.addColumn(Person::getLastName).setHeader("Last Name");
		Column<Person> ageColumn = grid.addColumn(Person::getAge).setHeader("Age");

		ListDataProvider<Person> dataProvider = (ListDataProvider<Person>) grid.getDataProvider();
		
		List<Person> personListForAdding = new ArrayList<>();		
		
		Button addButton = new Button("Add Item", event -> {
			
			dataProvider.getItems().add(new Person(106, "X", "Y", 16, new Address("95632", "New York"), "187-338-588"));
			// The dataProvider knows which List it is based on, so when you edit the list
			// you edit the dataprovider.
			grid.getDataProvider().refreshAll();

		});

		Button removeButton = new Button("Remove last", event -> {

			personList.remove(personList.size() - 1);
			// The dataProvider knows which List it is based on, so when you edit the list
			// you edit the dataprovider.
			grid.getDataProvider().refreshAll();
		});

		FooterRow footerRow = grid.appendFooterRow();
		footerRow.getCell(firstNameColumn).setComponent(addButton);
		footerRow.getCell(lastNameColumn).setComponent(removeButton);

		add(grid, addButton, removeButton);
		
		
	}
}
