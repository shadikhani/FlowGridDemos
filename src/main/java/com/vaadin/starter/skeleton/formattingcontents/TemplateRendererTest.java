package com.vaadin.starter.skeleton.formattingcontents;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.MaritalStatus;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.Person.Address;

@Route("TemplateRendererTest")
public class TemplateRendererTest extends VerticalLayout {

	public TemplateRendererTest() {

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
		grid.setItems(personList);

		// You can use the [[index]] variable to print the row index (0 based)
		grid.addColumn(TemplateRenderer.of("[[index]]")).setHeader("#");

		// You can set any property by using `withProperty`, including
		// properties not present on the original bean.
		grid.addColumn(TemplateRenderer
				.<Person>of("<div title='[[item.name]]'>[[item.name]]<br><small>[[item.yearsOld]]</small></div>")
				.withProperty("name", Person::getFirstName).withProperty("yearsOld",
						person -> person.getAge() > 1 ? person.getAge() + " years old" : person.getAge() + " year old"))
				.setHeader("Person");

		// You can also set complex objects directly. Internal properties of the
		// bean are accessible in the template.
		grid.addColumn(TemplateRenderer.<Person>of(
				"<div>[[item.address.street]], number [[item.address.number]]<br><small>[[item.address.postalCode]]</small></div>")
				.withProperty("address", Person::getAddress)).setHeader("Address");

		// You can set events handlers associated with the template. The syntax
		// follows the Polymer convention "on-event", such as "on-click".
		grid.addColumn(TemplateRenderer.<Person>of(
				"<button on-click='handleUpdate'>Update</button><button on-click='handleRemove'>Remove</button>")
				.withEventHandler("handleUpdate", person -> {
					person.setFirstName(person.getFirstName() + " Updated");
					grid.getDataProvider().refreshItem(person);
				}).withEventHandler("handleRemove", person -> {
					ListDataProvider<Person> dataProvider = (ListDataProvider<Person>) grid.getDataProvider();
					dataProvider.getItems().remove(person);
					dataProvider.refreshAll();
				})).setHeader("Actions");

		grid.setSelectionMode(SelectionMode.NONE);

		add(grid);
	}

}
