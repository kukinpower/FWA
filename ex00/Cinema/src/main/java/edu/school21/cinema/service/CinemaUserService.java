package edu.school21.cinema.service;

import edu.school21.cinema.model.CinemaUser;
import java.util.Optional;

public interface CinemaUserService {

  CinemaUser save(CinemaUser cinemaUser);

  Optional<CinemaUser> findByEmail(String emailToken);

  CinemaUser updateCinemaUser(CinemaUser cinemaUser);

  Optional<CinemaUser> signIn(String email, String password);
}
