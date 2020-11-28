package com.blank.ilia.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Role implements Serializable {
    @Id
    private Long id;
    private String name;
}
