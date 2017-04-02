package com.j13.evelynn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-10-6
 * Time: 下午4:42
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/index")
public class IndexController {


    @RequestMapping("")
    public String index(HttpServletRequest request, Map<String, Object> models) {
        return "/index";
    }




}
