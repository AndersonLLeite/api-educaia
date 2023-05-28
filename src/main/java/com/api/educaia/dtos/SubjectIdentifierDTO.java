package com.api.educaia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectIdentifierDTO {
    private UUID id;
    private String name;
}
