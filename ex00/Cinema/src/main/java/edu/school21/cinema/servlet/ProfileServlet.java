package edu.school21.cinema.servlet;

import edu.school21.cinema.dto.ImagesHistoryDto;
import edu.school21.cinema.model.AuthEventHistory;
import edu.school21.cinema.properties.JspPathProperties;
import edu.school21.cinema.service.AuthHistoryService;
import edu.school21.cinema.service.CinemaUserService;
import edu.school21.cinema.service.UserImagesService;
import edu.school21.cinema.type.ContentType;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    resp.setContentType(ContentType.HTML.getType());

    List<AuthEventHistory> authEvents = new ArrayList<>();
    AuthEventHistory authEventHistory = new AuthEventHistory();
    authEventHistory.setEventType("Registration");
    authEventHistory.setEventTime(Timestamp.valueOf(LocalDateTime.now()));
    authEventHistory.setIpAddress("127.0.0.1");
    authEvents.add(authEventHistory);
    authEvents.add(authEventHistory);

    List<ImagesHistoryDto> imagesHistoryList = new ArrayList<>();
    imagesHistoryList.add(new ImagesHistoryDto("some.png", "10Kb", "image/png"));
    imagesHistoryList.add(new ImagesHistoryDto("next.jpeg", "3Mb", "image/jpeg"));

    req.setAttribute("profileImage", userImagesService.getUserImage(req));
    req.setAttribute("userEmail", "some@mail.com");
    req.setAttribute("authEvents", authEvents);
    req.setAttribute("imagesHistoryList", imagesHistoryList);

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
