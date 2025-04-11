package model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Course implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CourseID")
	protected int id;
	@Column(name = "Title", columnDefinition = "nvarchar(100)")
	protected String title;
	@Column(name="Credits")
	protected int credits;

	@ManyToOne
	@JoinColumn(name="DepartmentID")
	protected Department department;

	@ManyToMany(mappedBy = "courses")
	protected Set<Instructor> instructors;
	

}
