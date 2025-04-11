package model;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)

@Entity
public class OnsiteCourse extends Course implements Serializable{
	
	@Column(name="Location", columnDefinition = "nvarchar(50)")
	private String location;
	@Column(name = "Days", columnDefinition = "nvarchar(50)")
	private String days;
	@Column(name="Time")
	private Time time;
	

}
