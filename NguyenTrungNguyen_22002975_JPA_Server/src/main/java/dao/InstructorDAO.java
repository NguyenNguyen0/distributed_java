package dao;

import models.Instructor;

public class InstructorDAO extends GenericDAO<Instructor> {
    public InstructorDAO() {
        super(Instructor.class);
    }
}