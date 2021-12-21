package edu.school21.cinema.service;

import edu.school21.cinema.model.AuthEventHistory;
import edu.school21.cinema.model.CinemaUser;
import java.sql.Timestamp;
import java.util.List;

public interface AuthHistoryService {

  AuthEventHistory saveSignUpEvent(CinemaUser cinemaUser, Timestamp createdAt, String ipAddress);

  List<AuthEventHistory> findAllByUserId(long cinemaUserId);

  AuthEventHistory saveSignInEvent(CinemaUser cinemaUser, Timestamp createdAt, String ipAddress);
}
