package com.blank.ilia.model.dto;

import com.blank.ilia.model.enums.StateEnum;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityDTO {
    @NotNull(message = "THE NAME OF CITY REQUIRED")
    private String name;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "THE STATE IS REQUIRED")
    private StateEnum state;
}
