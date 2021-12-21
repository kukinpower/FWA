package edu.school21.cinema.servlet;

import edu.school21.cinema.properties.JspPathProperties;
import edu.school21.cinema.type.ContentType;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

@WebServlet("/")
public class HomePageServlet extends HttpServlet {

  private ApplicationContext applicationContext;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType(ContentType.HTML.getType());

    String signUpPath = ((JspPathProperties) applicationContext.getBean("jspPathProperties")).getHome();
    RequestDispatcher requestDispatcher = req.getRequestDispatcher(signUpPath);
    requestDispatcher.forward(req, resp);
  }

  @Override
  public void init(ServletConfig config) {
    applicationContext = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
  }
}
