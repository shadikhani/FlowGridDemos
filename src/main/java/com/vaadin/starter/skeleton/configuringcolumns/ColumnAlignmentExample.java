package com.vaadin.starter.skeleton.configuringcolumns;

import java.util.List;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.skeleton.Person;
import com.vaadin.starter.skeleton.basicusage.PersonService;

@Route("ColumnAlignmentExample2")
public class ColumnAlignmentExample extends VerticalLayout{
	
	public ColumnAlignmentExample() {
		
		PersonService personService = new PersonService();
		List<Person> personList = personService.fetchAll();
		
		Grid<Person> grid = new Grid<>();

		grid.setItems(personList);

		grid.addColumn(Person::getFirstName)
		        .setHeader("First Name");

		// Setting a column-key allows fetching the column later
		grid.addColumn(Person::getAge).setHeader("Age").setKey("age");
		grid.getColumnByKey("age");
		

		RadioButtonGroup<ColumnTextAlign> alignments = new RadioButtonGroup<>();
		alignments.setItems(ColumnTextAlign.values());
		alignments.setLabel("Text alignment for the Age column");
		
		//ColumnTextAlign is a grid feature enum that is used to configure text alignment inside columns.
		alignments.setValue(ColumnTextAlign.START);
		alignments.addValueChangeListener(event -> grid.getColumnByKey("age")
		        .setTextAlign(event.getValue()));
		
		
		add(grid,alignments);
		
	}

}
