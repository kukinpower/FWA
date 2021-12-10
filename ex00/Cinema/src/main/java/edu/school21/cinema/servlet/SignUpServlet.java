package edu.school21.cinema.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
    PrintWriter writer = resp.getWriter();
    writer.println("<html>Hello, I am a Java servlet!</html>");
    writer.flush();
  }

//  @Override
//  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//      throws ServletException, IOException {
//    super.doPost(req, resp);
//  }

//  @Override
//  public void init(ServletConfig config) throws ServletException {
//    this.applicationContext = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
//  }
}
