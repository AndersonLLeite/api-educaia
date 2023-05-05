package com.api.educaia.services;

import com.api.educaia.dtos.UserDTO;
import com.api.educaia.models.UserModel;
import com.api.educaia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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


}
