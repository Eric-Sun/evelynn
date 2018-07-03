package com.j13.evelynn.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.j13.evelynn.core.AdminConstants;
import com.j13.evelynn.core.Constants;
import com.j13.evelynn.core.config.PropertiesConfiguration;
import com.j13.evelynn.net.AdminOrderServerManager;
import com.j13.evelynn.vo.OrderStatusVO;
import com.j13.evelynn.vo.OrderVO;
import com.j13.garen.api.resp.OrderAddResp;
import com.j13.poppy.core.CommonResultResp;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {
    private static int SIZE_PER_PAGE = 20;

    @Autowired
    AdminOrderServerManager adminOrderServerManager;

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


    @RequestMapping("download")
    public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException {

        String fileName = request.getParameter("img").replace(
                PropertiesConfiguration.getInstance().getStringValue(AdminConstants.IMG_URL_PREFIX)
                , "");
        String path = PropertiesConfiguration.getInstance().getStringValue(AdminConstants.LOCAL_IMG_DIR) + fileName;
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();
        String downloadFileName = request.getParameter("orderId") + ".jpg";
        headers.setContentDispositionFormData("attachment", downloadFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    @RequestMapping("/orderStatusList")
    @ResponseBody
    public String orderStatusList() {
        Map<Integer, String> orderStatusMap = Constants.orderStatusMap;
        List<Integer> orderStatusList = Constants.orderStatusList;

        List<OrderStatusVO> statusList = Lists.newLinkedList();
        for (Integer id : orderStatusList) {
            String content = orderStatusMap.get(id);
            OrderStatusVO vo = new OrderStatusVO(id, content);
            statusList.add(vo);
        }

        return JSON.toJSONString(statusList);
    }

    @RequestMapping("/orderListPG")
    public String orderListPG() {
        return "/admin/order/orderList";
    }

    @RequestMapping("/orderCreatePG")
    public String orderCreatePG() {
        return "/admin/order/orderCreate";
    }

    @RequestMapping("/orderCreate")
    @ResponseBody
    public String orderCreate(@RequestParam(value = "img", required = false) MultipartFile file,
                              HttpServletRequest request) throws IOException {
        String itemId = request.getParameter("itemId");
        float price = new Float(request.getParameter("finalPrice"));
        String remark = request.getParameter("remark");
        OrderAddResp resp = adminOrderServerManager.add(file, itemId, price, 0, remark);
        return JSON.toJSONString(resp);
    }

    @RequestMapping("/orderDelete")
    @ResponseBody
    public String orderDelete(HttpServletRequest request) {
        String orderNumber = request.getParameter("orderNumber");
        CommonResultResp resp = adminOrderServerManager.delete(orderNumber);
        return JSON.toJSONString(resp);
    }
}
