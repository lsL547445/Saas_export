package com.lsl.service.dept;

import com.github.pagehelper.PageInfo;
import com.lsl.system.Dept;

import java.util.List;


public interface DeptService {
    List<Dept>  findAll();

    List<Dept> edit(Dept dept);

    List<Dept> deleteById(String id);

    Dept findById(String id);

    PageInfo<Dept> findByPage(int pageNum, int pageSize);
}
