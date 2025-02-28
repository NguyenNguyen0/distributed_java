package models;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Department {
    @SerializedName("department_id")
    private String id;
    private String name;
    private String dean;
    private String building;
    private int room;
}