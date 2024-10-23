package ru.t1.demo.kafka.entity;

import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table(name = "users") /* user является ключевым словом в sql */
@Entity
public class User {

    @Id
    @UuidGenerator
    private UUID id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private Integer age;

    @Column
    @Enumerated(EnumType.STRING)
    private UserGender gender;

}