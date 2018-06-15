package com.j13.evelynn.security.service;

import com.j13.evelynn.net.AccountServerManager;
import com.j13.evelynn.security.model.Authority;
import com.j13.evelynn.security.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class WebInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {


    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
    @Autowired
    private AccountServerManager accountServerManager;

    private RequestMatcher requestMatcher;

    public WebInvocationSecurityMetadataSourceService(AccountServerManager accountServerManager) {
        this.accountServerManager = accountServerManager;
        loadResourceDefine();
    }

    public WebInvocationSecurityMetadataSourceService() {
    }

    /**
     * 加载资源和权限的列表
     */
    private void loadResourceDefine() {
        List<Resource> resourceList = accountServerManager.getResourceList();
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        for (Resource resource : resourceList) {
            Collection<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
            List<Authority> authorityList = accountServerManager.getAuthorityListByResourceName(resource.getName());
            for (Authority authority : authorityList) {
                ConfigAttribute ca = new SecurityConfig(authority.getName());
                attributes.add(ca);
            }
            resourceMap.put(resource.getName(), attributes);
        }
    }

    /**
     * 通过资源url获取权限
     */
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        loadResourceDefine();
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        Iterator<String> iterator = resourceMap.keySet().iterator();
        while (iterator.hasNext()) {
            String uri = iterator.next();
            requestMatcher = new RegexRequestMatcher(uri, request.getMethod(), true);

            if (requestMatcher.matches(request)) {
                return resourceMap.get(uri);
            }
        }
        return null;
    }


    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }


    public AccountServerManager getAccountServerManager() {
        return accountServerManager;
    }

    public void setAccountServerManager(AccountServerManager accountServerManager) {
        this.accountServerManager = accountServerManager;
    }
}
