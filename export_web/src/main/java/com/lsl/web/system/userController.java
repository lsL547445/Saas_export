package com.lsl.web.system;

import com.github.pagehelper.PageInfo;
import com.lsl.service.dept.DeptService;
import com.lsl.service.role.RoleService;
import com.lsl.service.user.UserService;
import com.lsl.system.Dept;
import com.lsl.system.Role;
import com.lsl.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/user")
public class userController {
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public String findByPage(HttpServletRequest request,
                             @RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "5") int pageSize){
        PageInfo<User> pageInfo = userService.findByPage(pageNum,pageSize);
        List<User> list = pageInfo.getList();
//        request.setAttribute("list",list);
        request.setAttribute("pageInfo",pageInfo);
        return "system/user/user-list";
    }
    /**
     * 跳转到添加新企业
     * @param request
     * @return
     */
    @RequestMapping("/toAdd")
    public String addUser(HttpServletRequest request,String id){
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList",all);
        return "system/user/user-add";
    }
    /**
     * 删除企业信息
     * @param request
     * @return
     */
    @RequestMapping("/delete")
    public String delUser(HttpServletRequest request,User User){
        List<User> companies = userService.deleteById(User.getId());
        request.setAttribute("list",companies);
        return "redirect:/system/user/list.do";
    }
    /**
     * 更新企业信息企业
     * @param request
     * @return
     */
    @RequestMapping("/edit")
    public String updateUser(HttpServletRequest request,User user){
        System.out.println(user);
        List<User> companies = userService.edit(user);
        request.setAttribute("list",companies);
        return "redirect:/system/user/list.do";
    }
    /**
     * 跳转更新企业信息页面
     * @param request
     * @return
     */
    @RequestMapping("/toUpdate")
    public String updateUser(HttpServletRequest request,String id){
        User user = userService.findById(id);
        request.setAttribute("user",user);
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList",all);
        return "system/user/user-update";
    }
    @RequestMapping("/roleList.do")
    public String roleList(HttpServletRequest request,String id ){
        //查询用户信息
        User user = userService.findById(id);
        request.setAttribute("user",user);
        //根据用户id查询对应的的角色
        List<Role> list = roleService.findRoleByUserId(id);
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s+=list.get(i).getId();
        }
        request.setAttribute("userRoleStr",s);
        //查询所有角色信息
        List<Role> all = roleService.findAll();
        request.setAttribute("roleList",all);
        return "system/user/user-role";
    }

    @RequestMapping("/changeRole.do")
    public String changeRole(HttpServletRequest request,String id ){
        //查询用户信息
        User user = userService.findById(id);
        request.setAttribute("user",user);
        //根据用户id查询对应的的角色
        List<Role> list = roleService.findRoleByUserId(id);
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s+=list.get(i).getId();
        }
        request.setAttribute("userRoleStr",s);
        //查询所有角色信息
        List<Role> all = roleService.findAll();
        request.setAttribute("roleList",all);
        return "system/user/user-role";
    }
}
