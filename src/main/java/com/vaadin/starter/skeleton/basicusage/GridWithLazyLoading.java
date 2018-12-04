package com.vaadin.starter.skeleton.basicusage;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Person;

@Route("GridWithLazyLoading")
public class GridWithLazyLoading extends VerticalLayout {

	public GridWithLazyLoading() {

		Grid<Person> grid = new Grid<>();
		PersonService personService = new PersonService();
		
		/*
		 * This Data Provider doesn't load all items into the memory right away.
		 * Grid will request only the data that should be shown in its current
		 * view "window". The Data Provider will use callbacks to load only a
		 * portion of the data.
		 */
		grid.setDataProvider(
				DataProvider.fromCallbacks(query -> personService.fetch(query.getOffset(), query.getLimit()).stream(),
						query -> personService.count()));

		grid.addColumn(Person::getFirstName).setHeader("First Name");
		grid.addColumn(Person::getLastName).setHeader("Lasst Name");
		grid.addColumn(Person::getAge).setHeader("Age");
		
		add(grid);
	}

}
