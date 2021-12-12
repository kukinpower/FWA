package edu.school21.cinema.model.mapper;

import edu.school21.cinema.model.CinemaUser;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CinemaUserMapper implements RowMapper<CinemaUser> {

  @Override
  public CinemaUser mapRow(ResultSet rs, int rowNum) throws SQLException {
    CinemaUser cinemaUser = new CinemaUser();

    cinemaUser.setUserId(rs.getLong("user_id"));
    cinemaUser.setEmail(rs.getString("email"));
    cinemaUser.setPassword(rs.getString("password"));
    cinemaUser.setFirstName(rs.getString("first_name"));
    cinemaUser.setLastName(rs.getString("last_name"));
    cinemaUser.setPhoneNumber(rs.getString("phone_number"));

    return cinemaUser;
  }
}
