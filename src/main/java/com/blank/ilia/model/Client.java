package com.blank.ilia.model;

import com.blank.ilia.model.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
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
    private String name;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    private LocalDate birthdate;
    private Integer age;
    @OneToOne
    private City city;

    @PrePersist
    public void calculateAge() {
        age = Period.between(birthdate, LocalDate.now()).getYears();
    }
}
