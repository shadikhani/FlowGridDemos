package com.vaadin.starter.skeleton.itemdetails;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.basicusage.PersonService;

@Route("ItemDetails")
public class ItemDetails extends VerticalLayout {

	public ItemDetails() {
		
		PersonService personService = new PersonService();
		List<Person> personList = personService.fetchAll();
		
		Grid<Person> grid = new Grid<>();
		
		grid.setItems(personList);

		grid.addColumn(Person::getFirstName).setHeader("First Name");
		grid.addColumn(Person::getAge).setHeader("Age");

		grid.setSelectionMode(SelectionMode.NONE);

		// You can use any renderer for the item details. By default, the
		// details are opened and closed by clicking the rows.
		grid.setItemDetailsRenderer(TemplateRenderer.<Person> of(
		        "<div style='border: 1px solid gray; padding: 10px; width: 100%; box-sizing: border-box;'>"
		                + "<div>Hi! My name is <b>[[item.firstname]]!</b></div>"
		                + "<div><img style='height: 80px; width: 80px;' src='[[item.image]]'/></div>"
		                + "</div>")
		        .withProperty("firstname", Person::getFirstName)
		        .withProperty("lastname", Person::getLastName)
		        .withProperty("address", Person::getAddress)
		        .withProperty("image",  Person::getImage)
		        .withEventHandler("handleClick", person -> {
		            grid.getDataProvider().refreshItem(person);
		        }));

		add(grid);

	}
}
