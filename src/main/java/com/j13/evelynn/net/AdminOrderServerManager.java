package com.j13.evelynn.net;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.j13.evelynn.core.ConfigurationConstants;
import com.j13.evelynn.core.Constants;
import com.j13.evelynn.core.config.PropertiesConfiguration;
import com.j13.evelynn.util.ImgUtil;
import com.j13.evelynn.util.InternetUtil;
import com.j13.evelynn.vo.OrderVO;
import com.j13.garen.facade.req.OrderDeleteReq;
import com.j13.garen.facade.req.OrderUpdateStatusReq;
import com.j13.garen.facade.resp.OrderAddResp;
import com.j13.garen.facade.resp.OrderGetResp;
import com.j13.garen.facade.resp.OrderListResp;
import com.j13.garen.poppy.core.CommonResultResp;
import com.j13.garen.poppy.util.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AdminOrderServerManager extends BaseServerManager {

    private static int ALL_STATUS = -1;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public List<OrderVO> list(int pageNum, int sizePerPage, int status) {
        String rawResponse = InternetUtil.post(
                getServerUrl(),
                RequestParams.getInstance()
                        .add("act", "order.list")
                        .add("status", status)
                        .add("pageNum", pageNum).add("sizePerPage", sizePerPage));
        OrderListResp resp = JSON.parseObject(rawResponse, OrderListResp.class);

        Map<Integer, String> orderStatusMap = Constants.orderStatusMap;
        List<OrderVO> orderList = Lists.newLinkedList();
        for (OrderGetResp r : resp.getList()) {
            OrderVO o = new OrderVO();
            BeanUtils.copyProperties(o, r);
            o.setImg(ImgUtil.getImgFullUrl(o.getImg()));
            o.setCreatetime(sdf.format(new Date(r.getCreatetime())));
            o.setStatusString(orderStatusMap.get(o.getStatus()));
            orderList.add(o);
        }
        return orderList;
    }

    public void add(MultipartFile file, String contactMobile, String itemId, float price, int userId) throws IOException {
        // 保存到本地，做tmp
        String fileName = System.currentTimeMillis() + ".jpg";
        File destFile = new File(PropertiesConfiguration.getInstance().getStringValue(ConfigurationConstants.LOCAL_TMP_DIR), fileName);
        file.transferTo(destFile);

        String rawResponse = InternetUtil.post(
                getServerUrl(),
                RequestParams.getInstance()
                        .add("act", "order.add")
                        .add("contactMobile", contactMobile)
                        .add("itemId", itemId)
                        .add("finalPrice", price)
                        .add("userId", userId), "img", destFile);
        OrderAddResp resp = JSON.parseObject(rawResponse, OrderAddResp.class);
    }

    public void updateStatus(int id, int status) {
        String rawResponse = InternetUtil.post(
                getServerUrl(),
                RequestParams.getInstance()
                        .add("act", "order.updateStatus")
                        .add("orderId", id)
                        .add("status", status));
        OrderUpdateStatusReq resp = JSON.parseObject(rawResponse, OrderUpdateStatusReq.class);
    }

    public void delete(int id) {
        String rawResponse = InternetUtil.post(
                getServerUrl(),
                RequestParams.getInstance()
                        .add("act", "order.delete")
                        .add("orderId", id));
        CommonResultResp resp = JSON.parseObject(rawResponse, CommonResultResp.class);
    }
}