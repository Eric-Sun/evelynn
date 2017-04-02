package com.j13.evelynn.model;

/**
 * Created by sunbo on 15-3-29.
 */
public class ActivateCode {
    public static final int NOT_USED = 0;
    public static final int USED = 1;

    private int id;
    private String code;
    private String groupName;
    private int used;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getNotUsed() {
        return NOT_USED;
    }

    public static int getUsed() {
        return USED;
    }

    public void setUsed(int used) {
        this.used = used;
    }
}
