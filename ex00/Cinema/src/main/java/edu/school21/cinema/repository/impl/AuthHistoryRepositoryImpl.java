package edu.school21.cinema.repository.impl;

import edu.school21.cinema.model.AuthEventHistory;
import edu.school21.cinema.model.CinemaUser;
import edu.school21.cinema.repository.AuthHistoryRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthHistoryRepositoryImpl implements AuthHistoryRepository {

  private final JdbcTemplate jdbcTemplate;

  private static final String SAVE_EVENT_QUERY = "insert into auth_event_history "
      + "(cinema_user_id, event_type, event_time, ip_address)\n"
      + "values (?, ?, ?, ?);";

  @Override
  public Optional<AuthEventHistory> save(AuthEventHistory authEventHistory) {
    GeneratedKeyHolder holder = new GeneratedKeyHolder();

    int update = jdbcTemplate.update(con -> {
      PreparedStatement statement = con.prepareStatement(SAVE_EVENT_QUERY, Statement.RETURN_GENERATED_KEYS);
      statement.setLong(1, authEventHistory.getCinemaUserId());
      statement.setString(2, authEventHistory.getEventType());
      statement.setTimestamp(3, authEventHistory.getEventTime());
      statement.setString(4, authEventHistory.getIpAddress());
      return statement;
    }, holder);

    long primaryKey = (Long)Objects.requireNonNull(holder.getKeys()).get("event_id");
    if (update == 0) {
      return Optional.empty();
    }
    authEventHistory.setEventId(primaryKey);
    return Optional.of(authEventHistory);
  }
}
