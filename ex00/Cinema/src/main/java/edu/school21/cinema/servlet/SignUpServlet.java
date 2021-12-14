package edu.school21.cinema.servlet;

import edu.school21.cinema.model.AuthEventHistory;
import edu.school21.cinema.model.CinemaUser;
import edu.school21.cinema.properties.JspPathProperties;
import edu.school21.cinema.service.AuthHistoryService;
import edu.school21.cinema.service.CinemaUserService;
import edu.school21.cinema.service.PasswordEncoderService;
import edu.school21.cinema.service.impl.CinemaUserServiceImpl;
import edu.school21.cinema.type.ContentType;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

  private ApplicationContext applicationContext;
  private CinemaUserService cinemaUserService;
  private AuthHistoryService authHistoryService;
  private PasswordEncoderService passwordEncoderService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType(ContentType.HTML.getType());

    String signUpPath = ((JspPathProperties) applicationContext.getBean(
        "jspPathProperties")).getSignUp();
    RequestDispatcher requestDispatcher = req.getRequestDispatcher(signUpPath);
    requestDispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    getBeansFromSpringApplicationContext();

    Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    CinemaUser user = cinemaUserService.save(
        new CinemaUser(req.getParameter("first-name")
        , req.getParameter("last-name")
        , req.getParameter("phone-number")
        , req.getParameter("email")
        , passwordEncoderService.encode(req.getParameter("password"))
    ));

    AuthEventHistory authEventHistory = authHistoryService.saveSignUpEvent(user, createdAt, req.getRemoteAddr());

    HttpSession httpSession = req.getSession();
    httpSession.setAttribute("email", user.getEmail());
    resp.sendRedirect("/profile");
  }

  private void getBeansFromSpringApplicationContext() {
    if (cinemaUserService == null) {
      cinemaUserService = applicationContext.getBean("cinemaUserService", CinemaUserService.class);
    }
    if (passwordEncoderService == null) {
      passwordEncoderService = applicationContext.getBean("passwordEncoderService",
          PasswordEncoderService.class);
    }
    if (authHistoryService == null) {
      authHistoryService = applicationContext.getBean("authHistoryService",
          AuthHistoryService.class);
    }
  }

  @Override
  public void init(ServletConfig config) {
    applicationContext = (ApplicationContext) config.getServletContext()
        .getAttribute("applicationContext");
  }
}
