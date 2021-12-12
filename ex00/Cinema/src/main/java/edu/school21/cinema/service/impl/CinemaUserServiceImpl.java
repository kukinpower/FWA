package edu.school21.cinema.service.impl;

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
    return cinemaUser;
  }
}
