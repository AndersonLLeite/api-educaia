package com.api.educaia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class UserIdentifierDTO {
    private UUID id;
    private String nameComplete;
    private int enrollment;
    private String profileImagePath;

}
