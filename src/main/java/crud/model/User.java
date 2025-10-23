package crud.model;

import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(description = "Сущность пользователя")
public class User {
    @Id
    @Schema(description = "Уникальный идентификатор пользователя", example = "123", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Column
    @Schema(description = "ФИО пользователя", example = "Петров Петр Петрович")
    private String name;

    @Column
    @Schema(description = "Электронная почта пользователя", example = "email@mail.com")
    private String email;

    @Column
    @Schema(description = "Возраст пользователя", example = "45")
    private Integer age;

    @Column(name = "created_at")
    @Schema(description = "Дата регистрации пользователя", example = "2000-01-01", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDate date;
}
