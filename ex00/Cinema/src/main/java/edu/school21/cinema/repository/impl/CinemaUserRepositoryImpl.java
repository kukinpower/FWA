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

  @Override
  public CinemaUser save(CinemaUser cinemaUser) {
    return null;
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
