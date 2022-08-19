package com.example.spring88x2.service;

import com.example.spring88x2.service.dto.IncrementAgeDTO;
import com.example.spring88x2.service.dto.LogginDTO;
import com.example.spring88x2.entity.User;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface UserService {
    List<User> getAllUser(int pageIndex, int pageSize);

    List<User> getAll(String keyword, int pageIndex, int pageSize);

    List<User> listAll();

    User findById(Integer id);

    void save(User user);

    void delete(Integer id);

    User findUserByUsernameAndPassword(LogginDTO loginDTO);

    void incrementAge(IncrementAgeDTO incrementAgeDTO);

    void incrementLastName(IncrementAgeDTO incrementAgeDTO);
}
