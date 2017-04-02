package com.j13.evelynn.security.service;

import com.j13.evelynn.security.dao.AuthorityDAO;
import com.j13.evelynn.security.dao.ResourceDAO;
import com.j13.evelynn.security.dao.UserDAO;
import com.j13.evelynn.security.model.Authority;
import com.j13.evelynn.security.model.Resource;
import com.j13.evelynn.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunbo on 14-11-16.
 */
@Service
public class AuthorityService {

    @Autowired
    AuthorityDAO authorityDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    ResourceDAO resourceDAO;

    public List<Authority> listAuthority() {
        return authorityDAO.list();
    }

    public void updateAuthority(int id, String authorityName, String[] resourceIdList) {
        Authority authority = new Authority();
        authority.setId(id);
        authority.setName(authorityName);
        authorityDAO.update(authority, resourceIdList);
    }

    public void deleteAuthority(int id) {
        authorityDAO.delete(id);
    }


    public List<User> userList() {
        return userDAO.list();
    }

    public void userUpdate(int id, String name, int authorityId) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAuthorityId(authorityId);

        userDAO.update(user);

    }

    public User getUser(int id) {
        User user = userDAO.getUser(id);
        return user;
    }

    public void deleteUser(int id) {
        userDAO.delete(id);

    }

    public void createUser(String name, int authorityId) {
        userDAO.create(name, authorityId);
    }

    public Authority getAuthority(int id) {
        Authority authority = authorityDAO.get(id);
        List<Integer> resourceIdList = resourceDAO.getResourceListByAuthorityId(id);
        authority.setResourceIdList(resourceIdList);
        return authority;
    }

    public void createAuthority(String name, String[] resourceIdList) {
        authorityDAO.insert(name, resourceIdList);
    }

    //// resource

    public void createResource(String name) {
        resourceDAO.insert(name);
    }

    public Resource getResource(int id) {
        return resourceDAO.get(id);
    }

    public List<Resource> getResouceList() {
        return resourceDAO.getResourceList();
    }


    public void updateResource(int id, String name) {
        resourceDAO.update(id, name);
    }

    public void deleteResource(int id) {
        resourceDAO.delete(id);
    }
}
