package com.lsl.dao;

import com.lsl.system.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptMapper {
    List<Dept> findAll();

    void save(Dept dept);

    void add(Dept dept);

    void deleteById(String id);

    Dept findById(String id);

    List<Dept> findByPage(int pageNum, int pageSize);
}
