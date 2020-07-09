package com.lsl.service.dept.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsl.dao.DeptMapper;
import com.lsl.service.dept.DeptService;
import com.lsl.system.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;



    @Override
    public List<Dept> findAll() {
        List<Dept> companies = deptMapper.findAll();
        return companies;
    }

    @Override
    public List<Dept> edit(Dept dept) {
        if(dept.getId().equals("")){
            UUID uuid = UUID.randomUUID();
            dept.setId(uuid.toString());
            deptMapper.add(dept);
        }else{
            deptMapper.save(dept);
        }
        return findAll();
    }

    @Override
    public List<Dept> deleteById(String id) {
        deptMapper.deleteById(id);
        return findAll();
    }

    @Override
    public Dept findById(String id) {
        Dept dept = deptMapper.findById(id);
        return dept;
    }

    @Override
    public PageInfo<Dept> findByPage(int pageNum, int pageSize) {
        //1.设置当前页码，页面大小
        PageHelper.startPage(pageNum,pageSize);

        //2.查询所有数据
        List<Dept> list = deptMapper.findAll();

        //3.封装PageInfo分页结果
        PageInfo<Dept> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }
}
