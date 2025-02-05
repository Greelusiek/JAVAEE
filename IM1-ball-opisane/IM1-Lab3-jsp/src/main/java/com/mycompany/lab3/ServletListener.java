package com.mycompany.lab3;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ServletListener implements ServletContextListener {
    
    @Inject
    private StampDao stampDao;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        sce.getServletContext().setAttribute("stampDao", stampDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
