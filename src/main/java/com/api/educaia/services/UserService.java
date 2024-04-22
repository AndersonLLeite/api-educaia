package com.api.educaia.services;

import com.api.educaia.dtos.UserIdentifierDTO;
import com.api.educaia.dtos.UserPublicDTO;
import com.api.educaia.models.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserModel createUser(UserModel userModel);

    List<UserModel> listUsers();



    Optional<UserModel> getUserByUsername(String username);

    void addPointsToUser(UserModel user, int points);

    List<UserModel> getUsersByClassId(String classId);

    int getMyRankingByPointsAndClassId(int points, String classId);

    int getMyRankingByPointsAndSchoolId(int points, String schoolId);

    int getMyRankingOverAll(int points);

    List<UserModel> getUsersRankByClassId(String classId);

    List<UserModel> getUsersRankBySchoolId(String schoolId);

    List<UserModel> getUsersRankOverall();

    List<UserPublicDTO> getUsersPublic(List<UserModel> users);

    void addFollower(UserModel user, String followerUsername);

    void removeFollower(UserModel user, String followerUsername);

    List<UserModel> getFollowers(UserModel user);

    List<UserModel> getFollowing(UserModel user);

    void addFollowing(UserModel follower, String username);

    void removeFollowing(UserModel follower, String username);

    List<Boolean> getIsFollowing(UserModel user, List<String> usernames);

    UserPublicDTO getUserPublic(UserModel user);

    List<UserPublicDTO> getUsersRankForum();

    List<UserModel> getStudentsByClassId(String classId);

    List<UserIdentifierDTO> getUsersIdentifier(List<UserModel> students);

    List<UserModel> fetchTeachersByScoolId(String schoolId);

    void deleteUserByUserId(UUID userId);
}
