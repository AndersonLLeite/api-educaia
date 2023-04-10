package com.api.educaia.dtos;

import com.api.educaia.enums.RoleName;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class RoleDTO {
    @NotBlank
    private String roleName;
}
