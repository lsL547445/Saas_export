package com.lsl.dao;

import com.lsl.company.Company;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompanyMapper {
    List<com.lsl.company.Company> findAll();

    void save(Company company);

    void add(Company company);

    void deleteById(String id);

    Company findById(String id);

    List<Company> findByPage(int pageNum, int pageSize);
}
