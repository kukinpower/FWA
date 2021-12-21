package edu.school21.cinema.repository.impl;

import edu.school21.cinema.model.CinemaUser;
import edu.school21.cinema.model.mapper.CinemaUserMapper;
import edu.school21.cinema.repository.CinemaUserRepository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CinemaUserRepositoryImpl implements CinemaUserRepository {

  private final JdbcTemplate jdbcTemplate;

  private static final String FIND_BY_EMAIL_QUERY = "select * from cinema_users where email = ? limit 1";
  private static final String SAVE_USER_QUERY = "insert into cinema_users "
      + "(email, password, first_name, last_name, phone_number, image_filename)\n"
      + "values (?, ?, ?, ?, ?, ?);";
  private static final String UPDATE_USER_QUERY = "update cinema_users\n"
      + "set email=?, password=?, first_name=?, last_name=?, phone_number=?, image_filename=?\n"
      + "where user_id=?;";

  @Override
  public Optional<CinemaUser> save(CinemaUser cinemaUser) {
    GeneratedKeyHolder holder = new GeneratedKeyHolder();

    int update = jdbcTemplate.update(con -> {
      PreparedStatement statement = con.prepareStatement(SAVE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, cinemaUser.getEmail());
      statement.setString(2, cinemaUser.getPassword());
      statement.setString(3, cinemaUser.getFirstName());
      statement.setString(4, cinemaUser.getLastName());
      statement.setString(5, cinemaUser.getPhoneNumber());
      statement.setString(6, cinemaUser.getImageFilename());
      return statement;
    }, holder);


    long primaryKey = (Long)Objects.requireNonNull(holder.getKeys()).get("user_id");
    if (update == 0) {
      return Optional.empty();
    }
    cinemaUser.setUserId(primaryKey);

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

  @Override
  public Optional<CinemaUser> updateCinemaUser(CinemaUser cinemaUser) {
    GeneratedKeyHolder holder = new GeneratedKeyHolder();

    int update = jdbcTemplate.update(con -> {
      PreparedStatement statement = con.prepareStatement(UPDATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, cinemaUser.getEmail());
      statement.setString(2, cinemaUser.getPassword());
      statement.setString(3, cinemaUser.getFirstName());
      statement.setString(4, cinemaUser.getLastName());
      statement.setString(5, cinemaUser.getPhoneNumber());
      statement.setString(6, cinemaUser.getImageFilename());
      statement.setLong(7, cinemaUser.getUserId());
      return statement;
    }, holder);


    long primaryKey = (Long)Objects.requireNonNull(holder.getKeys()).get("user_id");
    if (update == 0) {
      return Optional.empty();
    }
    cinemaUser.setUserId(primaryKey);

    return Optional.of(cinemaUser);
  }
}
