package edu.school21.cinema.repository.impl;

import edu.school21.cinema.repository.CinemaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CinemaUserRepositoryImpl implements CinemaUserRepository {

  private final JdbcTemplate jdbcTemplate;
}
