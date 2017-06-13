package com.j13.evelynn.net;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.j13.evelynn.security.model.Account;
import com.j13.evelynn.util.InternetUtil;
import com.j13.evelynn.vo.ItemVO;
import com.j13.garen.facade.req.ItemListReq;
import com.j13.garen.facade.resp.AccountGetResp;
import com.j13.garen.facade.resp.ItemAddResp;
import com.j13.garen.facade.resp.ItemGetResp;
import com.j13.garen.facade.resp.ItemListResp;
import com.j13.garen.poppy.core.CommonResultResp;
import com.j13.garen.poppy.util.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemServerManager extends BaseServerManager {


    public List<ItemVO> list() {

        String rawResponse = InternetUtil.post(
                getServerUrl(),
                RequestParams.getInstance()
                        .add("act", "item.list")
                        .add("pageNum", 0)
                        .add("sizePerPage", 100));
        ItemListResp resp = JSON.parseObject(rawResponse, ItemListResp.class);
        List<ItemVO> list = Lists.newLinkedList();
        for (ItemGetResp r : resp.getList()) {
            ItemVO v = new ItemVO();
            BeanUtils.copyProperties(v, r);
            list.add(v);
        }
        return list;
    }


    public ItemVO get(int id) {
        String rawResponse = InternetUtil.post(
                getServerUrl(),
                RequestParams.getInstance()
                        .add("act", "item.get")
                        .add("itemId", id));
        ItemGetResp resp = JSON.parseObject(rawResponse, ItemGetResp.class);
        ItemVO vo = new ItemVO();
        BeanUtils.copyProperties(vo, resp);
        return vo;
    }


    public void update(int id, String name, float price) {
        String rawResponse = InternetUtil.post(
                getServerUrl(),
                RequestParams.getInstance()
                        .add("act", "item.update")
                        .add("itemId", id)
                        .add("name", name)
                        .add("price", price));
        CommonResultResp resp = JSON.parseObject(rawResponse, CommonResultResp.class);
    }


    public void delete(int id) {
        String rawResponse = InternetUtil.post(
                getServerUrl(),
                RequestParams.getInstance()
                        .add("act", "item.delete")
                        .add("itemId", id));
        CommonResultResp resp = JSON.parseObject(rawResponse, CommonResultResp.class);
    }


    public int add(String name, float price) {
        String rawResponse = InternetUtil.post(
                getServerUrl(),
                RequestParams.getInstance()
                        .add("act", "item.add")
                        .add("name", name)
                        .add("price", price));
        ItemAddResp resp = JSON.parseObject(rawResponse, ItemAddResp.class);
        return resp.getItemId();
    }

}
