package com.vaadin.starter.skeleton.filtering;

import java.util.List;

import com.google.common.base.Objects;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.MaritalStatus;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.basicusage.PersonService;

@Route("UsingComboboxAndDateTypesForFiltering")
public class UsingComboboxAndDateTypesForFiltering extends VerticalLayout {
	private ComboBox<MaritalStatus> marritalStatus;
	private DatePicker birthDateField;

	public UsingComboboxAndDateTypesForFiltering() {

		PersonService personService = new PersonService();
		List<Person> personList = personService.fetchAll();


		Grid<Person> grid = new Grid<>();
		ListDataProvider<Person> dataProvider = new ListDataProvider<>(personList);
		grid.setDataProvider(dataProvider);		
		
		Column<Person> firstNameColumn =  grid.addColumn(Person::getFirstName).setHeader("Name");
		Column<Person> ageColumn =grid.addColumn(Person::getAge).setHeader("Age");
		Column<Person> birthDateColumn =grid.addColumn(person -> person.getBirthdate()).setHeader("Birth date");
		Column<Person> postalCodeColumn =grid.addColumn(person -> person.getAddress().getPostalCode()).setHeader("Postal Code");	


		HeaderRow filterRow = grid.appendHeaderRow();

		marritalStatus = new ComboBox<>("Filter by marital status: ");
		marritalStatus.setItems(MaritalStatus.values());
		birthDateField = new DatePicker("Filter by birth date: ");

		marritalStatus.addValueChangeListener(event -> {
			applyFilter(dataProvider);
		});

		birthDateField.addValueChangeListener(event -> {
			applyFilter(dataProvider);
		});

		// filterRow.getCell(nameColumn).setComponent(marritalStatus);
		marritalStatus.setSizeFull();

		add(marritalStatus, birthDateField, grid);

	}

	private void applyFilter(ListDataProvider<Person> dataProvider) {
		dataProvider.clearFilters();
		if (birthDateField.getValue() != null)
			dataProvider.addFilter(person -> Objects.equal(birthDateField.getValue(), person.getBirthdate()));
		if (marritalStatus.getValue() != null)
			dataProvider.addFilter(person -> marritalStatus.getValue() == person.getMaritalStatus());
	}
}
