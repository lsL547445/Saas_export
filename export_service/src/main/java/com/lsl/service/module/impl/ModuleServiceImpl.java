package com.lsl.service.module.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsl.dao.ModuleDao;
import com.lsl.service.module.ModuleService;
import com.lsl.system.Module;
import com.lsl.system.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleDao moduleMapper;



    @Override
    public List<Module> findAll() {
        List<Module> companies = moduleMapper.findAll();
        return companies;
    }

    @Override
    public List<Module> edit(Module module) {
        if(module.getId().equals("")){
            UUID uuid = UUID.randomUUID();
            module.setId(uuid.toString());
            moduleMapper.save(module);
        }else{
            moduleMapper.update(module);
        }
        return findAll();
    }

    @Override
    public List<Module> deleteById(String id) {
        moduleMapper.delete(id);
        return findAll();
    }

    @Override
    public Module findById(String id) {
        Module module = moduleMapper.findById(id);
        return module;
    }

    @Override
    public PageInfo<Module> findByPage(int pageNum, int pageSize) {
        //1.设置当前页码，页面大小
        PageHelper.startPage(pageNum,pageSize);

        //2.查询所有数据
        List<Module> list = moduleMapper.findAll();

        //3.封装PageInfo分页结果
        PageInfo<Module> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public List<Module> findModulesByRoleId(String id) {
        List<Module> modules= moduleMapper.findModulesByRoleId(id);
        return modules;
    }
}
