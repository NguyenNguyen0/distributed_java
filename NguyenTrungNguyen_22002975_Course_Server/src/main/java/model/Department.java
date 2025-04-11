package model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class Department implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="DepartmentID")
	private int id;
	@Column(name="Name", columnDefinition = "nvarchar(50)")
	private String name;
	@Column(name="Budget")
	private double budget;
	@Column(name="StartDate")
	private LocalDateTime startDate;
	@Column(name="Administrator")
	private int administrator;
	

}