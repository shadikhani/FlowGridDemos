package com.vaadin.starter.skeleton.usingcomponents;

import com.vaadin.flow.component.html.Div;
import com.vaadin.starter.skeleton.Person;

public class PersonComponent extends Div {

	private String text;
	private int timesClicked;

	/**
	 * Creates a new component with the given item.
	 *
	 * @param person the person to set
	 */
	public PersonComponent(Person person) {
//		this.addClickListener(event -> {
//			timesClicked++;
//			setText(text + "\nClicked " + timesClicked);
//		});
		setPerson(person);
	}

	/**
	 * Sets the person for the component.
	 *
	 * @param person the person to be inside inside the cell
	 */
	public void setPerson(Person person) {
		text = "Hi, I'm " + person.getFirstName() + "!";
		setText(text);
	}

	@Override
	public int hashCode() {
		return text == null ? 0 : text.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PersonComponent)) {
			return false;
		}
		PersonComponent other = (PersonComponent) obj;
		if (text == null) {
			if (other.text != null) {
				return false;
			}
		} else if (!text.equals(other.text)) {
			return false;
		}
		return true;
	}
}
