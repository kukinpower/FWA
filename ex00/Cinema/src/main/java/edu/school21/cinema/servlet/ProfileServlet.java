package edu.school21.cinema.servlet;

import edu.school21.cinema.exception.NoCinemaUserFoundException;
import edu.school21.cinema.model.AuthEventHistory;
import edu.school21.cinema.model.CinemaUser;
import edu.school21.cinema.properties.JspPathProperties;
import edu.school21.cinema.service.AuthHistoryService;
import edu.school21.cinema.service.CinemaUserService;
import edu.school21.cinema.service.UserImagesService;
import edu.school21.cinema.token.TokenConstant;
import edu.school21.cinema.type.ContentType;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

  private ApplicationContext applicationContext;
  private CinemaUserService cinemaUserService;
  private AuthHistoryService authHistoryService;
  private JspPathProperties jspPathProperties;
  private UserImagesService userImagesService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    String emailToken = (String) session.getAttribute(TokenConstant.TOKEN);
    CinemaUser cinemaUser = cinemaUserService.findByEmail(emailToken).orElseThrow(
        NoCinemaUserFoundException::new);

    List<AuthEventHistory> authEvents = authHistoryService.findAllByUserId(cinemaUser.getUserId());

    resp.setContentType(ContentType.HTML.getType());

    req.setAttribute("profileImage", userImagesService.getUserImage(cinemaUser));
    req.setAttribute("userEmail", cinemaUser.getEmail());
    req.setAttribute("authEvents", authEvents);
    req.setAttribute("imagesHistoryList", userImagesService.getUserImagesHistoryList(cinemaUser));

    RequestDispatcher requestDispatcher = req.getRequestDispatcher(jspPathProperties.getProfile());
    requestDispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.sendRedirect("/profile");
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
    if (jspPathProperties == null) {
      jspPathProperties = applicationContext.getBean("jspPathProperties", JspPathProperties.class);
    }
    if (userImagesService == null) {
      userImagesService = applicationContext.getBean("userImagesService", UserImagesService.class);
    }
  }
}
