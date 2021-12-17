package edu.school21.cinema.servlet;

import edu.school21.cinema.exception.CouldntWriteImageOnDiskException;
import edu.school21.cinema.model.CinemaUser;
import edu.school21.cinema.properties.JspPathProperties;
import edu.school21.cinema.service.CinemaUserService;
import edu.school21.cinema.service.UserImagesService;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

@WebServlet("/uploadImage")
@MultipartConfig
@Slf4j
public class UploadImageServlet extends HttpServlet {

  private ApplicationContext applicationContext;
  private CinemaUserService cinemaUserService;
  private UserImagesService userImagesService;
  private JspPathProperties jspPathProperties;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String emailToken = (String) req.getAttribute("emailToken");
    CinemaUser cinemaUser = cinemaUserService.findByEmail(emailToken);
    File directory = userImagesService.getUserImageDirectory(cinemaUser);

    writeImageToUserDirectory(req, directory, cinemaUser);

    cinemaUserService.updateCinemaUser(cinemaUser);

    resp.sendRedirect(jspPathProperties.getProfile());
  }

  private CinemaUser writeImageToUserDirectory(HttpServletRequest req, File directory, CinemaUser cinemaUser) {
    try {
      String filename = "";
      for (Part part : req.getParts()) {
        filename = part.getSubmittedFileName();
        part.write(directory + "/" + filename);
      }
      if (filename.isEmpty()) {
        log.error("No parts provided");
      }
      cinemaUser.setImageFilename(filename);
      return cinemaUser;
    } catch (IOException | ServletException e) {
      e.printStackTrace();
      log.error("An error occurred while writing file on disk");
      throw new CouldntWriteImageOnDiskException();
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
    if (userImagesService == null) {
      userImagesService = applicationContext.getBean("userImagesService", UserImagesService.class);
    }
    if (jspPathProperties == null) {
      jspPathProperties = applicationContext.getBean("jspPathProperties", JspPathProperties.class);
    }
  }
}
