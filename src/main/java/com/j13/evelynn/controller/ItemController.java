package com.j13.evelynn.controller;

import com.alibaba.fastjson.JSON;
import com.j13.evelynn.net.ItemServerManager;
import com.j13.evelynn.security.model.Account;
import com.j13.evelynn.security.model.Authority;
import com.j13.evelynn.util.MD5Util;
import com.j13.evelynn.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemServerManager itemServerManager;

    @RequestMapping("/itemUpdate")
    public String itemUpdate(HttpServletRequest request, Map<String, Object> model) {
        int id = new Integer(request.getParameter("id"));
        String name = request.getParameter("name");
        float price = new Float(request.getParameter("price"));
        itemServerManager.update(id, name, price);
        return "redirect:/item/itemList";
    }

    @RequestMapping("/itemList")
    public String itemList(HttpServletRequest request, Map<String, Object> model) {
        List<ItemVO> itemList = itemServerManager.list();
        model.put("itemList", itemList);
        return "/item/itemList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list() {
        List<ItemVO> itemList = itemServerManager.list();
        return JSON.toJSONString(itemList);
    }


    @RequestMapping("/itemDetailForUpdate")
    public String itemDetailForUpdate(HttpServletRequest request, Map<String, Object> model) {
        int id = new Integer(request.getParameter("id"));
        ItemVO item = itemServerManager.get(id);
        model.put("item", item);

        return "/item/itemDetailForUpdate";
    }

    @RequestMapping("/itemPreCreate")
    public String itemPreCreate(HttpServletRequest request, Map<String, Object> model) {
        return "/item/itemCreate";
    }


    @RequestMapping("/itemCreate")
    public String itemCreate(HttpServletRequest request, Map<String, Object> model) {
        String name = request.getParameter("name");
        float price = new Float(request.getParameter("price"));
        itemServerManager.add(name, price);
        return "redirect:/item/itemList";
    }


    @RequestMapping("/itemDelete")
    public String itemDelete(HttpServletRequest request, Map<String, Object> model) {
        int id = new Integer(request.getParameter("id"));
        itemServerManager.delete(id);
        return "redirect:/item/itemList";
    }


}
