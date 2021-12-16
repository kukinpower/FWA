package edu.school21.cinema.service.impl;

import edu.school21.cinema.exception.UserImageReadingException;
import edu.school21.cinema.properties.UserImageProperties;
import edu.school21.cinema.service.UserImagesService;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
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
      return readDefaultImageToString(req, userImageProperties.getDefaultImage());
    } catch (IOException e) {
      throw new UserImageReadingException();
    }
  }

  private String readDefaultImageToString(HttpServletRequest req, String path) throws IOException {
    InputStream resourceAsStream = req.getServletContext().getResourceAsStream(path);
    byte[] bytes = new byte[resourceAsStream.available()];
    int read = resourceAsStream.read(bytes);
    if (read == 0) {
      throw new UserImageReadingException();
    }

    return "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
  }
}
