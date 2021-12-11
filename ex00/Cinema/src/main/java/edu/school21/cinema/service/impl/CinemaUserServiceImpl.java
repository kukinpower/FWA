package edu.school21.cinema.service.impl;

import edu.school21.cinema.model.CinemaUser;
import edu.school21.cinema.repository.CinemaUserRepository;
import edu.school21.cinema.service.CinemaUserService;
import org.springframework.stereotype.Service;

@Service("cinemaUserService")
public class CinemaUserServiceImpl implements CinemaUserService {

//  public CinemaUserRepository repository;

  @Override
  public CinemaUser save(CinemaUser cinemaUser) {
    return cinemaUser;
  }
}
