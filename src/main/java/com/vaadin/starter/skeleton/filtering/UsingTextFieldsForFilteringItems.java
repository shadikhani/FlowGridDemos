package com.vaadin.starter.skeleton.filtering;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.MaritalStatus;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.Person.Address;

@Route("UsingTextFieldsForFilteringItems")
public class UsingTextFieldsForFilteringItems extends VerticalLayout{
	
	public UsingTextFieldsForFilteringItems() {
		
		List<Person> personList = new ArrayList<>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		personList
				.add(new Person(100, "Mickael", "Blackwell", 68, new Address("12080", "Washington"),"127-942-237",MaritalStatus.Married, LocalDate.parse("05/10/1950", formatter)));
		personList.add(new Person(101, "Peter", "Buchanan", 38, new Address("93849", "New York"), "201-793-488",MaritalStatus.Single,LocalDate.parse("09/10/1980", formatter)));
		personList.add(new Person(102, "Samuel", "Lee", 53, new Address("86829", "New York"), "043-713-538",MaritalStatus.Married,LocalDate.parse("03/28/1965", formatter)));
		personList.add(new Person(103, "Anton", "Ross", 37, new Address("63521", "New York"), "150-813-6462",MaritalStatus.Single,LocalDate.parse("07/22/1981", formatter)));
		personList.add(new Person(104, "Aaron", "Atkinson", 18, new Address("25415", "Washington"), "321-679-8544",MaritalStatus.Single,LocalDate.parse("05/10/2000", formatter)));
		personList.add(new Person(105, "Jack", "Woodward", 28, new Address("95632", "New York"), "187-338-588",MaritalStatus.Married,LocalDate.parse("02/10/1990", formatter)));
		
		Grid<Person> grid = new Grid<>();
		ListDataProvider<Person> dataProvider = new ListDataProvider<>(
				personList);
		grid.setDataProvider(dataProvider);

		List<ValueProvider<Person, String>> valueProviders = new ArrayList<>();
		valueProviders.add(Person::getFirstName);
		valueProviders.add(person -> String.valueOf(person.getAge()));
		valueProviders.add(person -> person.getAddress().getStreet());
		valueProviders.add(person -> person.getAddress().getPostalCode());

		Iterator<ValueProvider<Person, String>> iterator = valueProviders
		        .iterator();

		grid.addColumn(iterator.next()).setHeader("Name");
		grid.addColumn(iterator.next()).setHeader("Age");
		grid.addColumn(iterator.next()).setHeader("Street");
		grid.addColumn(iterator.next()).setHeader("Postal Code");

		HeaderRow filterRow = grid.appendHeaderRow();

		Iterator<ValueProvider<Person, String>> iterator2 = valueProviders
		        .iterator();

		grid.getColumns().forEach(column -> {
		    TextField field = new TextField();
		    ValueProvider<Person, String> valueProvider = iterator2.next();

		    field.addValueChangeListener(event -> dataProvider
		            .addFilter(person -> StringUtils.containsIgnoreCase(
		                    valueProvider.apply(person), field.getValue())));

		    field.setValueChangeMode(ValueChangeMode.EAGER);

		    filterRow.getCell(column).setComponent(field);
		    field.setSizeFull();
		    field.setPlaceholder("Filter");
		});
		
		add(grid);
	}

}
