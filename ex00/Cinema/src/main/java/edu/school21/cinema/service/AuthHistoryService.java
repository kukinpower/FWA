package edu.school21.cinema.service;

import edu.school21.cinema.model.AuthEventHistory;
import edu.school21.cinema.model.CinemaUser;
import java.sql.Timestamp;

public interface AuthHistoryService {

  AuthEventHistory saveSignUpEvent(CinemaUser user, Timestamp createdAt, String ipAddress);
}
