package com.vaadin.starter.skeleton;

import org.jfairy.Fairy;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
public class MainView extends VerticalLayout {
	private final Fairy fairy = Fairy.create();

	public MainView() {
//Test
//		List<Person> personList = new ArrayList<>();
//		personList.add(new Person(1, "Shadi", "Khani", 1980, new Address("Iran", 1, "2145"), "09122053247"));
//		personList.add(new Person(2, "Shervin", "Khani", 1981, new Address("Ekbatan", 2, "7721"), "09122053247"));
//		personList.add(new Person(3, "Mehdi", "Javan", 1980, new Address("Ekbatan", 1, "2145"), "09122053247"));
//		personList.add(new Person(4, "Golestan", "Javan", 1980, new Address("Ekbatan", 1, "2145"), "09122053247"));

/////////////////////////////////////////////// Case 1: Configuring columns

//		// Providing a bean-type generates columns for all of it's properties
//		Grid<Person> grid = new Grid<>(Person.class);
//		grid.setItems(personList);
//
//		// Property-names are automatically set as keys
//		// You can remove undesired columns by using the key
//		grid.removeColumnByKey("id");
//		
//		// Columns for sub-properties can be added easily
//		grid.addColumn("address.postalCode");

		/////////////////////////////////////////////// Case 2: Compact and normal

//		Grid<Person> grid = new Grid<>();
//		grid.setItems(personList);
//
//		Column<Person> idColumn = grid.addColumn(Person::getId).setHeader("ID");
//		Column<Person> firstNaameColumn = grid.addColumn(Person::getFirstName).setHeader("First Name");
//		Column<Person> lastNameColumn = grid.addColumn(Person::getLastName).setHeader("Last Name");
//		Column<Person> address = grid.addColumn(Person::getAddress).setHeader("address");
//		Column<Person> phoneNumber = grid.addColumn(Person::getPhoneNumber).setHeader("phoneNumber");
//		Column<Person> yearOfBirth =grid.addColumn(Person::getYearOfBirth).setHeader("Year of Birth");
//
//		NativeButton compactButton = new NativeButton("Compact");
//		compactButton.addClickListener(event -> {
//
//			idColumn.setVisible(!idColumn.isVisible());
//			address.setVisible(!address.isVisible());
//			phoneNumber.setVisible(!phoneNumber.isVisible());
//
//			if (compactButton.getText().equals("Compact")) 
//				compactButton.setText("Normal");
//			 else 
//				compactButton.setText("Compact");
//
//		});

		////////////////////////////////////////////////////////////// Case 3: Frozen

//		Grid<Person> grid = new Grid<>();
//		grid.setItems(personList);
//
//		grid.addColumn(Person::getId).setHeader("ID").setFrozen(true);
//		grid.addColumn(Person::getFirstName).setHeader("First Name");
//		grid.addColumn(Person::getLastName).setHeader("Last Name");
//		grid.addColumn(Person::getPhoneNumber).setHeader("Phone Number");
//		grid.addColumn(Person::getAddress).setHeader("Adress");
//		grid.addColumn(Person::getYearOfBirth).setHeader("Year of Birth");
//
//		grid.setColumnReorderingAllowed(true);
//		GridMultiSelectionModel<?> multiSlection = (GridMultiSelectionModel<?>) grid
//				.setSelectionMode(SelectionMode.MULTI);
//		multiSlection.setSelectionColumnFrozen(true);

		////////////////////////////////////////////////////////////// Case 4: column
		////////////////////////////////////////////////////////////// alignment

		Grid<Person> grid = new Grid<>();
		grid.setItems(personList);
				
        final String priceTemplate = "<div style='text-align: right'>[[item.age]]</div>";
        grid.addColumn(TemplateRenderer.<Person>of(priceTemplate)
        		.withProperty("age", Person::getAge))
                .setHeader("Age");

		add(grid);

	}
}
