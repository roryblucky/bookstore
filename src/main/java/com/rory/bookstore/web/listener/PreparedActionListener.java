package com.rory.bookstore.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by RoryGao on 15/6/13.
 */
public class PreparedActionListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties pro = new Properties();
        Map<String, String> actionMap = new LinkedHashMap<>();
        try {
            pro.load(PreparedActionListener.class.getClassLoader().getResourceAsStream("actions.properties"));
            for (Map.Entry entry : pro.entrySet()) {
                actionMap.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sce.getServletContext().setAttribute("actionMap", actionMap);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
