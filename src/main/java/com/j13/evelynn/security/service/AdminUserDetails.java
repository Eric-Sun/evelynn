package com.j13.evelynn.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class AdminUserDetails extends User {

    private int uid;

    public AdminUserDetails(int uid, String username, String password, Collection<GrantedAuthority> list) {
        super(username, password, list);
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
