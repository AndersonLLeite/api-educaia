package com.api.educaia.dtos;

import com.api.educaia.models.RoleModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
