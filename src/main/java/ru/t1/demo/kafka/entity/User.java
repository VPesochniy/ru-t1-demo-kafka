package ru.t1.demo.kafka.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
@Builder
@Entity
@Table(name = "users") /* user является ключевым словом в sql */
public class User {

    @Id
    @Setter(AccessLevel.NONE)
    @UuidGenerator
    private UUID id;

    @Column /* можно повесить кучу ограничений, но мы сейчас работаем с кафкой */
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
