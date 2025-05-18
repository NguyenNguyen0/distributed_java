package model;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "book_translations")
public class BookTranslation extends Book implements java.io.Serializable {
    private String language;
    @Column(name = "translate_name")
    private String translateName;
}
