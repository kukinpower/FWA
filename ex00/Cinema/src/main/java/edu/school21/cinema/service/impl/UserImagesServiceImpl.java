package edu.school21.cinema.service.impl;

import edu.school21.cinema.dto.ImagesHistoryDto;
import edu.school21.cinema.exception.UserImageReadingException;
import edu.school21.cinema.properties.UserImageProperties;
import edu.school21.cinema.service.UserImagesService;
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
  public String getUserImage(HttpServletRequest req) {
    try {
      return readDefaultImageToString(req, userImageProperties.getDefaultImageFilename());
    } catch (IOException e) {
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
  public String getDefaultUserImageFilename() {
    return userImageProperties.getDefaultImageFilename();
  }

  private String readDefaultImageToString(HttpServletRequest req, String filename) throws IOException {
    InputStream resourceAsStream = req.getServletContext()
        .getResourceAsStream(userImageProperties.getImagesPrefix() + filename);
    byte[] bytes = new byte[resourceAsStream.available()];

    int read = resourceAsStream.read(bytes);
    if (read == 0) {
      throw new UserImageReadingException();
    }

    return "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
  }
}
