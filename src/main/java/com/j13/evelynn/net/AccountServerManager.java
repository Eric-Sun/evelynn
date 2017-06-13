package com.j13.evelynn.net;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.j13.evelynn.core.AdminConstants;
import com.j13.evelynn.core.AdminException;
import com.j13.evelynn.core.config.PropertiesConfiguration;
import com.j13.evelynn.security.model.Account;
import com.j13.evelynn.security.model.Authority;
import com.j13.evelynn.security.model.Resource;
import com.j13.evelynn.util.InternetUtil;
import com.j13.evelynn.util.MD5Util;
import com.j13.garen.facade.req.AccountUpdateReq;
import com.j13.garen.facade.resp.*;
import com.j13.garen.poppy.core.CommonResultResp;
import com.j13.garen.poppy.util.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("accountServerManager")
public class AccountServerManager extends BaseServerManager {

    public boolean checkExisted(String name, String password) {
        String passwordAfterMd5 = MD5Util.getMD5String(password);
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "account.checkExisted");
        params.put("name", name);
        params.put("password", MD5Util.getMD5String(password));
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        AccountCheckExistedResp resp = JSON.parseObject(rawResponse, AccountCheckExistedResp.class);
        return resp.isExisted();
    }


    public List<Resource> getResourceList() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "resource.list");
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        ResourceListResp resp = JSON.parseObject(rawResponse, ResourceListResp.class);
        List<Resource> rst = Lists.newLinkedList();
        for (ResourceGetResp r : resp.getList()) {
            Resource resource = new Resource();
            BeanUtils.copyProperties(resource, r);
            rst.add(resource);
        }
        return rst;
    }

    public List<Authority> getAuthorityListByResourceName(String resourceName) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "resource.getAuthorityListByResourceName");
        params.put("name", resourceName);
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        AuthorityListResp resp = JSON.parseObject(rawResponse, AuthorityListResp.class);
        List<Authority> list = Lists.newLinkedList();
        for (AuthorityGetResp r : resp.getList()) {
            Authority a = new Authority();
            BeanUtils.copyProperties(a, r);
            list.add(a);
        }
        return list;
    }

    public List<Authority> getAuthorityListByAccountName(String accountNmae) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "account.getAuthorityByName");
        params.put("name", accountNmae);
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        AccountGetAuthorityByNameResp resp = JSON.parseObject(rawResponse, AccountGetAuthorityByNameResp.class);

        List<Authority> list = Lists.newLinkedList();
        for (AuthorityGetResp r : resp.getData()) {
            Authority a = new Authority();
            BeanUtils.copyProperties(a, r);
            list.add(a);
        }
        return list;
    }


    public int getAccountIdByName(String accountName) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "account.getAccountIdByName");
        params.put("name", accountName);
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        AccountGetAccountIdByNameResp resp = JSON.parseObject(rawResponse, AccountGetAccountIdByNameResp.class);
        return resp.getAccountId();
    }

    public List<Account> accountList() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "account.list");
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        AccountListResp resp = JSON.parseObject(rawResponse, AccountListResp.class);
        List<Account> list = Lists.newLinkedList();
        for (AccountGetResp r : resp.getList()) {
            Account a = new Account();
            BeanUtils.copyProperties(a, r);
            list.add(a);
        }
        return list;
    }

    public List<Authority> listAuthority() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "authority.list");
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        AuthorityListResp resp = JSON.parseObject(rawResponse, AuthorityListResp.class);
        List<Authority> list = Lists.newLinkedList();
        for (AuthorityGetResp r : resp.getList()) {
            Authority a = new Authority();
            BeanUtils.copyProperties(a, r);
            list.add(a);
        }
        return list;
    }

    public Account getAccount(int id) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "account.get");
        params.put("id", id);
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        AccountGetResp resp = JSON.parseObject(rawResponse, AccountGetResp.class);
        Account a = new Account();
        BeanUtils.copyProperties(a, resp);
        return a;
    }


    public void createAccount(String name, String password, int authorityId, String brief, String mobile, String realName) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "account.add");
        params.put("name", name);
        params.put("passwordAfterMD5", MD5Util.getMD5String(password));
        params.put("authorityId", authorityId);
        params.put("brief", brief);
        params.put("mobile", mobile);
        params.put("realName", realName);
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        PainterAddResp resp = JSON.parseObject(rawResponse, PainterAddResp.class);
    }

    public void updateAccount(int id, String name, String password, int authorityId, String brief, String mobile, String realName) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "account.update");
        params.put("id", id);
        params.put("name", name);
        params.put("passwordAfterMD5", MD5Util.getMD5String(password));
        params.put("authorityId", authorityId);
        params.put("brief", brief);
        params.put("mobile", mobile);
        params.put("realName", realName);
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        AccountUpdateReq resp = JSON.parseObject(rawResponse, AccountUpdateReq.class);
    }


    public void deleteAccount(int id) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "account.delete");
        params.put("id", id);
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        CommonResultResp resp = JSON.parseObject(rawResponse, CommonResultResp.class);
    }

    public Authority getAuthority(int id) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "authority.get");
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        AuthorityGetResp resp = JSON.parseObject(rawResponse, AuthorityGetResp.class);
        Authority a = new Authority();
        BeanUtils.copyProperties(a, resp);
        return a;
    }

    public void updateAuthority(int id, String name, String[] resourceIdList) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "authority.update");
        params.put("name", name);
        params.put("id", id);
        params.put("resourceIdArray", JSON.toJSONString(resourceIdList));
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        CommonResultResp resp = JSON.parseObject(rawResponse, CommonResultResp.class);
    }

    public void deleteAuthority(int id) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "authority.delete");
        params.put("id", id);
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        CommonResultResp resp = JSON.parseObject(rawResponse, CommonResultResp.class);
    }

    public void createAuthority(String name, String[] resourceIdList) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "authority.add");
        params.put("name", name);
        params.put("resourceIdArray", JSON.toJSONString(resourceIdList));
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        AuthorityAddResp resp = JSON.parseObject(rawResponse, AuthorityAddResp.class);
    }

    public void createResource(String name) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "resource.add");
        params.put("name", name);
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        ResourceAddResp resp = JSON.parseObject(rawResponse, ResourceAddResp.class);
    }

    public void updateResource(int id, String name) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "resource.update");
        params.put("id", id);
        params.put("name", name);
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        CommonResultResp resp = JSON.parseObject(rawResponse, CommonResultResp.class);
    }

    public void deleteResource(int id) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "resource.delete");
        params.put("id", id);
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        CommonResultResp resp = JSON.parseObject(rawResponse, CommonResultResp.class);
    }

    public Resource getResource(int id) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("act", "resource.get");
        params.put("id", id);
        String url = getServerUrl();
        String rawResponse = InternetUtil.post(url, params);
        ResourceGetResp resp = JSON.parseObject(rawResponse, ResourceGetResp.class);
        Resource r = new Resource();
        BeanUtils.copyProperties(r, resp);
        return r;
    }

}
