package com.j13.evelynn.net;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.j13.evelynn.core.ConfigurationConstants;
import com.j13.evelynn.core.Constants;
import com.j13.evelynn.core.config.PropertiesConfiguration;
import com.j13.evelynn.util.ImgUtil;
import com.j13.evelynn.util.InternetUtil;
import com.j13.evelynn.vo.OrderVO;
import com.j13.garen.api.req.OrderUpdateStatusReq;
import com.j13.garen.api.resp.AdminPainterOrderGetResp;
import com.j13.garen.api.resp.AdminPainterOrderListResp;
import com.j13.poppy.util.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PainterOrderServerManager extends BaseServerManager {

    private static int ALL_STATUS = -1;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public List<OrderVO> list(int pageNum, int sizePerPage, int status) {
        String rawResponse = InternetUtil.post(
                getServerUrl(),
                RequestParams.getInstance()
                        .add("act", "page.painter.order.list")
                        .add("status", status)
                        .add("pageNum", pageNum).add("sizePerPage", sizePerPage));
        AdminPainterOrderListResp resp = JSON.parseObject(rawResponse, AdminPainterOrderListResp.class);

        Map<Integer, String> orderStatusMap = Constants.orderStatusMap;
        List<OrderVO> orderList = Lists.newLinkedList();
        for (AdminPainterOrderGetResp r : resp.getList()) {
            OrderVO o = new OrderVO();
            BeanUtils.copyProperties(o, r);
            o.setImg(ImgUtil.getImgFullUrl(o.getImg()));
            o.setCreatetime(sdf.format(new Date(r.getCreatetime())));
            o.setStatusString(orderStatusMap.get(o.getStatus()));
            orderList.add(o);
        }
        return orderList;
    }

    /**
     * 画家修改状态
     * @param id
     * @param status
     */
    public void updateStatus(int id, int status) {
        String rawResponse = InternetUtil.post(
                getServerUrl(),
                RequestParams.getInstance()
                        .add("act", "page.painter.order.updateStatus")
                        .add("orderId", id)
                        .add("status", status));
        OrderUpdateStatusReq resp = JSON.parseObject(rawResponse, OrderUpdateStatusReq.class);
    }


}
