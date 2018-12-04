package com.vaadin.starter.skeleton.filtering;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Objects;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.MaritalStatus;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.Person.Address;

@Route("UsingComboboxAndDateTypesForFiltering")
public class UsingComboboxAndDateTypesForFiltering extends VerticalLayout {
	private ComboBox<MaritalStatus> marritalStatus;
	private DatePicker birthDateField;

	public UsingComboboxAndDateTypesForFiltering() {

		List<Person> personList = new ArrayList<>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		personList
				.add(new Person(100, "Elizabeth", "Blackwell", 11, new Address("12080", "Washington"),"127-942-237",MaritalStatus.Married, LocalDate.parse("05/10/1910", formatter)));
		personList.add(new Person(101, "Mia", "Buchanan", 3, new Address("93849", "New York"), "201-793-488",MaritalStatus.Single,LocalDate.parse("09/10/1980", formatter)));
		personList.add(new Person(102, "Samuel", "Lee", 2, new Address("86829", "New York"), "043-713-538",MaritalStatus.Married,LocalDate.parse("03/28/1910", formatter)));
		personList.add(new Person(103, "Lydia", "Ross", 73, new Address("63521", "New York"), "150-813-6462",MaritalStatus.Single,LocalDate.parse("07/22/1981", formatter)));
		personList.add(new Person(104, "Aaron", "Atkinson", 17, new Address("25415", "Washington"), "321-679-8544",MaritalStatus.Married,LocalDate.parse("05/10/1910", formatter)));
		personList.add(new Person(105, "London", "Woodward", 16, new Address("95632", "New York"), "187-338-588",MaritalStatus.Married,LocalDate.parse("02/10/1910", formatter)));

		Grid<Person> grid = new Grid<>();
		ListDataProvider<Person> dataProvider = new ListDataProvider<>(personList);
		grid.setDataProvider(dataProvider);

		List<ValueProvider<Person, String>> valueProviders = new ArrayList<>();
		valueProviders.add(Person::getFirstName);
		valueProviders.add(person -> String.valueOf(person.getAge()));
		valueProviders.add(person -> String.valueOf(person.getBirthdate()));
		valueProviders.add(person -> person.getAddress().getPostalCode());
		
		

		Iterator<ValueProvider<Person, String>> iterator = valueProviders.iterator();

		Column nameColumn = grid.addColumn(iterator.next()).setHeader("Name");
		grid.addColumn(iterator.next()).setHeader("Age");
		grid.addColumn(iterator.next()).setHeader("Birth Date");
		grid.addColumn(iterator.next()).setHeader("Postal Code");

		HeaderRow filterRow = grid.appendHeaderRow();

		Iterator<ValueProvider<Person, String>> iterator2 = valueProviders.iterator();
		Iterator<ValueProvider<Person, String>> iteratorFormarritalStatus = valueProviders.iterator();

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
