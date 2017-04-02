package com.j13.evelynn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-10-6
 * Time: 下午6:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("")
public class UserController {


    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        return "login";
    }

}
