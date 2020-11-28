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
    @NotNull
    private String name;
    private GenderEnum gender;
    private LocalDate birthdate;
    private City city;
}
