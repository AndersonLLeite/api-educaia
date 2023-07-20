package com.api.educaia.services;

import com.api.educaia.dtos.UserDTO;
import com.api.educaia.dtos.UserIdentifierDTO;
import com.api.educaia.dtos.UserPublicDTO;
import com.api.educaia.enums.RoleName;
import com.api.educaia.models.UserModel;
import com.api.educaia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements  UserService{
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserModel createUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public List<UserModel> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void addPointsToUser(UserModel user, int points) {
        user.setPoints(user.getPoints() + points);
        userRepository.save(user);
    }

    @Override
    public List<UserModel> getUsersByClassId(String classId) {
        return userRepository.findByClassId(classId);
    }

    @Override
    public int getMyRankingByPointsAndClassId(int points, String classId) {
        return userRepository.getRankingByPointsAndClassId(points, classId);
    }

    @Override
    public int getMyRankingByPointsAndSchoolId(int points, String schoolId) {
        return userRepository.getRankingByPointsAndSchoolId(points, schoolId);
    }

    @Override
    public int getMyRankingOverAll(int points) {

        return userRepository.getRankingOverAll(points);
    }

    @Override
    public List<UserModel> getUsersRankByClassId(String classId) {
        Pageable top23Users = PageRequest.of(0, 30);
        return userRepository.getUsersRankByClassId(classId, top23Users);
    }

    @Override
    public List<UserModel> getUsersRankBySchoolId(String schoolId) {
        Pageable top23Users = PageRequest.of(0, 30);
        return userRepository.getUsersRankBySchoolId(schoolId, top23Users);
    }

    @Override
    public List<UserModel> getUsersRankOverall() {
        Pageable top23Users = PageRequest.of(0, 23);
        return userRepository.getUsersRankOverall(top23Users);
    }

    @Override
    public List<UserPublicDTO> getUsersPublic(List<UserModel> users) {
        List<UserPublicDTO> usersPublicDTO = new ArrayList<>();
        for (UserModel user : users) {
            UserPublicDTO userCopy = new UserPublicDTO( user.getUsername(), user.getNameComplete(), user.getPoints(),user.getSchoolId(), user.getProfileImagePath(), user.getMedalImagePath(), user.getFollowers(), user.getFollowing(), user.getForumPoints() );
            usersPublicDTO.add(userCopy);
        }
        return usersPublicDTO;
    }
    @Transactional
    @Override
    public void addFollower(UserModel user, String followerUsername) {
        if (user.getFollowers().contains(followerUsername)) {
            return;
        }
        user.addFollower(followerUsername);
        userRepository.save(user);
    }
    @Transactional
    @Override
    public void removeFollower(UserModel user, String followerUsername) {
        user.removeFollower(followerUsername);
        userRepository.save(user);
    }

    @Override
    public List<UserModel> getFollowers(UserModel user) {
        List<UserModel> followers = userRepository.findByUsernameIn(user.getFollowers());
        return followers;
    }

    @Override
    public List<UserModel> getFollowing(UserModel user) {
        List<UserModel> following = userRepository.findByUsernameIn(user.getFollowing());
        return following;
    }



    @Override
    public void addFollowing(UserModel follower, String username) {
        if (follower.getFollowing().contains(username)) {
            return;
        }
        follower.addFollowing(username);
        userRepository.save(follower);
    }

    @Override
    public void removeFollowing(UserModel follower, String username) {
        follower.removeFollowing(username);
        userRepository.save(follower);

    }

    @Override
    public List<Boolean> getIsFollowing(UserModel user, List<String> usernames) {
        List<Boolean> isFollowing = new ArrayList<>();
        for (String username : usernames) {
            isFollowing.add(user.getFollowing().contains(username));
        }
        return isFollowing;
    }

    @Override
    public UserPublicDTO getUserPublic(UserModel user) {
        return new UserPublicDTO(user.getUsername(), user.getNameComplete(), user.getPoints(), user.getSchoolId(), user.getProfileImagePath(), user.getMedalImagePath(), user.getFollowers(), user.getFollowing(), user.getForumPoints());
    }

    @Override
    public List<UserPublicDTO> getUsersRankForum() {
        Pageable top20UsersForum = PageRequest.of(0, 20);
        List<UserModel> usersRankForum = userRepository.getUsersRankForum(top20UsersForum);
        return getUsersPublic(usersRankForum);
    }

    @Override
    public List<UserModel> getStudentsByClassId(String classId) {
        return userRepository.findByClassId(classId);
    }

    @Override
    public List<UserIdentifierDTO> getUsersIdentifier(List<UserModel> users) {
        List<UserIdentifierDTO> usersIdentifier = new ArrayList<>();
        for (UserModel user : users) {
            usersIdentifier.add(new UserIdentifierDTO(user.getId(), user.getNameComplete(), user.getProfileImagePath()));
        }
        return usersIdentifier;
    }

    @Override
    public List<UserModel> fetchTeachersByScoolId(String schoolId) {
        return userRepository.getBySchoolIdAndRole(schoolId, RoleName.ROLE_TEACHER);
    }
    @Transactional
    @Override
    public void deleteUserByUserId(UUID userId) {
        userRepository.deleteById(userId);
    }


}
