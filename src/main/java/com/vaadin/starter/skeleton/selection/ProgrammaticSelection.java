package com.vaadin.starter.skeleton.selection;

import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.basicusage.PersonService;

@Route("ProgrammaticSelection")
public class ProgrammaticSelection extends VerticalLayout {

	public ProgrammaticSelection() {

		PersonService personService = new PersonService();
		List<Person> personList = personService.fetchAll();

		Grid<Person> grid = new Grid<>();
		grid.setItems(personList);
		SingleSelect<Grid<Person>, Person> singleSelect = grid.asSingleSelect();

		TextField filterField = new TextField();
		filterField.setValueChangeMode(ValueChangeMode.EAGER);
		filterField.addValueChangeListener(event -> {
			Optional<Person> foundPerson = personList.stream()
					.filter(person -> person.getFirstName().toLowerCase().startsWith(event.getValue().toLowerCase()))
					.findFirst();

			singleSelect.setValue(foundPerson.orElse(null));
		});

		grid.addColumn(Person::getFirstName).setHeader("Name");
		grid.addColumn(Person::getAge).setHeader("Age");

		Grid<Person> secondGrid = new Grid<>();
		secondGrid.setItems(personList);
		secondGrid.setSelectionMode(SelectionMode.MULTI);

		secondGrid.addColumn(Person::getFirstName).setHeader("Name");
		secondGrid.addColumn(Person::getAge).setHeader("Age");

		NativeButton deselectBtn = new NativeButton("Deselect all");
		deselectBtn.addClickListener(event -> secondGrid.asMultiSelect().deselectAll());
		NativeButton selectAllBtn = new NativeButton("Select all");
		selectAllBtn.addClickListener(
				event -> ((GridMultiSelectionModel<Person>) secondGrid.getSelectionModel()).selectAll());

		add(filterField, grid, secondGrid, selectAllBtn, deselectBtn);
	}

}