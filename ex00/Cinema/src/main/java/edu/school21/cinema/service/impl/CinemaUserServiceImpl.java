package edu.school21.cinema.service.impl;

import edu.school21.cinema.exception.NoCinemaUserSavedException;
import edu.school21.cinema.model.CinemaUser;
import edu.school21.cinema.repository.CinemaUserRepository;
import edu.school21.cinema.service.CinemaUserService;
import edu.school21.cinema.service.PasswordEncoderService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("cinemaUserService")
@RequiredArgsConstructor
public class CinemaUserServiceImpl implements CinemaUserService {

  private final CinemaUserRepository cinemaUserRepository;
  private final PasswordEncoderService passwordEncoderService;

  @Override
  public CinemaUser save(CinemaUser cinemaUser) {
    return cinemaUserRepository.save(cinemaUser)
        .orElseThrow(NoCinemaUserSavedException::new);
  }

  @Override
  public Optional<CinemaUser> findByEmail(String emailToken) {
    return cinemaUserRepository.findByEmail(emailToken);
  }

  @Override
  public CinemaUser updateCinemaUser(CinemaUser cinemaUser) {
    return cinemaUserRepository.updateCinemaUser(cinemaUser)
        .orElseThrow(NoCinemaUserSavedException::new);
  }

  @Override
  public Optional<CinemaUser> signIn(String email, String password) {
    Optional<CinemaUser> cinemaUser = findByEmail(email);
    if (cinemaUser.isPresent()) {
      if (!passwordEncoderService.matches(password, cinemaUser.get().getPassword())) {
        return Optional.empty();
      }
    }
    return cinemaUser;
  }
}
