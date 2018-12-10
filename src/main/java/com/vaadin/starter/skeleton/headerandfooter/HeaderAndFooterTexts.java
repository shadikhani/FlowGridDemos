package com.vaadin.starter.skeleton.headerandfooter;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.basicusage.PersonService;

@Route("HeaderAndFooterTexts")
public class HeaderAndFooterTexts extends VerticalLayout {

	public HeaderAndFooterTexts() {

		PersonService personService = new PersonService();
		List<Person> personList = personService.fetchAll();

		Grid<Person> grid = new Grid<>();
		grid.setItems(personList);

		grid.addColumn(Person::getFirstName).setHeader("First name")
				.setFooter("Total: " + personList.size() + " people");

		long averageOfAge = Math.round(personList.stream().mapToInt(Person::getAge).average().orElse(0));

		grid.addColumn(Person::getAge).setHeader("Age").setFooter("Average: " + averageOfAge);

		add(grid);
	}

}
