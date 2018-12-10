package com.vaadin.starter.skeleton.usingcomponents;

import com.vaadin.flow.component.html.Div;
import com.vaadin.starter.skeleton.Person;

public class PersonComponent extends Div {

	private String text;
//	private int timesClicked;

	/**
	 * Creates a new component with the given item.
	 *
	 * @param person the person to set
	 */
	public PersonComponent(Person person) {
		setPerson(person);
	}

	/**
	 * Sets the person for the component.
	 *
	 * @param person the person to be inside inside the cell
	 */
	public void setPerson(Person person) {
		text = "Hi, i'm the component for " + person.getFirstName() + "!";
		setText(text);
	}
}
