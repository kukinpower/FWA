package edu.school21.cinema.servlet;

import edu.school21.cinema.model.CinemaUser;
import edu.school21.cinema.properties.JspPathProperties;
import edu.school21.cinema.service.AuthHistoryService;
import edu.school21.cinema.service.CinemaUserService;
import edu.school21.cinema.token.TokenConstant;
import edu.school21.cinema.type.ContentType;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

  private ApplicationContext applicationContext;
  private CinemaUserService cinemaUserService;
  private AuthHistoryService authHistoryService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType(ContentType.HTML.getType());

    String signInPath = ((JspPathProperties) applicationContext.getBean(
        "jspPathProperties")).getSignIn();
    RequestDispatcher requestDispatcher = req.getRequestDispatcher(signInPath);
    requestDispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Timestamp signInTime = Timestamp.valueOf(LocalDateTime.now());

    String email = req.getParameter("email");
    String password = req.getParameter("password");

    Optional<CinemaUser> cinemaUser = cinemaUserService.signIn(email, password);

    if (cinemaUser.isPresent()) {
      req.getSession().setAttribute(TokenConstant.TOKEN, cinemaUser.get().getEmail());
      authHistoryService.saveSignInEvent(cinemaUser.get(), signInTime, req.getRemoteAddr());
      resp.sendRedirect("/profile");
    } else {
      resp.sendRedirect("/signUp");
    }
  }

  @Override
  public void init(ServletConfig config) {
    applicationContext = (ApplicationContext) config.getServletContext()
        .getAttribute("applicationContext");
    getBeansFromSpringApplicationContext();
  }

  private void getBeansFromSpringApplicationContext() {
    if (cinemaUserService == null) {
      cinemaUserService = applicationContext.getBean("cinemaUserService", CinemaUserService.class);
    }
    if (authHistoryService == null) {
      authHistoryService = applicationContext.getBean("authHistoryService", AuthHistoryService.class);
    }
  }
}
