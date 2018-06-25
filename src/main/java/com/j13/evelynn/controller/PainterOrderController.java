package com.j13.evelynn.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.j13.evelynn.net.AdminOrderServerManager;
import com.j13.evelynn.vo.OrderVO;
import com.j13.garen.api.resp.AdminPainterOrderGetResp;
import com.j13.poppy.core.CommonResultResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
        return "/painter/order/orderList";
    }

    @RequestMapping("/recordCreatePG")
    public String recordCreate() {
        return "/painter/order/recordCreate";
    }


    @RequestMapping("/orderDetailPG")
    public ModelAndView orderDetailPG(HttpServletRequest request, @RequestParam("orderNumber") String orderNumber) {
        ModelAndView mav = new ModelAndView("/painter/order/orderDetail");
        mav.addObject("orderNumber", orderNumber);
        return mav;
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


    @RequestMapping("/orderDetail")
    @ResponseBody
    public String orderDetail(HttpServletRequest request) {
        Map<String, Object> model = Maps.newHashMap();
        String orderNumber = request.getParameter("orderNumber");
        AdminPainterOrderGetResp orderVO = adminOrderServerManager.get(orderNumber);
        model.put("order", orderVO);
        return JSON.toJSONString(model);
    }


    @RequestMapping("/recordCreate")
    @ResponseBody
    public String recordCreate(@RequestParam(value = "img", required = false) MultipartFile file,
                               HttpServletRequest request) throws IOException {
        String remark = request.getParameter("remark");

        adminOrderServerManager.addRecord(file, 1, "444", 2, remark);
        Map<String, Object> model = Maps.newHashMap();
        model.put("a", "a");
        System.out.println("fafafs");
        return JSON.toJSONString(model);

    }


    @RequestMapping("/updateStatus")
    @ResponseBody
    public String updateStatus(@RequestParam(value = "orderNumber", required = false) String orderNumber,
                               @RequestParam(value = "status") int status) throws IOException {
        CommonResultResp resp = adminOrderServerManager.updateStatus(orderNumber, status);
        return JSON.toJSONString(resp);

    }
}
