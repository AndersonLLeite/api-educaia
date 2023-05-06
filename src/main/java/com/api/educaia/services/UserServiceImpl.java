package com.api.educaia.services;

import com.api.educaia.dtos.UserDTO;
import com.api.educaia.models.UserModel;
import com.api.educaia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public List<UserModel> getUsersByClassId(UUID classId) {
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
        Pageable top23Users = PageRequest.of(0, 23);
        return userRepository.getUsersRankByClassId(classId, top23Users);
    }

    @Override
    public List<UserModel> getUsersRankBySchoolId(String schoolId) {
        Pageable top23Users = PageRequest.of(0, 23);
        return userRepository.getUsersRankBySchoolId(schoolId, top23Users);
    }

    @Override
    public List<UserModel> getUsersRankOverall() {
        Pageable top23Users = PageRequest.of(0, 23);
        return userRepository.getUsersRankOverall(top23Users);
    }


}
