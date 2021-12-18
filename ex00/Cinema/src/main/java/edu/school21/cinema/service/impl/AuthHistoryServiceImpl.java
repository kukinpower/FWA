package edu.school21.cinema.service.impl;

import edu.school21.cinema.model.AuthEventHistory;
import edu.school21.cinema.model.CinemaUser;
import edu.school21.cinema.repository.AuthHistoryRepository;
import edu.school21.cinema.service.AuthHistoryService;
import edu.school21.cinema.type.AuthEventType;
import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("authHistoryService")
@RequiredArgsConstructor
public class AuthHistoryServiceImpl implements AuthHistoryService {

  private final AuthHistoryRepository authHistoryRepository;

  @Override
  public AuthEventHistory saveSignUpEvent(CinemaUser cinemaUser, Timestamp createdAt, String ipAddress) {
    return saveEvent(AuthEventType.REGISTRATION, cinemaUser, createdAt, ipAddress);
  }

  @Override
  public AuthEventHistory saveSignInEvent(CinemaUser cinemaUser, Timestamp createdAt, String ipAddress) {
    return saveEvent(AuthEventType.AUTHORISATION, cinemaUser, createdAt, ipAddress);
  }

  @Override
  public List<AuthEventHistory> findAllByUserId(long cinemaUserId) {
    return authHistoryRepository.findAllByUserId(cinemaUserId);
  }

  private AuthEventHistory saveEvent(AuthEventType eventType, CinemaUser cinemaUser, Timestamp createdAt,
      String ipAddress) {
    AuthEventHistory authEventHistory = new AuthEventHistory();
    authEventHistory.setEventType(eventType.name());
    authEventHistory.setCinemaUserId(cinemaUser.getUserId());
    authEventHistory.setEventTime(createdAt);
    authEventHistory.setIpAddress(ipAddress);

    Optional<AuthEventHistory> res = authHistoryRepository.save(authEventHistory);
    return res.orElseThrow(NoSuchElementException::new);
  }
}
