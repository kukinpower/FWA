package edu.school21.cinema.service.impl;

import edu.school21.cinema.service.PasswordEncoderService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("passwordEncoderService")
public class PasswordEncoderServiceImpl implements PasswordEncoderService {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public PasswordEncoderServiceImpl() {
    this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
  }

  @Override
  public String encode(CharSequence rawPassword) {
    return bCryptPasswordEncoder.encode(rawPassword);
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
  }
}
