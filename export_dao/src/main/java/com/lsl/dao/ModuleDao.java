package com.lsl.dao;

import com.lsl.system.Module;
import com.lsl.system.Module;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleDao {
    List<Module> findAll();

    void update(Module module);

    void save(Module module);

    void delete(String id);

    Module findById(String id);

    List<Module> findModulesByRoleId(String id);
}
