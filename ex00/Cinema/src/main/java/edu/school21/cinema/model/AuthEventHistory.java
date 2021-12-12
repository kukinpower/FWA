package edu.school21.cinema.model;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthEventHistory {

  private long eventId;
  private long cinemaUserId;
  private String eventType;
  private Timestamp eventTime;
  private String ipAddress;
}
