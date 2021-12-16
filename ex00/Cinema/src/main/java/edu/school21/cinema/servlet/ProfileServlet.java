package edu.school21.cinema.servlet;

import edu.school21.cinema.exception.WtfException;
import edu.school21.cinema.model.AuthEventHistory;
import edu.school21.cinema.properties.JspPathProperties;
import edu.school21.cinema.service.AuthHistoryService;
import edu.school21.cinema.service.CinemaUserService;
import edu.school21.cinema.service.PasswordEncoderService;
import edu.school21.cinema.type.ContentType;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
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

    JspPathProperties jspPathProperties = (JspPathProperties) applicationContext.getBean(
        "jspPathProperties");
    String profilePath = jspPathProperties.getProfile();
    String defaultImagePath = jspPathProperties.getDefaultImage();

    req.setAttribute("profileImage", readFileToString(req, defaultImagePath));
    req.setAttribute("authEvents", authEvents);

    RequestDispatcher requestDispatcher = req.getRequestDispatcher(profilePath);

    requestDispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.sendRedirect("/profile");
  }

  private String readFileToString(HttpServletRequest req, String path) throws IOException {
    InputStream resourceAsStream = req.getServletContext().getResourceAsStream(path);
    byte[] bytes = new byte[resourceAsStream.available()];
    int read = resourceAsStream.read(bytes);
    if (read == 0) {
      throw new WtfException();
    }

    return "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
  }

  @Override
  public void init(ServletConfig config) {
    applicationContext = (ApplicationContext) config.getServletContext()
        .getAttribute("applicationContext");
  }
}
