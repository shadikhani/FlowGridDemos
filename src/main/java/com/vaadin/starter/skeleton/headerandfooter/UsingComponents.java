package com.vaadin.starter.skeleton.headerandfooter;

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
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.basicusage.PersonService;

@Route("UsingComponents")
public class UsingComponents extends VerticalLayout {

	public UsingComponents() {
		
		PersonService personService = new PersonService();
		List<Person> personList = personService.fetchAll();

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
		Button lessThanTwentyYearsold = new Button("-20 years old", event -> {
			dataProvider.setFilter(person -> person.getAge() < 20);
		});

		Button twentyToForty = new Button("Between 20-40 years old", event -> {
			dataProvider.setFilter(person -> (person.getAge() >= 20 && person.getAge() <= 40));
		});

		Button overForty = new Button("+40 years old", event -> {
			dataProvider.setFilter(person -> person.getAge() > 40);
		});
		

		HorizontalLayout filter = new HorizontalLayout(lessThanTwentyYearsold, twentyToForty, overForty);
		informationCell.setComponent(filter);

		HeaderCell addressCell = topRow.join(streetColumn, postalCodeColumn);
		addressCell.setComponent(new Label("Address Information"));

		grid.appendFooterRow().getCell(nameColumn).setComponent(new Label("Total: " + personList.size() + " people"));

		add(grid);

	}

}
