package edu.school21.cinema.servlet;

import edu.school21.cinema.properties.JspPathProperties;
import edu.school21.cinema.type.ContentType;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

  private ApplicationContext applicationContext;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType(ContentType.HTML.getType());

    String signUpPath = ((JspPathProperties) applicationContext.getBean("jspPathProperties")).getSignUp();
    RequestDispatcher requestDispatcher = req.getRequestDispatcher(signUpPath);
    requestDispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    PrintWriter writer = resp.getWriter();
    writer.println("<html>Hello, I am a Java servlet!</html>");
    writer.flush();
  }

  @Override
  public void init(ServletConfig config) {
    this.applicationContext = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
  }
}
