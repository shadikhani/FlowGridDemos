package com.vaadin.starter.skeleton.usingcomponents;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.starter.skeleton.Person;

public class PersonCard extends Div{

	/**
     * Constructor that takes a Person as parameter.
     *
     * @param person
     *            the person to be used inside the card
     */
    public PersonCard(Person person) {
        addClassName("custom-details");
        setId("person-card-" + person.getId());

        VerticalLayout layout1 = new VerticalLayout();
        layout1.add(new Label("Name: " + person.getFirstName()));
        layout1.add(new Label("Id: " + person.getId()));
        layout1.add(new Label("Age: " + person.getAge()));

        VerticalLayout layout2 = new VerticalLayout();
        layout2.add(
                new Label("Street: " + person.getAddress().getStreet()));
        layout2.add(new Label(
                "Address number: " + person.getAddress().getNumber()));
        layout2.add(new Label(
                "Postal Code: " + person.getAddress().getPostalCode()));

        HorizontalLayout hlayout = new HorizontalLayout(layout1, layout2);
        hlayout.getStyle().set("border", "1px solid gray")
                .set("padding", "10px").set("boxSizing", "border-box")
                .set("width", "100%");

        add(hlayout);
    }
}