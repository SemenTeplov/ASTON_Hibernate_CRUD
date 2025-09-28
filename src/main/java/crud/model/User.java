package crud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Users")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
