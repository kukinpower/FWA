package edu.school21.cinema.repository;

import edu.school21.cinema.model.AuthEventHistory;
import java.util.Optional;

public interface AuthHistoryRepository {

  Optional<AuthEventHistory> save(AuthEventHistory authEventHistory);
}
