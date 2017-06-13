package com.j13.evelynn.core;


import com.j13.evelynn.core.config.PropertiesConfiguration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-11
 * Time: 下午7:14
 * To change this template use File | Settings | File Templates.
 */
public class BaseListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        try {
//        PropertiesConfiguration.getInstance().addResource("/admin-core.properties");
//    } catch (AdminException e) {
//        e.printStackTrace();
//    }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
