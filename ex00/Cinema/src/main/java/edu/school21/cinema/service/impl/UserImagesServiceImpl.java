package edu.school21.cinema.service.impl;

import edu.school21.cinema.dto.ImagesHistoryDto;
import edu.school21.cinema.exception.UserImageReadingException;
import edu.school21.cinema.model.CinemaUser;
import edu.school21.cinema.properties.UserImageProperties;
import edu.school21.cinema.service.UserImagesService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

@Service("userImagesService")
@RequiredArgsConstructor
public class UserImagesServiceImpl implements UserImagesService {

  private final UserImageProperties userImageProperties;

  @Override
  public String getUserImage(CinemaUser cinemaUser) {
    return getImageAsBase64String(getFilenameByCinemaUser(cinemaUser));
  }

  @Override
  public List<ImagesHistoryDto> getUserImagesHistoryList(CinemaUser cinemaUser) {
    List<ImagesHistoryDto> collect = new ArrayList<>();
    collect.add(generateImagesHistoryDtoFromFile(
        new File(userImageProperties.getImagesPrefix() + getDefaultUserImageFilename())));

    File userImageDirectory = getUserImageDirectory(cinemaUser);

    collect.addAll(Arrays.stream(
            Objects.requireNonNull(userImageDirectory.listFiles()))
        .map(this::generateImagesHistoryDtoFromFile)
        .collect(Collectors.toList()));

    return collect;
  }

  private ImagesHistoryDto generateImagesHistoryDtoFromFile(File file) {
    return new ImagesHistoryDto(file.getName(), getImageSize(file),
        "image/" + getFileExtension(file));
  }

  private String getImageSize(File image) {
    return FileUtils.byteCountToDisplaySize(image.length());
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

  private String getFilenameByCinemaUser(CinemaUser cinemaUser) {
    return userImageProperties.getDefaultImageFilename()
        .equals(cinemaUser.getImageFilename()) ? cinemaUser.getImageFilename() :
        cinemaUser.getUserId() + "/" + cinemaUser.getImageFilename();
  }

  @Override
  public String getImageAsBase64String(String filename) {
    try {
      String extension = getFileStringExtension(filename);

      File image = new File(userImageProperties.getImagesPrefix() + filename);
      FileInputStream fileInputStream = new FileInputStream(image);

      byte[] bytes = new byte[fileInputStream.available()];

      int read = fileInputStream.read(bytes);
      if (read == 0) {
        throw new UserImageReadingException();
      }

      return "data:image/" + extension + ";base64," + Base64.getEncoder().encodeToString(bytes);
    } catch (IOException e) {
      e.printStackTrace();
      throw new UserImageReadingException();
    }
  }

  private String getFileExtension(File file) {
    return getFileStringExtension(file.getName());
  }

  private String getFileStringExtension(String filename) {
    return filename.substring(filename.lastIndexOf(".") + 1);
  }
}
