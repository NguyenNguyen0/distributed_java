package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)

@Entity
public class OnlineCourse extends Course implements Serializable {

	@Column(name = "URL", columnDefinition = "nvarchar(100)")
	private String url;
	
}
