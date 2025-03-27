package dao;

import models.StudentGrade;

public class StudentGradeDAO extends GenericDAO<StudentGrade> {
    public StudentGradeDAO() {
        super(StudentGrade.class);
    }
}