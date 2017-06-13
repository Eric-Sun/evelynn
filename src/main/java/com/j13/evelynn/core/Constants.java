package com.j13.evelynn.core;

import java.util.*;

public class Constants {

    public static class DB {
        public static int NOT_DELETED = 0;
        public static int DELETED = 1;
    }


    public static Map<Integer, String> orderStatusMap = new HashMap<Integer, String>() {
        {
            put(0, "初始化");
            put(1, "第1步");
            put(2, "第2步");
            put(3, "第3步");
            put(4, "第4步");
            put(5, "结束");
        }
    };

    public static List<Integer> orderStatusList = new LinkedList<Integer>(){{
        add(0);
        add(1);
        add(2);
        add(3);
        add(4);
        add(5);
    }};

}
