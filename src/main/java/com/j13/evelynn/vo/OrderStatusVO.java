package com.j13.evelynn.vo;

public class OrderStatusVO {
    private int id;
    private String content;

    public OrderStatusVO(int id, String content) {
        this.content = content;
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
