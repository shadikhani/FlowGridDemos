package com.vaadin.starter.skeleton.usingcomponents;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.basicusage.PersonService;

@Route("UsingComponents2")
public class UsingComponents extends VerticalLayout{
	
	public UsingComponents() {
		
		PersonService personService = new PersonService();
		List<Person> personList = personService.fetchAll();

		
		Grid<Person> grid = new Grid<>();
		grid.setItems(personList);

		// Use the component constructor that accepts an item ->
		// new PersonComponent(Person person)
		grid.addComponentColumn(PersonComponent::new).setHeader("Person");

		// Or you can use an ordinary function to setup the component
		grid.addComponentColumn(item -> new Button("Remove", clickEvent -> {
		    ListDataProvider<Person> dataProvider = (ListDataProvider<Person>) grid
		            .getDataProvider();
		    dataProvider.getItems().remove(item);
		    dataProvider.refreshAll();
		})).setHeader("Actions");

		grid.setSelectionMode(SelectionMode.NONE);
		add(grid);
		
	}

}
