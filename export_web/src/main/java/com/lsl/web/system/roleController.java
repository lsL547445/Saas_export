package com.lsl.web.system;

import com.github.pagehelper.PageInfo;
import com.lsl.service.dept.DeptService;

import com.lsl.system.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lsl.service.role.RoleService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/system/role")
public class roleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private DeptService deptService;

    @RequestMapping("/list")
    public String findByPage(HttpServletRequest request,
                             @RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "5") int pageSize){
        PageInfo<Role> pageInfo = roleService.findByPage(pageNum,pageSize);
        List<Role> list = pageInfo.getList();
//        request.setAttribute("list",list);
        request.setAttribute("pageInfo",pageInfo);
        return "system/role/role-list";
    }
    /**
     * 跳转到添加新企业
     * @param request
     * @return
     */
    @RequestMapping("/toAdd")
    public String addRole(HttpServletRequest request,String id){
        return "system/role/role-add";
    }
    /**
     * 删除企业信息
     * @param request
     * @return
     */
    @RequestMapping("/delete")
    public String delRole(HttpServletRequest request,Role Role){
        List<Role> companies = roleService.deleteById(Role.getId());
        request.setAttribute("list",companies);
        return "redirect:/system/role/list.do";
    }
    /**
     * 更新企业信息企业
     * @param request
     * @return
     */
    @RequestMapping("/edit")
    public String updateRole(HttpServletRequest request,Role role){
        List<Role> companies = roleService.edit(role);
        request.setAttribute("list",companies);
        return "redirect:/system/role/list.do";
    }
    /**
     * 跳转更新企业信息页面
     * @param request
     * @return
     */
    @RequestMapping("/toUpdate")
    public String updateRole(HttpServletRequest request,String id){
        Role role = roleService.findById(id);
        request.setAttribute("module",role);
        return "system/role/role-update";
    }

}
