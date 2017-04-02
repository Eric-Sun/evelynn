package com.j13.evelynn.security.model;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-10-9
 * Time: 下午2:54
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private int id;
    private String name;
    private int authorityId;
    private String authorityName;

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
