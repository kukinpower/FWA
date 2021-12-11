package edu.school21.cinema.listener;

import edu.school21.cinema.config.AppConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebListener
public class CinemaContextListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    sce.getServletContext()
        .setAttribute("applicationContext",
            new AnnotationConfigApplicationContext(AppConfig.class));
  }
}
