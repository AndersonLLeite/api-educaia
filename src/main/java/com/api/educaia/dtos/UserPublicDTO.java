package com.api.educaia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserPublicDTO {
    private String nameComplete;
    private int points;
    private String schoolId;
    private String profileImagePath;
    private List<String> medalImagePath;
}
