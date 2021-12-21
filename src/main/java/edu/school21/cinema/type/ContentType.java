package edu.school21.cinema.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContentType {
  HTML("text/html");

  private final String type;
}
