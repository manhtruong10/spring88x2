package com.example.spring88x2.repository;

import com.example.spring88x2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUsersByUsernameAndPassword(String username, String password);

    @Modifying
    @Query(value = "SELECT u.* FROM user u " +
            "WHERE u.is_deleted = ?1 " +
            "ORDER BY u.id " +
            "lIMIT ?1, ?2 ", nativeQuery = true)
    List<User> findAll(int fromIndex, int pageSize, boolean isDeleted);

    @Modifying
    @Query(value = "SELECT u.* FROM user u " +
            "WHERE u.is_deleted = ?1 " +
            "AND (" +
                "u.first_name LIKE %?2% " +
                "OR u.last_name LIKE %?2% " +
                "OR u.username LIKE %?2% "+
            ")" +
            "ORDER BY u.id " +
            "lIMIT ?3, ?4 ", nativeQuery = true)
    List<User> findAllUser(boolean isDeleted, String keyword , int fromIndex, int pageSize);

    List<User> findAllByIdIn(List<Integer> ids);
}
