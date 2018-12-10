package com.vaadin.starter.skeleton.filtering;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.basicusage.PersonService;

@Route("UsingTextFieldsForFilteringItems")
public class UsingTextFieldsForFilteringItems extends VerticalLayout {

	public UsingTextFieldsForFilteringItems() {

		PersonService personService = new PersonService();
		List<Person> personList = personService.fetchAll();

		Grid<Person> grid = new Grid<>();
		ListDataProvider<Person> dataProvider = new ListDataProvider<>(personList);
		grid.setDataProvider(dataProvider);

		Column<Person> firstNameColumn = grid.addColumn(Person::getFirstName).setHeader("Name");
		Column<Person> ageColumn = grid.addColumn(Person::getAge).setHeader("Age");
		Column<Person> cityColumn = grid.addColumn(person -> person.getAddress().getCity()).setHeader("City");
		Column<Person> postalCodeColumn = grid.addColumn(person -> person.getAddress().getPostalCode())
				.setHeader("Postal Code");

		HeaderRow filterRow = grid.appendHeaderRow();
		// First filter
		TextField firstNameField = new TextField();
		firstNameField.addValueChangeListener(event -> dataProvider
				.addFilter(person -> StringUtils.containsIgnoreCase(person.getFirstName(), firstNameField.getValue())));

		firstNameField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(firstNameColumn).setComponent(firstNameField);
		firstNameField.setSizeFull();
		firstNameField.setPlaceholder("Filter");

		// Second filter
		TextField ageField = new TextField();
		ageField.addValueChangeListener(event -> dataProvider.addFilter(
				person -> StringUtils.containsIgnoreCase(String.valueOf(person.getAge()), ageField.getValue())));

		ageField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(ageColumn).setComponent(ageField);
		ageField.setSizeFull();
		ageField.setPlaceholder("Filter");

		// Third filter
		TextField cityField = new TextField();
		cityField.addValueChangeListener(event -> dataProvider.addFilter(
				person -> StringUtils.containsIgnoreCase(person.getAddress().getCity(), cityField.getValue())));

		cityField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(cityColumn).setComponent(cityField);
		cityField.setSizeFull();
		ageField.setPlaceholder("Filter");

		// Forth filter
		TextField postalCodeField = new TextField();
		postalCodeField.addValueChangeListener(event -> dataProvider.addFilter(person -> StringUtils
				.containsIgnoreCase(person.getAddress().getPostalCode(), postalCodeField.getValue())));

		postalCodeField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(postalCodeColumn).setComponent(postalCodeField);
		postalCodeField.setSizeFull();
		ageField.setPlaceholder("Filter");

		add(grid);
	}

}
