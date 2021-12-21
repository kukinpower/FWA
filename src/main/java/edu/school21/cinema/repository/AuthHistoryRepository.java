package edu.school21.cinema.repository;

import edu.school21.cinema.model.AuthEventHistory;
import java.util.List;
import java.util.Optional;

public interface AuthHistoryRepository {

  Optional<AuthEventHistory> save(AuthEventHistory authEventHistory);

  List<AuthEventHistory> findAllByUserId(long id);
}
