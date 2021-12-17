package edu.school21.cinema.service.impl;

import edu.school21.cinema.dto.ImagesHistoryDto;
import edu.school21.cinema.exception.UserImageReadingException;
import edu.school21.cinema.model.CinemaUser;
import edu.school21.cinema.properties.UserImageProperties;
import edu.school21.cinema.service.UserImagesService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("userImagesService")
@RequiredArgsConstructor
public class UserImagesServiceImpl implements UserImagesService {

  private final UserImageProperties userImageProperties;

  @Override
  public String getUserImage(HttpServletRequest req, CinemaUser cinemaUser) {
    try {
      return readImageToString(req, cinemaUser);
    } catch (IOException e) {
      e.printStackTrace();
      throw new UserImageReadingException();
    }
  }

  @Override
  public List<ImagesHistoryDto> getImagesHistoryList() {
    List<ImagesHistoryDto> imagesHistoryList = new ArrayList<>();
    imagesHistoryList.add(new ImagesHistoryDto("some.png", "10Kb", "image/png"));
    imagesHistoryList.add(new ImagesHistoryDto("next.jpeg", "3Mb", "image/jpeg"));

    return imagesHistoryList;
  }

  @Override
  public File getUserImageDirectory(CinemaUser cinemaUser) {
    File directory = new File(userImageProperties.getImagesPrefix() + cinemaUser.getUserId());
    if (!directory.exists()) {
      directory.mkdir();
    }
    return directory;
  }

  @Override
  public String getDefaultUserImageFilename() {
    return userImageProperties.getDefaultImageFilename();
  }

  /*
     String fullPath = req.getServletContext()
        .getRealPath(userImageProperties.getImagesPrefix()) +
        (userImageProperties.getDefaultImageFilename()
            .equals(cinemaUser.getImageFilename()) ? cinemaUser.getImageFilename() :
            cinemaUser.getUserId() + "/" + cinemaUser.getImageFilename());
   */

  private String readImageToString(HttpServletRequest req, CinemaUser cinemaUser) throws IOException {
    String filename = userImageProperties.getDefaultImageFilename()
        .equals(cinemaUser.getImageFilename()) ? cinemaUser.getImageFilename() :
        cinemaUser.getUserId() + "/" + cinemaUser.getImageFilename();

    File image = new File(userImageProperties.getImagesPrefix() + filename);
    FileInputStream fileInputStream = new FileInputStream(image);

    byte[] bytes = new byte[fileInputStream.available()];

    int read = fileInputStream.read(bytes);
    if (read == 0) {
      throw new UserImageReadingException();
    }

    return "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
  }
}
