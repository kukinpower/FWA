package edu.school21.cinema.service.impl;

import edu.school21.cinema.exception.NoCinemaUserSavedException;
import edu.school21.cinema.model.CinemaUser;
import edu.school21.cinema.repository.CinemaUserRepository;
import edu.school21.cinema.service.CinemaUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("cinemaUserService")
@RequiredArgsConstructor
public class CinemaUserServiceImpl implements CinemaUserService {

  private final CinemaUserRepository cinemaUserRepository;

  @Override
  public CinemaUser save(CinemaUser cinemaUser) {
    return cinemaUserRepository.save(cinemaUser)
        .orElseThrow(NoCinemaUserSavedException::new);
  }

  @Override
  public CinemaUser findByEmail(String emailToken) {
    return cinemaUserRepository.findByEmail(emailToken)
        .orElseThrow(NoCinemaUserSavedException::new);
  }

  @Override
  public CinemaUser updateCinemaUser(CinemaUser cinemaUser) {
    return cinemaUserRepository.updateCinemaUser(cinemaUser)
        .orElseThrow(NoCinemaUserSavedException::new);
  }
}
