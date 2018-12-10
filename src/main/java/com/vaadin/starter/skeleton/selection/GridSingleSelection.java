package com.vaadin.starter.skeleton.selection;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.basicusage.PersonService;

@Route("GridSingleSelection")
public class GridSingleSelection extends VerticalLayout {

	public GridSingleSelection() {
		PersonService personService = new PersonService();
		List<Person> personList = personService.fetchAll();

		Grid<Person> grid = new Grid<>();
		grid.setItems(personList);

		grid.addColumn(Person::getFirstName).setHeader("Name");
		grid.addColumn(Person::getAge).setHeader("Age");
		Label messageDiv = new Label();

		grid.asSingleSelect().addValueChangeListener(event -> {
			String message = String.format("Selection changed from %s to %s", event.getOldValue(), event.getValue());
			messageDiv.setText(message);
		});

		add(grid, messageDiv);

	}

}
