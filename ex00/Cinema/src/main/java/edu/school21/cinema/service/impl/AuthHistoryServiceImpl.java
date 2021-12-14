package edu.school21.cinema.service.impl;

import edu.school21.cinema.model.AuthEventHistory;
import edu.school21.cinema.model.CinemaUser;
import edu.school21.cinema.repository.AuthHistoryRepository;
import edu.school21.cinema.service.AuthHistoryService;
import edu.school21.cinema.type.AuthEventType;
import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("authHistoryService")
@RequiredArgsConstructor
public class AuthHistoryServiceImpl implements AuthHistoryService {

  private final AuthHistoryRepository authHistoryRepository;

  @Override
  public AuthEventHistory saveSignUpEvent(CinemaUser user, Timestamp createdAt, String ipAddress) {
    AuthEventHistory authEventHistory = new AuthEventHistory();
    authEventHistory.setEventType(AuthEventType.REGISTRATION.name());
    authEventHistory.setCinemaUserId(user.getUserId());
    authEventHistory.setEventTime(createdAt);
    authEventHistory.setIpAddress(ipAddress);

    Optional<AuthEventHistory> res = authHistoryRepository.save(authEventHistory);
    return res.orElseThrow(NoSuchElementException::new);
  }
}
