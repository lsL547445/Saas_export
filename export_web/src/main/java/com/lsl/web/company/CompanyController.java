package com.lsl.web.company;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageInfo;
import com.lsl.company.Company;
import com.lsl.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

//    /**
//     * 获取企业列表
//     * @param request
//     * @return
//     */
//    @RequestMapping("/list")
//    public String findAll(HttpServletRequest request){
//        List<Company> companies = companyService.findAll();
//        request.setAttribute("list",companies);
//        return "company/company-list";
//    }
    @RequestMapping("/list")
    public String findByPage(HttpServletRequest request,
                             @RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "5") int pageSize){
        PageInfo<Company> pageInfo = companyService.findByPage(pageNum,pageSize);
        List<Company> list = pageInfo.getList();
        request.setAttribute("list",list);
        request.setAttribute("pageInfo",pageInfo);
        return "company/company-list";
    }
    /**
     * 跳转到添加新企业
     * @param request
     * @return
     */
    @RequestMapping("/toAdd")
    public String addCompany(HttpServletRequest request){

        return "company/company-add";
    }
    /**
     * 删除企业信息
     * @param request
     * @return
     */
    @RequestMapping("/delete")
    public String delCompany(HttpServletRequest request,Company company){
        List<Company> companies = companyService.deleteById(company.getId());
        request.setAttribute("list",companies);
        return "redirect:/company/list.do";
    }
    /**
     * 更新企业信息企业
     * @param request
     * @return
     */
    @RequestMapping("/edit")
    public String updateCompany(HttpServletRequest request,Company company){
        List<Company> companies = companyService.edit(company);
        request.setAttribute("list",companies);
        return "redirect:/company/list.do";
    }
    /**
     * 跳转更新企业信息页面
     * @param request
     * @return
     */
    @RequestMapping("/toUpdate")
    public String updateCompany(HttpServletRequest request,String id){
        Company company = companyService.findById(id);
        request.setAttribute("company",company);
        return "company/company-update";
    }
}
