package com.lsl.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsl.system.User;
import com.lsl.dao.UserMapper;
import com.lsl.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;



    @Override
    public List<User> findAll() {
        List<User> companies = userMapper.findAll();
        return companies;
    }

    @Override
    public List<User> edit(User user) {
        if(user.getId().equals("")){
            UUID uuid = UUID.randomUUID();
            user.setId(uuid.toString());
            userMapper.save(user);
        }else{
            userMapper.update(user);
        }
        return findAll();
    }

    @Override
    public List<User> deleteById(String id) {
        userMapper.delete(id);
        return findAll();
    }

    @Override
    public User findById(String id) {
        User user = userMapper.findById(id);
        return user;
    }

    @Override
    public PageInfo<User> findByPage(int pageNum, int pageSize) {
        //1.设置当前页码，页面大小
        PageHelper.startPage(pageNum,pageSize);

        //2.查询所有数据
        List<User> list = userMapper.findAll();

        //3.封装PageInfo分页结果
        PageInfo<User> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }
}
