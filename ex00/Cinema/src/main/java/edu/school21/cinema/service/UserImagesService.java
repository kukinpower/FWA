package edu.school21.cinema.service;

import edu.school21.cinema.dto.ImagesHistoryDto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface UserImagesService {

  String getUserImage(HttpServletRequest req);

  String getDefaultUserImageFilename();

  List<ImagesHistoryDto> getImagesHistoryList();
}
