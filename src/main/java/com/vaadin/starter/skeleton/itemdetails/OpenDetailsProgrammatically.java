package com.vaadin.starter.skeleton.itemdetails;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.basicusage.PersonService;

@Route("OpenDetailsProgrammatically")
public class OpenDetailsProgrammatically extends VerticalLayout{
	
	public OpenDetailsProgrammatically() {
		
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
		        "<div class='custom-details' style='border: 1px solid gray; padding: 10px; width: 100%; box-sizing: border-box;'>"
		                + "<div>Hi! My name is <b>[[item.firstname]]!</b></div>"
		                + "</div>")
		        .withProperty("firstname", Person::getFirstName)
		        //This is now how we open the details
		        .withEventHandler("handleClick", person -> {
		            grid.getDataProvider().refreshItem(person);
		        }));
		
		// Disable the default way of opening item details:
		grid.setDetailsVisibleOnClick(false);

		grid.addColumn(new NativeButtonRenderer<>("Details",
		        item -> grid.setDetailsVisible(item,
		                !grid.isDetailsVisible(item))));

		add(grid);
		
	}

}
