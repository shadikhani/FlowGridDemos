package com.vaadin.starter.skeleton.basicusage;

import java.util.Arrays;
import java.util.List;

import com.vaadin.starter.skeleton.Person;

public class PersonService {
	

	public List<Person> fetch(int offset, int limit) {
		return Arrays.asList(Arrays.copyOfRange(PersonData.PERSON_LIST, offset, offset + limit));
	}

	public int count() {
		return PersonData.PERSON_LIST.length;
	}

}
