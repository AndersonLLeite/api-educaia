package com.api.educaia.services;

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

    List<UserModel> getUsersByClassId(UUID classId);

    int getMyRankingByPointsAndClassId(int points, String classId);

    int getMyRankingByPointsAndSchoolId(int points, String schoolId);

    int getMyRankingOverAll(int points);

    List<UserModel> getUsersRankByClassId(String classId);

    List<UserModel> getUsersRankBySchoolId(String schoolId);

    List<UserModel> getUsersRankOverall();

    List<UserPublicDTO> getUsersPublic(List<UserModel> users);
}
