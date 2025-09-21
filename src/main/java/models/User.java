package models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
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
