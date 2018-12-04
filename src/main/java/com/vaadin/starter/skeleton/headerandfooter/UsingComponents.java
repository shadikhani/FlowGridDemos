package com.vaadin.starter.skeleton.headerandfooter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.HeaderRow.HeaderCell;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.MaritalStatus;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.Person.Address;

@Route("UsingComponents")
public class UsingComponents extends VerticalLayout {

	public UsingComponents() {
		List<Person> personList = new ArrayList<>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		personList
				.add(new Person(100, "Elizabeth", "Blackwell", 11, new Address("12080", "Washington"),"127-942-237",MaritalStatus.Married, LocalDate.parse("05/10/1910", formatter)));
		personList.add(new Person(101, "Mia", "Buchanan", 3, new Address("93849", "New York"), "201-793-488",MaritalStatus.Single,LocalDate.parse("09/10/1980", formatter)));
		personList.add(new Person(102, "Samuel", "Lee", 2, new Address("86829", "New York"), "043-713-538",MaritalStatus.Married,LocalDate.parse("03/28/1910", formatter)));
		personList.add(new Person(103, "Lydia", "Ross", 73, new Address("63521", "New York"), "150-813-6462",MaritalStatus.Single,LocalDate.parse("07/22/1981", formatter)));
		personList.add(new Person(104, "Aaron", "Atkinson", 17, new Address("25415", "Washington"), "321-679-8544",MaritalStatus.Married,LocalDate.parse("05/10/1910", formatter)));
		personList.add(new Person(105, "London", "Woodward", 16, new Address("95632", "New York"), "187-338-588",MaritalStatus.Married,LocalDate.parse("02/10/1910", formatter)));

		ListDataProvider<Person> dataProvider = DataProvider.ofCollection(personList);

		Grid<Person> grid = new Grid<>();
		grid.setDataProvider(dataProvider);

		Column<Person> nameColumn = grid.addColumn(Person::getFirstName).setHeader(new Label("Name"))
				.setComparator((p1, p2) -> p1.getFirstName().compareToIgnoreCase(p2.getFirstName()));
		Column<Person> ageColumn = grid.addColumn(Person::getAge, "age").setHeader(new Label("Age"));
		Column<Person> streetColumn = grid.addColumn(person -> person.getAddress().getCity())
				.setHeader(new Label("City"));
		Column<Person> postalCodeColumn = grid.addColumn(person -> person.getAddress().getPostalCode())
				.setHeader(new Label("Postal Code"));

		HeaderRow topRow = grid.prependHeaderRow();

		HeaderCell informationCell = topRow.join(nameColumn, ageColumn);
		Button lessThanTwentyYearsold = new Button("-20", event -> {
			dataProvider.setFilter(person -> person.getAge() < 20);
		});

		Button twentyToForty = new Button("20-40", event -> {
			dataProvider.setFilter(person -> (person.getAge() >= 20 && person.getAge() <= 40));
		});

		Button overForty = new Button("+40", event -> {
			dataProvider.setFilter(person -> person.getAge() > 40);
		});

		Label label2 = new Label("Basic Information");

		HorizontalLayout filter = new HorizontalLayout(lessThanTwentyYearsold, twentyToForty, overForty);
		informationCell.setComponent(filter);

		HeaderCell addressCell = topRow.join(streetColumn, postalCodeColumn);
		addressCell.setComponent(new Label("Address Information"));

		grid.appendFooterRow().getCell(nameColumn).setComponent(new Label("Total: " + personList.size() + " people"));

		add(grid);

	}

}
