package com.lsl.service.role;

import com.github.pagehelper.PageInfo;
import com.lsl.system.Module;
import com.lsl.system.Role;

import java.util.List;


public interface RoleService {
    List<Role>  findAll();

    List<Role> edit(Role role);

    List<Role> deleteById(String id);

    Role findById(String id);

    PageInfo<Role> findByPage(int pageNum, int pageSize);

    List<Module> findModulesByRoleId(String id);

    List<Role> findRoleByUserId(String id);
}
