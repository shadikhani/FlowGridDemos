package com.vaadin.starter.skeleton.basicusage;

import java.util.List;

import com.vaadin.starter.skeleton.Person;

public class PersonService {
	private PersonData personData = new PersonData();

	public List<Person> fetch(int offset, int limit) {
		return personData.getPersons().subList(offset, offset + limit);
	}

	public int count() {
		return personData.getPersons().size();
	}

	public List<Person> fetchAll() {
		return personData.getPersons();
	}
}
