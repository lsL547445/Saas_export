package com.lsl.service.company.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsl.company.Company;
import com.lsl.dao.CompanyMapper;
import com.lsl.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;



    @Override
    public List<Company> findAll() {
        List<Company> companies = companyMapper.findAll();
        return companies;
    }

    @Override
    public List<Company> edit(Company company) {
        if(company.getId().equals("")){
            UUID uuid = UUID.randomUUID();
            company.setId(uuid.toString());
            companyMapper.add(company);
        }else{
            companyMapper.save(company);
        }
        return findAll();
    }

    @Override
    public List<Company> deleteById(String id) {
        companyMapper.deleteById(id);
        return findAll();
    }

    @Override
    public Company findById(String id) {
        Company company = companyMapper.findById(id);
        return company;
    }

    @Override
    public PageInfo<Company> findByPage(int pageNum, int pageSize) {
        //1.设置当前页码，页面大小
        PageHelper.startPage(pageNum,pageSize);

        //2.查询所有数据
        List<Company> list = companyMapper.findAll();

        //3.封装PageInfo分页结果
        PageInfo<Company> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }
}
