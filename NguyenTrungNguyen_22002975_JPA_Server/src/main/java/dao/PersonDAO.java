package dao;

import models.Person;

public class PersonDAO extends GenericDAO<Person> {
    public PersonDAO() {
        super(Person.class);
    }
}