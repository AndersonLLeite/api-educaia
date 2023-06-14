package com.api.educaia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserIdentifierDTO {
    private String id;
    private String nameComplete;
    private String profileImagePath;

}
