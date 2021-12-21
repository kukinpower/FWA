package edu.school21.cinema.service;

import edu.school21.cinema.dto.ImagesHistoryDto;
import edu.school21.cinema.model.CinemaUser;
import java.io.File;
import java.util.List;

public interface UserImagesService {

  String getUserImage(CinemaUser cinemaUser);

  String getDefaultUserImageFilename();

  List<ImagesHistoryDto> getUserImagesHistoryList(CinemaUser cinemaUser);

  File getUserImageDirectory(CinemaUser cinemaUser);

  String getImageAsBase64String(String filename);
}
