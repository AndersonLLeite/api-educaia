package com.api.educaia.controllers;

import com.api.educaia.dtos.UserDTO;
import com.api.educaia.models.UserModel;
import com.api.educaia.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
        System.out.println(userModel);
        UserModel userModelResponse  = userService.createUser(userModel);

        return new ResponseEntity<UserModel>(userModelResponse, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/list-users", method = RequestMethod.GET)
    public ResponseEntity<?> listUsers() {

        List<UserModel> users = userService.listUsers();
        return new ResponseEntity<List<UserModel>>(users, HttpStatus.OK);
    }
    @GetMapping("/me")
    public ResponseEntity<?> getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<UserModel> userOp = userService.getUserByUsername(userDetails.getUsername());
        if (!userOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        UserModel user = userOp.get();
        return ResponseEntity.ok(user);
    }

}
