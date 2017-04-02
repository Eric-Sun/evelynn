package com.j13.evelynn.controller;

import com.j13.evelynn.security.model.Authority;
import com.j13.evelynn.security.model.Resource;
import com.j13.evelynn.security.model.User;
import com.j13.evelynn.security.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by sunbo on 14-11-16.
 */
@Controller
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;


    @RequestMapping("/userList")
    public String userList(HttpServletRequest request, Map<String, Object> model) {

        List<User> userList = authorityService.userList();
        model.put("data", userList);
        return "/authority/userList";
    }


    @RequestMapping("/userDetailForUpdate")
    public String userDetailForUpdate(HttpServletRequest request, Map<String, Object> model) {
        int id = new Integer(request.getParameter("id"));
        User user = authorityService.getUser(id);
        List<Authority> authorityList = authorityService.listAuthority();
        model.put("user", user);
        model.put("authorityList", authorityList);

        return "/authority/userDetailForUpdate";
    }

    @RequestMapping("/userPreCreate")
    public String preCreate(HttpServletRequest request, Map<String, Object> model) {
        List<Authority> authorityList = authorityService.listAuthority();
        model.put("authorityList", authorityList);
        return "/authority/userCreate";
    }

    @RequestMapping("/userCreate")
    public String userCreate(HttpServletRequest request, Map<String, Object> model) {
        String name = request.getParameter("name");
        int authorityId = new Integer(request.getParameter("authorityId"));

        authorityService.createUser(name, authorityId);
        return "redirect:/authority/userList";
    }


    @RequestMapping("/userUpdate")
    public String userUpdate(HttpServletRequest request, Map<String, Object> model) {
        int id = new Integer(request.getParameter("id"));
        String name = request.getParameter("name");
        int authorityId = new Integer(request.getParameter("authorityId"));

        authorityService.userUpdate(id, name, authorityId);
        return "redirect:/authority/userList";
    }

    @RequestMapping("/userDelete")
    public String userDelete(HttpServletRequest request, Map<String, Object> model) {
        int id = new Integer(request.getParameter("id"));
        authorityService.deleteUser(id);

        return "redirect:/authority/userList";
    }


    ////// Authority

    @RequestMapping("/authorityList")
    public String authorityList(HttpServletRequest request, Map<String, Object> model) {
        List<Authority> authorityList = authorityService.listAuthority();
        model.put("authorityList", authorityList);
        return "/authority/authorityList";
    }


    @RequestMapping("/authorityDetailForUpdate")
    public String authorityDetailForUpdate(HttpServletRequest request, Map<String, Object> model) {
        int id = new Integer(request.getParameter("id"));

        Authority authority = authorityService.getAuthority(id);
        List<Resource> resourceList = authorityService.getResouceList();
        model.put("authority", authority);
        model.put("resourceList", resourceList);
        return "/authority/authorityDetailForUpdate";
    }


    @RequestMapping("/authorityUpdate")
    public String authorityUpdate(HttpServletRequest request, Map<String, Object> model) {
        int id = new Integer(request.getParameter("id"));
        String name = request.getParameter("name");
        String[] resourceIdList = request.getParameterValues("resourceIdList");


        authorityService.updateAuthority(id, name, resourceIdList);
        return "redirect:/authority/authorityList";
    }


    @RequestMapping("/authorityDelete")
    public String authorityDelete(HttpServletRequest request) {
        int id = new Integer(request.getParameter("id"));
        authorityService.deleteAuthority(id);
        return "redirect:/authority/authorityList";
    }

    @RequestMapping("/authorityCreate")
    public String authorityCreate(HttpServletRequest request) {
        String name = request.getParameter("name");
        String[] resourceIdList = request.getParameterValues("resourceIdList");

        authorityService.createAuthority(name, resourceIdList);
        return "redirect:/authority/authorityList";
    }


    @RequestMapping("/authorityPreCreate")
    public String authorityPreCreate(HttpServletRequest request, Map<String, Object> model) {


        List<Resource> resourceList = authorityService.getResouceList();
        model.put("resourceList", resourceList);

        return "/authority/authorityCreate";
    }


    /// resource

    @RequestMapping("/resourceList")
    public String resourceList(HttpServletRequest request, Map<String, Object> model) {

        List<Resource> resourceList = authorityService.getResouceList();
        model.put("resourceList", resourceList);
        return "/authority/resourceList";
    }


    @RequestMapping("/resourceCreate")
    public String resourceCreate(HttpServletRequest request) {
        String name = request.getParameter("name");
        authorityService.createResource(name);
        return "redirect:/authority/resourceList";
    }


    @RequestMapping("/resourceUpdate")
    public String resourceUpdate(HttpServletRequest request) {
        String name = request.getParameter("name");
        int id = new Integer(request.getParameter("id"));
        authorityService.updateResource(id, name);
        return "redirect:/authority/resourceList";
    }


    @RequestMapping("/resourceDelete")
    public String resourceDelete(HttpServletRequest request) {
        int id = new Integer(request.getParameter("id"));
        authorityService.deleteResource(id);
        return "redirect:/authority/resourceList";
    }


    @RequestMapping("/resourceDetailForUpdate")
    public String resourceDetailForUpdate(HttpServletRequest request, Map<String, Object> model) {
        int id = new Integer(request.getParameter("id"));

        Resource resource = authorityService.getResource(id);
        model.put("resource", resource);
        return "/authority/resourceDetailForUpdate";
    }

    @RequestMapping("/resourcePreCreate")
    public String resourcePreCreate(HttpServletRequest request) {
        return "/authority/resourceCreate";
    }


}
