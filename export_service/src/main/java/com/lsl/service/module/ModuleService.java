package com.lsl.service.module;

import com.github.pagehelper.PageInfo;
import com.lsl.system.Module;

import java.util.List;


public interface ModuleService {
    List<Module>  findAll();

    List<Module> edit(Module module);

    List<Module> deleteById(String id);

    Module findById(String id);

    PageInfo<Module> findByPage(int pageNum, int pageSize);

    List<Module> findModulesByRoleId(String id);

}
