package com.j13.evelynn.util;

import com.google.common.collect.Lists;
import com.j13.evelynn.core.AdminException;
import com.j13.evelynn.net.RequestParams;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InternetUtil {

    public static String get(String url) throws AdminException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.86 Safari/537.36");
            httpGet.addHeader("Cookie", "uuid=\"w:f6bdcea4338c47ac89e503ae0153c988\"; __utma=101886750.1946652733.1464254094.1467133998.1467133998.1; __utmz=101886750.1467133998.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); tt_webid=16873306519; _gat=1; csrftoken=4d661dc9bbe498098e40beb48cb184f8; Hm_lvt_773f1a5aa45c642cf87eef671e4d3f6a=1467714269,1468071983,1468493730,1468772301; Hm_lpvt_773f1a5aa45c642cf87eef671e4d3f6a=1468772487; _ga=GA1.2.1946652733.1464254094");
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String rawResponse = EntityUtils.toString(entity);
            return rawResponse;
        } catch (IOException e) {
            throw new AdminException("http error. Url=" + url, e);
        } finally {

            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    throw new AdminException("http error. Url=" + url, e);
                }

            }
            try {
                httpclient.close();
            } catch (IOException e) {
                throw new AdminException("http error. Url=" + url, e);
            }
        }
    }

    public static String post(String url, RequestParams p) {
        return InternetUtil.post(url, p.end());
    }


    public static String post(String url, Map<String, Object> params) throws AdminException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = Lists.newLinkedList();

            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                if (params.get(key) != null)
                    nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
            }


            HttpEntity httpEntity = new UrlEncodedFormEntity(nvps, Charset.forName("UTF-8"));

            httpPost.setEntity(httpEntity);

            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String rawResponse = EntityUtils.toString(entity);
            return rawResponse;
        } catch (IOException e) {
            throw new AdminException("http error. Url=" + url, e);
        } finally {

            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    throw new AdminException("http error. Url=" + url, e);
                }

            }
            try {
                httpclient.close();
            } catch (IOException e) {
                throw new AdminException("http error. Url=" + url, e);
            }
        }
    }

    public static String post(String url, RequestParams p, String fileKey, File file) {
        return InternetUtil.post(url, p.end(), fileKey, file);
    }

    public static String post(String url, Map<String, Object> params, String fileKey, File file) throws AdminException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            FileBody fb = new FileBody(file);

            MultipartEntity reqEntity = new MultipartEntity();
            reqEntity.addPart(fileKey, fb);
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                if (params.get(key) != null)
                    reqEntity.addPart(key, new StringBody(params.get(key).toString()));
            }

            httpPost.setEntity(reqEntity);

            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String rawResponse = EntityUtils.toString(entity);
            return rawResponse;
        } catch (IOException e) {
            throw new AdminException("http error. Url=" + url, e);
        } finally {

            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    throw new AdminException("http error. Url=" + url, e);
                }

            }
            try {
                httpclient.close();
            } catch (IOException e) {
                throw new AdminException("http error. Url=" + url, e);
            }
        }
    }


}
