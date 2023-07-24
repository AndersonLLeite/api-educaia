package com.api.educaia.dtos;

import com.api.educaia.models.RoleModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank
    private String username;
    @NotBlank
    private int enrollment;
    @NotBlank

    private String nameComplete;
    @NotBlank
    private String password;
    @NotBlank
    private String role;
    @NotBlank
    private String schoolId;

}
