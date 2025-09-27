package crud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Users")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private Integer id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private Integer age;

    @Column(name = "created_at")
    private LocalDate date;
}
