package com.lsl.web.system;

import com.github.pagehelper.PageInfo;
import com.lsl.service.dept.DeptService;
import com.lsl.system.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/dept")
public class deptController {
    @Autowired
    private DeptService deptService;
    
    @RequestMapping("/list")
    public String findByPage(HttpServletRequest request,
                             @RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "5") int pageSize){
        PageInfo<Dept> pageInfo = deptService.findByPage(pageNum,pageSize);
        List<Dept> list = pageInfo.getList();
//        request.setAttribute("list",list);
        request.setAttribute("pageInfo",pageInfo);
        return "system/dept/dept-list";
    }
    /**
     * 跳转到添加新企业
     * @param request
     * @return
     */
    @RequestMapping("/toAdd")
    public String addDept(HttpServletRequest request){
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList",all);
        return "system/dept/dept-add";
    }
    /**
     * 删除企业信息
     * @param request
     * @return
     */
    @RequestMapping("/delete")
    public String delDept(HttpServletRequest request,Dept Dept){
        List<Dept> companies = deptService.deleteById(Dept.getId());
        request.setAttribute("list",companies);
        return "redirect:/system/dept/list.do";
    }
    /**
     * 更新企业信息企业
     * @param request
     * @return
     */
    @RequestMapping("/edit")
    public String updateDept(HttpServletRequest request,Dept dept){
        System.out.println(dept);
        List<Dept> companies = deptService.edit(dept);
        request.setAttribute("list",companies);
        return "redirect:/system/dept/list.do";
    }
    /**
     * 跳转更新企业信息页面
     * @param request
     * @return
     */
    @RequestMapping("/toUpdate")
    public String updateDept(HttpServletRequest request,String id){
        Dept dept = deptService.findById(id);
        request.setAttribute("dept",dept);
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList",all);
        return "system/dept/dept-update";
    }
}
