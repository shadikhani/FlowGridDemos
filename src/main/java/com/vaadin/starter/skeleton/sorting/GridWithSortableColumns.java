package com.vaadin.starter.skeleton.sorting;

import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.basicusage.PersonService;

@Route("GridWithSortableColumns")
public class GridWithSortableColumns extends VerticalLayout {

	public GridWithSortableColumns() {

		PersonService personService = new PersonService();
		List<Person> personList = personService.fetchAll();

		Grid<Person> grid = new Grid<>();
		grid.setItems(personList);
		grid.setSelectionMode(SelectionMode.NONE);

		grid.addColumn(Person::getFirstName, "first name").setHeader("First Name");
		grid.addColumn(Person::getLastName, "last name").setHeader("Last Name");
		grid.addColumn(Person::getAge, "age").setHeader("Age");

		grid.addColumn(TemplateRenderer.<Person>of("<div>[[item.city]]<br><small>[[item.postalCode]]</small></div>")

				.withProperty("city", person -> person.getAddress().getCity())
				.withProperty("postalCode", person -> person.getAddress().getPostalCode()), "city", "postalCode")
				.setHeader("Address");

		Checkbox multiSort = new Checkbox("Multiple column sorting enabled");
		Label messageDiv = new Label();
		multiSort.addValueChangeListener(event -> grid.setMultiSort(event.getValue()));

		// you can set the sort order from server-side with the grid.sort method
		NativeButton invertAllSortings = new NativeButton("Invert all sort directions", event -> {
			List<GridSortOrder<Person>> newList = grid.getSortOrder().stream()
					.map(order -> new GridSortOrder<>(order.getSorted(), order.getDirection().getOpposite()))
					.collect(Collectors.toList());
			grid.sort(newList);
		});

		NativeButton resetAllSortings = new NativeButton("Reset all sortings", event -> grid.sort(null));

		add(grid, multiSort, messageDiv, invertAllSortings, resetAllSortings);
	}

}
