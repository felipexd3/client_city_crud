package com.blank.ilia.model.dto;

import com.blank.ilia.model.City;
import com.blank.ilia.model.enums.GenderEnum;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {
    @NotNull(message = "NAME IS REQUIRED")
    private String name;
    @NotNull(message = "GENDER IS REQUIRED")
    private GenderEnum gender;
    @NotNull(message = "BIRTHDATE IS REQUIRED")
    private LocalDate birthdate;
    @NotNull(message = "CITY IS REQUIRED")
    private City city;
}
