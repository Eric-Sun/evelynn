package com.j13.evelynn.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.j13.evelynn.core.AdminConstants;
import com.j13.evelynn.core.AdminException;
import com.j13.evelynn.core.config.PropertiesConfiguration;
import com.j13.evelynn.net.DZResponse;
import com.j13.evelynn.net.ListDZByDateResp;
import com.j13.evelynn.net.SizeDZByDateResp;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JaxManager {


    private String getBaseUrl() {
        String url = PropertiesConfiguration.getInstance().getStringValue(AdminConstants.JAX_SERVER_URL_KEY);
        return url;
    }


    public List<DZResponse> listDZ(String date, int pageNum, int size) throws AdminException {
        Map<String, String> params = Maps.newHashMap();
        params.put("act", "dz.listDZByDate");
        params.put("date", date);
        params.put("pageNum", pageNum + "");
        params.put("size", size + "");
        String url = getBaseUrl();
        String rawResponse = InternetUtil.post(url, params);
        ListDZByDateResp resp = JSON.parseObject(rawResponse, ListDZByDateResp.class);
        return resp.getData();
    }


    public int sizeDZ(String date) throws AdminException {
        Map<String, String> params = Maps.newHashMap();
        params.put("act", "dz.sizeDZByDate");
        params.put("date", date);

        String url = getBaseUrl();
        String rawResponse = InternetUtil.post(url, params);
        SizeDZByDateResp resp = JSON.parseObject(rawResponse, SizeDZByDateResp.class);
        return resp.getSize();
    }
}
