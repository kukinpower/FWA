package edu.school21.cinema.model;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthEventHistory {

  private long eventId;
  private long cinemaUserId;
  private String eventType;
  private Timestamp eventTime;
  private String ipAddress;
}
