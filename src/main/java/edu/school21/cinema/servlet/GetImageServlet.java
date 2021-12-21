package edu.school21.cinema.servlet;

import edu.school21.cinema.exception.NoCinemaUserFoundException;
import edu.school21.cinema.model.CinemaUser;
import edu.school21.cinema.properties.JspPathProperties;
import edu.school21.cinema.service.CinemaUserService;
import edu.school21.cinema.service.UserImagesService;
import edu.school21.cinema.token.TokenConstant;
import edu.school21.cinema.type.ContentType;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

@WebServlet("/getImage/*")
@MultipartConfig
@Slf4j
public class GetImageServlet extends HttpServlet {

  private ApplicationContext applicationContext;
  private CinemaUserService cinemaUserService;
  private UserImagesService userImagesService;
  private JspPathProperties jspPathProperties;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    String emailToken = (String) session.getAttribute(TokenConstant.TOKEN);
    CinemaUser cinemaUser = cinemaUserService.findByEmail(emailToken).orElseThrow(
        NoCinemaUserFoundException::new);

    resp.setContentType(ContentType.HTML.getType());

    String filename = req.getRequestURI().substring(req.getRequestURI().indexOf("/", 1) + 1);

    req.setAttribute("profileImage", userImagesService.getImageAsBase64String(filename));
    req.setAttribute("userEmail", cinemaUser.getEmail());

    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/" + jspPathProperties.getImage());
    requestDispatcher.forward(req, resp);
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
    if (userImagesService == null) {
      userImagesService = applicationContext.getBean("userImagesService", UserImagesService.class);
    }
    if (jspPathProperties == null) {
      jspPathProperties = applicationContext.getBean("jspPathProperties", JspPathProperties.class);
    }
  }
}
