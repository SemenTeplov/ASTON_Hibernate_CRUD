package crud.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
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
    private Integer id;
    private String name;
    private String email;
    private Integer age;

    @JsonProperty("created_at")
    private LocalDate date;
}
