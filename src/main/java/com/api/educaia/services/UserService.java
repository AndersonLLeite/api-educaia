package com.api.educaia.services;

import com.api.educaia.models.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserModel createUser(UserModel userModel);

    List<UserModel> listUsers();



    Optional<UserModel> getUserByUsername(String username);

    void addPointsToUser(UserModel user, int points);
}
