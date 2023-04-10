package com.api.educaia.services;

import com.api.educaia.models.RoleModel;
import com.api.educaia.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;
    @Transactional
    @Override
    public RoleModel createRole(RoleModel roleModel) {
        return roleRepository.save(roleModel);
    }

    @Override
    public List<RoleModel> listRoles() {
        return roleRepository.findAll();
    }

    public RoleModel findByName(String roleName) {
        return roleRepository.findByRoleName(roleName).orElseThrow(() -> new RuntimeException("Error: Role is not found."));

    }
}
