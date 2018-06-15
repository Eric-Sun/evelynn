package com.j13.evelynn.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.j13.evelynn.net.AdminOrderServerManager;
import com.j13.evelynn.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/painter/order")
public class PainterOrderController {

    private static int SIZE_PER_PAGE = 20;

    @Autowired
    AdminOrderServerManager adminOrderServerManager;


    @RequestMapping("/orderListPG")
    public String orderListPG() {
        return "/admin/order/orderList";
    }


    @RequestMapping("/orderList")
    @ResponseBody
    public String orderList(HttpServletRequest request) {
        Map<String, Object> model = Maps.newHashMap();
        int pageNum = 0;
        if (request.getParameter("pageNum") != null)
            pageNum = new Integer(request.getParameter("pageNum"));
        int status = -1;
        if (request.getParameter("status") != null) {
            status = new Integer(request.getParameter("status"));
        }
        List<OrderVO> orderList = adminOrderServerManager.list(pageNum, SIZE_PER_PAGE, status);

        model.put("orderList", orderList);
        return JSON.toJSONString(model);
    }

}
