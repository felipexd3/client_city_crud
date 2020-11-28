package com.blank.ilia.model;

import com.blank.ilia.model.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Client extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "GENDER IS REQUIRED")
    private GenderEnum gender;
    @NotNull(message = "BIRTHDATE IS REQUIRED")
    @Column(nullable = false)
    private LocalDate birthdate;
    private Integer age;
    @OneToOne
    @NotNull(message = "CITY IS REQUIRED")
    private City city;

    @PrePersist
    public void calculateAge() {
        age = Period.between(birthdate, LocalDate.now()).getYears();
    }
}
