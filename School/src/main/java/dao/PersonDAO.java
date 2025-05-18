package dao;

public class PersonDAO extends GenericDAO<models.Person, Integer>{
    public PersonDAO() {
        super(models.Person.class);
    }
}
