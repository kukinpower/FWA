package edu.school21.cinema.model.mapper;

import edu.school21.cinema.model.AuthEventHistory;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AuthHistoryEventRowMapper implements RowMapper<AuthEventHistory> {

  @Override
  public AuthEventHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
    AuthEventHistory authEventHistory = new AuthEventHistory();

    authEventHistory.setEventId(rs.getLong("event_id"));
    authEventHistory.setCinemaUserId(rs.getLong("cinema_user_id"));
    authEventHistory.setEventType(rs.getString("event_type"));
    authEventHistory.setEventTime(rs.getTimestamp("event_time"));
    authEventHistory.setIpAddress(rs.getString("ip_address"));

    return authEventHistory;
  }
}
