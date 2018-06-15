package com.j13.evelynn.controller;

import com.j13.evelynn.net.AccountServerManager;
import com.j13.evelynn.security.model.Authority;
import com.j13.evelynn.security.model.Resource;
import com.j13.evelynn.security.model.Account;
import com.j13.evelynn.util.MD5Util;
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
@RequestMapping("/admin/authority")
public class AdminAuthorityController {

    @Autowired
    AccountServerManager accountServerManager;


    @RequestMapping("/accountList")
    public String userList(HttpServletRequest request, Map<String, Object> model) {

        List<Account> accountList = accountServerManager.accountList();
        model.put("data", accountList);
        return "/admin/authority/accountList";
    }


    @RequestMapping("/accountDetailForUpdate")
    public String userDetailForUpdate(HttpServletRequest request, Map<String, Object> model) {
        int id = new Integer(request.getParameter("id"));
        Account account = accountServerManager.getAccount(id);
        List<Authority> authorityList = accountServerManager.listAuthority();
        model.put("account", account);
        model.put("authorityList", authorityList);

        return "/authority/accountDetailForUpdate";
    }

    @RequestMapping("/accountPreCreate")
    public String preCreate(HttpServletRequest request, Map<String, Object> model) {
        List<Authority> authorityList = accountServerManager.listAuthority();
        model.put("authorityList", authorityList);
        return "/authority/accountCreate";
    }

    @RequestMapping("/accountCreate")
    public String userCreate(HttpServletRequest request, Map<String, Object> model) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        int authorityId = new Integer(request.getParameter("authorityId"));
        String brief = request.getParameter("brief");
        String mobile = request.getParameter("mobile");
        String realName = request.getParameter("realName");

        String passwordAfterMd5 = MD5Util.getMD5String(password);
        accountServerManager.createAccount(name, passwordAfterMd5, authorityId, brief, mobile, realName);

        return "redirect:/authority/accountList";
    }


    @RequestMapping("/accountUpdate")
    public String userUpdate(HttpServletRequest request, Map<String, Object> model) {
        int id = new Integer(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        int authorityId = new Integer(request.getParameter("authorityId"));
        String brief = request.getParameter("brief");
        String mobile = request.getParameter("mobile");
        String realName = request.getParameter("realName");
        accountServerManager.updateAccount(id, name, password, authorityId, brief, mobile, realName);
        return "redirect:/authority/accountList";
    }

    @RequestMapping("/accountDelete")
    public String userDelete(HttpServletRequest request, Map<String, Object> model) {
        int id = new Integer(request.getParameter("id"));
        accountServerManager.deleteAccount(id);

        return "redirect:/authority/accountList";
    }


    ////// Authority

    @RequestMapping("/authorityList")
    public String authorityList(HttpServletRequest request, Map<String, Object> model) {
        List<Authority> authorityList = accountServerManager.listAuthority();
        model.put("authorityList", authorityList);
        return "/authority/authorityList";
    }


    @RequestMapping("/authorityDetailForUpdate")
    public String authorityDetailForUpdate(HttpServletRequest request, Map<String, Object> model) {
        int id = new Integer(request.getParameter("id"));

        Authority authority = accountServerManager.getAuthority(id);
        List<Resource> resourceList = accountServerManager.getResourceList();
        model.put("authority", authority);
        model.put("resourceList", resourceList);
        return "/authority/authorityDetailForUpdate";
    }


    @RequestMapping("/authorityUpdate")
    public String authorityUpdate(HttpServletRequest request, Map<String, Object> model) {
        int id = new Integer(request.getParameter("id"));
        String name = request.getParameter("name");
        String[] resourceIdList = request.getParameterValues("resourceIdList");


        accountServerManager.updateAuthority(id, name, resourceIdList);
        return "redirect:/authority/authorityList";
    }


    @RequestMapping("/authorityDelete")
    public String authorityDelete(HttpServletRequest request) {
        int id = new Integer(request.getParameter("id"));
        accountServerManager.deleteAuthority(id);
        return "redirect:/authority/authorityList";
    }

    @RequestMapping("/authorityCreate")
    public String authorityCreate(HttpServletRequest request) {
        String name = request.getParameter("name");
        String[] resourceIdList = request.getParameterValues("resourceIdList");

        accountServerManager.createAuthority(name, resourceIdList);
        return "redirect:/authority/authorityList";
    }


    @RequestMapping("/authorityPreCreate")
    public String authorityPreCreate(HttpServletRequest request, Map<String, Object> model) {


        List<Resource> resourceList = accountServerManager.getResourceList();
        model.put("resourceList", resourceList);

        return "/authority/authorityCreate";
    }


    /// resource

    @RequestMapping("/resourceList")
    public String resourceList(HttpServletRequest request, Map<String, Object> model) {

        List<Resource> resourceList = accountServerManager.getResourceList();
        model.put("resourceList", resourceList);
        return "/authority/resourceList";
    }


    @RequestMapping("/resourceCreate")
    public String resourceCreate(HttpServletRequest request) {
        String name = request.getParameter("name");
        accountServerManager.createResource(name);
        return "redirect:/authority/resourceList";
    }


    @RequestMapping("/resourceUpdate")
    public String resourceUpdate(HttpServletRequest request) {
        String name = request.getParameter("name");
        int id = new Integer(request.getParameter("id"));
        accountServerManager.updateResource(id, name);
        return "redirect:/authority/resourceList";
    }


    @RequestMapping("/resourceDelete")
    public String resourceDelete(HttpServletRequest request) {
        int id = new Integer(request.getParameter("id"));
        accountServerManager.deleteResource(id);
        return "redirect:/authority/resourceList";
    }


    @RequestMapping("/resourceDetailForUpdate")
    public String resourceDetailForUpdate(HttpServletRequest request, Map<String, Object> model) {
        int id = new Integer(request.getParameter("id"));

        Resource resource = accountServerManager.getResource(id);
        model.put("resource", resource);
        return "/authority/resourceDetailForUpdate";
    }

    @RequestMapping("/resourcePreCreate")
    public String resourcePreCreate(HttpServletRequest request) {
        return "/authority/resourceCreate";
    }


}
