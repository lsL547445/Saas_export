package com.lsl.service.company;

import com.github.pagehelper.PageInfo;
import com.lsl.company.Company;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CompanyService {
    List<Company>  findAll();

    List<Company> edit(Company company);

    List<Company> deleteById(String id);

    Company findById(String id);

    PageInfo<Company> findByPage(int pageNum, int pageSize);
}
