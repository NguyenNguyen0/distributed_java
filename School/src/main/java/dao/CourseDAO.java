package dao;

import models.Course;

public class CourseDAO extends GenericDAO<models.Course, Integer> {
    public CourseDAO() {
        super(Course.class);
    }
}
