package edu.school21.cinema.service;

import edu.school21.cinema.dto.ImagesHistoryDto;
import edu.school21.cinema.model.CinemaUser;
import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface UserImagesService {

  String getUserImage(HttpServletRequest req, CinemaUser cinemaUser);

  String getDefaultUserImageFilename();

  List<ImagesHistoryDto> getImagesHistoryList();

  File getUserImageDirectory(CinemaUser cinemaUser);
}
