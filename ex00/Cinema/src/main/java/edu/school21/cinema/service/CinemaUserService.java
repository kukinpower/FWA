package edu.school21.cinema.service;

import edu.school21.cinema.model.CinemaUser;

public interface CinemaUserService {

  CinemaUser save(CinemaUser cinemaUser);

  CinemaUser findByEmail(String emailToken);

  CinemaUser updateCinemaUser(CinemaUser cinemaUser);
}
