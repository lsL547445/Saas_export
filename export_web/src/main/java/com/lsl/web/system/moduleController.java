package com.lsl.web.system;

import com.github.pagehelper.PageInfo;
import com.lsl.service.dept.DeptService;
import com.lsl.service.module.ModuleService;
import com.lsl.service.role.RoleService;
import com.lsl.system.Module;
import com.lsl.system.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/module")
public class moduleController {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public String findByPage(HttpServletRequest request,
                             @RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "5") int pageSize){
        PageInfo<Module> pageInfo = moduleService.findByPage(pageNum,pageSize);
        List<Module> list = pageInfo.getList();
//        request.setAttribute("list",list);
        request.setAttribute("pageInfo",pageInfo);
        return "system/module/module-list";
    }
    /**
     * 跳转到添加新企业
     * @param request
     * @return
     */
    @RequestMapping("/toAdd")
    public String addModule(HttpServletRequest request,String id){
        return "system/module/module-add";
    }
    /**
     * 删除企业信息
     * @param request
     * @return
     */
    @RequestMapping("/delete")
    public String delModule(HttpServletRequest request,Module Module){
        List<Module> companies = moduleService.deleteById(Module.getId());
        request.setAttribute("list",companies);
        return "redirect:/system/module/list.do";
    }
    /**
     * 更新企业信息企业
     * @param request
     * @return
     */
    @RequestMapping("/edit")
    public String updateModule(HttpServletRequest request,Module module){
        List<Module> companies = moduleService.edit(module);
        request.setAttribute("list",companies);
        return "redirect:/system/module/list.do";
    }
    /**
     * 跳转更新企业信息页面
     * @param request
     * @return
     */
    @RequestMapping("/toUpdate")
    public String updateModule(HttpServletRequest request,String id){
        Module module = moduleService.findById(id);
        request.setAttribute("module",module);
        return "system/module/module-update";
    }
    /**
     * 跳转到权限分配模块
     * @param request
     * @return
     */
    @RequestMapping("/roleModule")
    public String moduleMoudel(HttpServletRequest request,String roleid){
        System.out.println(roleid);
        Role role = roleService.findById(roleid);
        request.setAttribute("role",role);
        //查询该角色拥有的权限
        List<Module> modules = moduleService.findModulesByRoleId(roleid);
        List<Map> list = new ArrayList<>();
        //4.封装List集合
        if(modules!=null && modules.size()>0){

            //4.1 遍历所有模块
            for(Module mod:modules){
                //{ id:12, pId:1, name:"出口报运", open:true, checked: true }
                Map<String,Object> map = new HashMap<>();
                map.put("id",mod.getId());
                map.put("pId",mod.getParentId());
                map.put("name",mod.getName());
                map.put("open",true);//全部展示

                //让哪些该角色绑定过的模块  勾上（加上checked=true属性）
                //contains: 判断某个List集合中是否包含某个对象（判断对象的所有属性值内容是否一致）
//                if(moduleModules.contains(module)){
////                    map.put("checked",true);
////                }

              /* for(Module m2:moduleModules){
                   if(m2.getId()==module.getId()){
                       map.put("checked",true);
                   }
               }*/

                //把Map放入List集合中
                list.add(map);
            }

        }
        System.out.println(list);
        request.setAttribute("modules",list);
            return "system/role/role-module";
    }

    /**
     * 跳转到权限分配模块
     * @param request
     * @return
     */
    @RequestMapping("/getZtreeNodes")
    @ResponseBody
    public List<Map> getZtreeNodes(HttpServletRequest request, String roleid){
        //查询该角色拥有的权限
        List<Module> modules = moduleService.findModulesByRoleId(roleid);
        List<Map> list = new ArrayList<>();
        //查询所有模块
        List<Module> moduleList = moduleService.findAll();
        //4.封装List集合
        if(modules!=null && modules.size()>0){

            //4.1 遍历所有模块
            for(Module mod:moduleList){
                //{ id:12, pId:1, name:"出口报运", open:true, checked: true }
                Map<String,Object> map = new HashMap<>();
                map.put("id",mod.getId());
                map.put("pId",mod.getParentId());
                map.put("name",mod.getName());
                map.put("open",true);//全部展示

                //让哪些该角色绑定过的模块  勾上（加上checked=true属性）
                //contains: 判断某个List集合中是否包含某个对象（判断对象的所有属性值内容是否一致）
                if(modules.contains(mod)){
                    map.put("checked",true);
                }

              /* for(Module m2:moduleModules){
                   if(m2.getId()==module.getId()){
                       map.put("checked",true);
                   }
               }*/

                //把Map放入List集合中
                list.add(map);
            }

        }
        return list;
    }
}
