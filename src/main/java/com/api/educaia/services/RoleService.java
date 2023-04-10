package com.api.educaia.services;

import com.api.educaia.models.RoleModel;

import java.util.List;

public interface RoleService {
    RoleModel createRole(RoleModel roleModel);

    List<RoleModel> listRoles();
}
