package com.api.educaia.controllers;

import com.api.educaia.dtos.RoleDTO;
import com.api.educaia.dtos.UserDTO;
import com.api.educaia.models.RoleModel;
import com.api.educaia.models.UserModel;
import com.api.educaia.services.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping(value = "/create-role", method = RequestMethod.POST)
    public ResponseEntity<?> createRole(@RequestBody @Valid RoleDTO roleDTO) {
        var roleModel = new RoleModel();
        BeanUtils.copyProperties(roleDTO, roleModel);
        RoleModel roleModelResponse  = roleService.createRole(roleModel);

        return new ResponseEntity<RoleModel>(roleModelResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list-roles", method = RequestMethod.GET)
    public ResponseEntity<?> listRoles() {

        List<RoleModel> roles = roleService.listRoles();
        return new ResponseEntity<List<RoleModel>>(roles, HttpStatus.OK);
    }
}
