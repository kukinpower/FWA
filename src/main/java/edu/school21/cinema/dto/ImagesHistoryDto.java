package edu.school21.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImagesHistoryDto {

  private String filename;
  private String size;
  private String mime;
}
