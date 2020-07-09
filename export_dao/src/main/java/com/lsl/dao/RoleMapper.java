package com.lsl.dao;

import com.lsl.system.Module;
import com.lsl.system.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    List<Role> findAll();

    void update(Role role);

    void save(Role role);

    void delete(String id);

    Role findById(String id);

    List<Module> findModulesByRoleId(String id);

    List<Role> findRoleByUserId(String id);
}
