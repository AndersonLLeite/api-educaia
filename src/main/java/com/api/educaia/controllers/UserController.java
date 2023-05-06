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
import java.util.UUID;

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

    @GetMapping("/get-users-by-class-id/{classId}")
    public ResponseEntity<?> getUsersByClassId(@PathVariable UUID classId) {
        List<UserModel> users = userService.getUsersByClassId(classId);
        return new ResponseEntity<List<UserModel>>(users, HttpStatus.OK);
    }




    @GetMapping("/getMyRankingInClass/{username}/{classId}")
    public ResponseEntity<?> getMyRanking(@PathVariable String username, @PathVariable String classId) {

        Optional<UserModel> userOp = userService.getUserByUsername(username);
        if (!userOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        UserModel user = userOp.get();

        int myRank = userService.getMyRankingByPointsAndClassId(user.getPoints(), classId);
        return ResponseEntity.ok(myRank);
    }

    @GetMapping("/getMyRankingInSchool/{username}/{schoolId}")
    public ResponseEntity<?> getMyRankingInSchool(@PathVariable String username, @PathVariable String schoolId) {

        Optional<UserModel> userOp = userService.getUserByUsername(username);
        if (!userOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        UserModel user = userOp.get();

        int myRank = userService.getMyRankingByPointsAndSchoolId(user.getPoints(), schoolId);
        return ResponseEntity.ok(myRank);
    }

    @GetMapping("/getMyRankingOverall/{username}")
    public ResponseEntity<?> getMyRankingOverall(@PathVariable String username) {
        Optional<UserModel> userOp = userService.getUserByUsername(username);
        if (!userOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        UserModel user = userOp.get();

        int myRank = userService.getMyRankingOverAll(user.getPoints());
        return ResponseEntity.ok(myRank);
    }

    @GetMapping("/getUsersRankByClassId/{classId}")
    public ResponseEntity<?> getUsersRankByClassId(@PathVariable String classId) {
        List<UserModel> users = userService.getUsersRankByClassId(classId);
        return new ResponseEntity<List<UserModel>>(users, HttpStatus.OK);
    }

    @GetMapping("/getUsersRankBySchoolId/{schoolId}")
    public ResponseEntity<?> getUsersRankBySchoolId(@PathVariable String schoolId) {
        List<UserModel> users = userService.getUsersRankBySchoolId(schoolId);
        return new ResponseEntity<List<UserModel>>(users, HttpStatus.OK);
    }

    @GetMapping("/getUsersRankOverall")
    public ResponseEntity<?> getUsersRankOverall() {
        List<UserModel> users = userService.getUsersRankOverall();
        return new ResponseEntity<List<UserModel>>(users, HttpStatus.OK);
    }


}
