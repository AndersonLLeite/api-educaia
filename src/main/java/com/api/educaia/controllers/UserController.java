package com.api.educaia.controllers;

import com.api.educaia.dtos.UserDTO;
import com.api.educaia.dtos.UserIdentifierDTO;
import com.api.educaia.dtos.UserPublicDTO;
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
    public ResponseEntity<?> getUsersByClassId(@PathVariable String classId) {
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
        if (myRank < 30) {
            List<UserModel> users = userService.getUsersRankByClassId(classId);
            int myIndex = users.indexOf(user) + 1;
            return ResponseEntity.ok(myIndex);
        }
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
        if (myRank < 30) {
            List<UserModel> users = userService.getUsersRankBySchoolId(schoolId);
            int myIndex = users.indexOf(user) + 1;
            return ResponseEntity.ok(myIndex);
        }
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
        if (myRank < 30) {
            List<UserModel> users = userService.getUsersRankOverall();
            int myIndex = users.indexOf(user) + 1;
            return ResponseEntity.ok(myIndex);
        }
        return ResponseEntity.ok(myRank);
    }

    @GetMapping("/getUsersRankByClassId/{classId}")
    public ResponseEntity<?> getUsersRankByClassId(@PathVariable String classId) {
        List<UserModel> users = userService.getUsersRankByClassId(classId);
        List<UserPublicDTO> usersPublic = userService.getUsersPublic(users);
        return new ResponseEntity<List<UserPublicDTO>>(usersPublic, HttpStatus.OK);
    }

    @GetMapping("/getUsersRankBySchoolId/{schoolId}")
    public ResponseEntity<?> getUsersRankBySchoolId(@PathVariable String schoolId) {
        List<UserModel> users = userService.getUsersRankBySchoolId(schoolId);
        List<UserPublicDTO> usersPublic = userService.getUsersPublic(users);
        return new ResponseEntity<List<UserPublicDTO>>(usersPublic, HttpStatus.OK);
    }

    @GetMapping("/getUsersRankOverall")
    public ResponseEntity<?> getUsersRankOverall() {
        List<UserModel> users = userService.getUsersRankOverall();
        List<UserPublicDTO> usersPublic = userService.getUsersPublic(users);
        return new ResponseEntity<List<UserPublicDTO>>(usersPublic, HttpStatus.OK);
    }

    @PutMapping("/addFollow/{username}/{followerUsername}")
    public ResponseEntity<?> addFollow(@PathVariable String username, @PathVariable String followerUsername) {
        Optional<UserModel> userOp = userService.getUserByUsername(username);
        Optional<UserModel> followerOp = userService.getUserByUsername(followerUsername);
        if (!userOp.isPresent() || !followerOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        UserModel user = userOp.get();
        UserModel follower = followerOp.get();
        userService.addFollower(user, followerUsername);
        userService.addFollowing(follower, username);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/removeFollow/{username}/{followerUsername}")
    public ResponseEntity<?> removeFollow(@PathVariable String username, @PathVariable String followerUsername) {
        Optional<UserModel> userOp = userService.getUserByUsername(username);
        Optional<UserModel> followerOp = userService.getUserByUsername(followerUsername);
        if (!userOp.isPresent() || !followerOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        UserModel user = userOp.get();
        UserModel follower = followerOp.get();

        userService.removeFollower(user, followerUsername);
        userService.removeFollowing(follower, username);
        return ResponseEntity.ok().build();

}


        @GetMapping("/getFollowers/{username}")
        public ResponseEntity<?> getFollowers(@PathVariable String username) {
            Optional<UserModel> userOp = userService.getUserByUsername(username);
            if (!userOp.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            UserModel user = userOp.get();

            List<UserModel> followers = userService.getFollowers(user);
            List<UserPublicDTO> followersPublic = userService.getUsersPublic(followers);
            return new ResponseEntity<List<UserPublicDTO>>(followersPublic, HttpStatus.OK);
        }

        @GetMapping("/getFollowing/{username}")
        public ResponseEntity<?> getFollowing(@PathVariable String username) {
            Optional<UserModel> userOp = userService.getUserByUsername(username);
            if (!userOp.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            UserModel user = userOp.get();

            List<UserModel> following = userService.getFollowing(user);
            List<UserPublicDTO> followingPublic = userService.getUsersPublic(following);
            return new ResponseEntity<List<UserPublicDTO>>(followingPublic, HttpStatus.OK);
        }

        @PostMapping("/getIsFollowing/{username}")
        public ResponseEntity<?> getIsFollowing(@PathVariable String username, @RequestBody List<String> usernames) {
            Optional<UserModel> userOp = userService.getUserByUsername(username);
            if (!userOp.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            UserModel user = userOp.get();

            List<Boolean> isFollowing = userService.getIsFollowing(user, usernames);
            return new ResponseEntity<List<Boolean>>(isFollowing, HttpStatus.OK);
        }

        @GetMapping("/getUserByUsername/{username}")
        public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
            Optional<UserModel> userOp = userService.getUserByUsername(username);
            if (!userOp.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            UserModel user = userOp.get();
            UserPublicDTO userPublic = userService.getUserPublic(user);
            return new ResponseEntity<UserPublicDTO>(userPublic, HttpStatus.OK);
        }

        @GetMapping("/fetchTeachersBySchoolId/{schoolId}")
        public ResponseEntity<?> fetchTeachersByScoolId(@PathVariable String schoolId) {
            List<UserModel> teachers = userService.fetchTeachersByScoolId(schoolId);
            List<UserIdentifierDTO> teachersIdentifier = userService.getUsersIdentifier(teachers);
            return new ResponseEntity<List<UserIdentifierDTO>>(teachersIdentifier, HttpStatus.OK);

        }






}
