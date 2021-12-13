package edu.school21.cinema.repository;

import edu.school21.cinema.model.CinemaUser;
import java.util.Optional;

public interface CinemaUserRepository {

  Optional<CinemaUser> save(CinemaUser cinemaUser);

  Optional<CinemaUser> findByEmail(String email);
}
