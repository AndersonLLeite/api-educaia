package com.api.educaia.dtos;

import com.api.educaia.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    @NotBlank
    private String roleName;
}
