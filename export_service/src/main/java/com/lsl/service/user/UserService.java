package com.lsl.service.user;

import com.github.pagehelper.PageInfo;
import com.lsl.system.User;

import java.util.List;


public interface UserService {
    List<User>  findAll();

    List<User> edit(User user);

    List<User> deleteById(String id);

    User findById(String id);

    PageInfo<User> findByPage(int pageNum, int pageSize);
}
