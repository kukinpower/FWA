package edu.school21.cinema.servlet;


import edu.school21.cinema.service.CinemaUsersService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;

@WebServlet("/users")
public class CinemaUsersServlet extends HttpServlet {

  private CinemaUsersService cinemaUsersService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
    this.cinemaUsersService = springContext.getBean(CinemaUsersService.class);
  }
}
