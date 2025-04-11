package model;

import java.io.Serializable;
import java.sql.Timestamp;
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
public class OfficeAssignment implements Serializable{

	@Id
	@OneToOne
	@JoinColumn(name = "InstructorID", unique = true)
	private Instructor instructor;
	
	@Column(name="Location", columnDefinition = "nvarchar(50)")
	private String location;
	@Column(name="Timestamp", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private Timestamp timestamp;


	
}
