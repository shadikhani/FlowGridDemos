package com.vaadin.starter.skeleton.selection;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.basicusage.PersonService;

@Route("GridMultiSelection")
public class GridMultiSelection extends VerticalLayout{

	public GridMultiSelection() {
		
		PersonService personService = new PersonService();
		List<Person> personList = personService.fetchAll();
		
		
		Grid<Person> grid = new Grid<>();
		grid.setItems(personList);

		grid.addColumn(Person::getFirstName).setHeader("First Name");
		grid.addColumn(Person::getAge).setHeader("Age");

		grid.setSelectionMode(SelectionMode.MULTI);

		Label messageDiv= new Label();
		
		grid.asMultiSelect().addValueChangeListener(event -> {			
			String message = String.format("Selection changed from %s to %s", event.getOldValue(), event.getValue());
			messageDiv.setText(message);
		});

		// You can pre-select items
		grid.asMultiSelect().select(personList.get(0), personList.get(1));
		
		add(grid,messageDiv);
		
	}

}
