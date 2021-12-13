package edu.school21.cinema.repository.impl;

import edu.school21.cinema.model.CinemaUser;
import edu.school21.cinema.model.mapper.CinemaUserMapper;
import edu.school21.cinema.repository.CinemaUserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CinemaUserRepositoryImpl implements CinemaUserRepository {

  private final JdbcTemplate jdbcTemplate;

  private static final String FIND_BY_EMAIL_QUERY = "select * from cinema_users where email = ? limit 1";
  private static final String SAVE_USER_QUERY = "insert into cinema_users "
      + "(email, password, first_name, last_name, phone_number)\n"
      + "values (?, ?, ?, ?, ?);";

  @Override
  public Optional<CinemaUser> save(CinemaUser cinemaUser) {
    int update = jdbcTemplate.update(SAVE_USER_QUERY,
        cinemaUser.getEmail()
        , cinemaUser.getPassword()
        , cinemaUser.getFirstName()
        , cinemaUser.getLastName()
        , cinemaUser.getPhoneNumber()
    );
    if (update == 0) {
      return Optional.empty();
    }
    return Optional.of(cinemaUser);
  }

  @Override
  public Optional<CinemaUser> findByEmail(String email) {
    try {
      return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_EMAIL_QUERY, new CinemaUserMapper(), email));
    } catch (Exception e) {
      e.printStackTrace();
      log.error("Not found for email: " + email);
      return Optional.empty();
    }
  }
}
