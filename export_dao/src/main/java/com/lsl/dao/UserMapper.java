package com.lsl.dao;

import com.lsl.system.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> findAll();

    void update(User user);

    void save(User user);

    void delete(String id);

    User findById(String id);

}
