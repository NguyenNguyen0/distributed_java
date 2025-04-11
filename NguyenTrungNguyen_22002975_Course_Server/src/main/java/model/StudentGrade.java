package model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
public class StudentGrade implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int enrollmentID;
	@ManyToOne
	@JoinColumn(name="StudentID")
	private Student student;
	@ManyToOne
	@JoinColumn(name="CourseID")
	private Course course;
	@Column(name="Grade", columnDefinition = "decimal(3, 2)")
	private double grade;
	

}
