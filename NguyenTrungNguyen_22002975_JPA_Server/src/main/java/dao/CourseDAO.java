package dao;

import models.Course;

public class CourseDAO extends GenericDAO<Course> {
    public CourseDAO() {
        super(Course.class);
    }
}