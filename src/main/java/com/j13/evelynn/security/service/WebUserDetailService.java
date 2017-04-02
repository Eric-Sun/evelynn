package com.j13.evelynn.security.service;

import com.j13.evelynn.security.dao.UserDAO;
import com.j13.evelynn.security.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class WebUserDetailService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        Collection<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        List<Authority> authorityList = userDAO.getAuthorityByName(username);
        int uid = userDAO.getUserByName(username);

        for (Authority authority : authorityList) {
            SimpleGrantedAuthority auth = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorityList.add(auth);
        }
        return new AdminUserDetails(uid, username, "", grantedAuthorityList);
    }

}
