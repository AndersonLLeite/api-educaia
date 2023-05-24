package com.api.educaia.repositories;

import com.api.educaia.dtos.UserPublicDTO;
import com.api.educaia.models.UserModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByUsername(String username);

    List<UserModel> findByClassId(UUID classId);

    @Query("SELECT COUNT(*) FROM UserModel u WHERE u.points >= :points AND u.classId = :classId")
    int getRankingByPointsAndClassId(@Param("points") int points, @Param("classId") String classId);

    @Query("SELECT COUNT(*) FROM UserModel u WHERE u.points >= :points AND u.schoolId = :schoolId")
    int getRankingByPointsAndSchoolId(@Param("points") int points, @Param("schoolId") String schoolId);

    @Query("SELECT COUNT(*) FROM UserModel u WHERE u.points > :points ")
    int getRankingOverAll(@Param("points") int points);

    @Query("SELECT u FROM UserModel u WHERE u.classId = :classId ORDER BY u.points DESC")
    List<UserModel> getUsersRankByClassId(@Param("classId") String classId, Pageable pageable);

    @Query("SELECT u FROM UserModel u WHERE u.schoolId = :schoolId ORDER BY u.points DESC")
    List<UserModel> getUsersRankBySchoolId(@Param("schoolId") String schoolId, Pageable pageable);

    @Query("SELECT u FROM UserModel u ORDER BY u.points DESC")
    List<UserModel> getUsersRankOverall(Pageable pageable);


    @Query("SELECT u FROM UserModel u WHERE u.username IN :followers")
    List<UserModel> findByUsernameIn(List<String> followers);

    @Query("SELECT u FROM UserModel u ORDER BY u.forumPoints DESC")
    List<UserModel> getUsersRankForum(Pageable top20UsersForum);
}
