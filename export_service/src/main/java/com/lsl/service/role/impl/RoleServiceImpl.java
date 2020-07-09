package com.lsl.service.role.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsl.dao.RoleMapper;
import com.lsl.system.Module;
import com.lsl.system.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsl.service.role.RoleService;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;



    @Override
    public List<Role> findAll() {
        List<Role> companies = roleMapper.findAll();
        return companies;
    }

    @Override
    public List<Role> edit(Role role) {
        if(role.getId().equals("")){
            UUID uuid = UUID.randomUUID();
            role.setId(uuid.toString());
            roleMapper.save(role);
        }else{
            roleMapper.update(role);
        }
        return findAll();
    }

    @Override
    public List<Role> deleteById(String id) {
        roleMapper.delete(id);
        return findAll();
    }

    @Override
    public Role findById(String id) {
        Role role = roleMapper.findById(id);
        return role;
    }

    @Override
    public PageInfo<Role> findByPage(int pageNum, int pageSize) {
        //1.设置当前页码，页面大小
        PageHelper.startPage(pageNum,pageSize);

        //2.查询所有数据
        List<Role> list = roleMapper.findAll();

        //3.封装PageInfo分页结果
        PageInfo<Role> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public List<Module> findModulesByRoleId(String id) {
        List<Module> modules= roleMapper.findModulesByRoleId(id);
        return modules;
    }

    @Override
    public List<Role> findRoleByUserId(String id) {
        List<Role> list = roleMapper.findRoleByUserId(id);
        return list;
    }
}
