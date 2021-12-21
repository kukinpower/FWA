package edu.school21.cinema.service;

public interface PasswordEncoderService {


  String encode(CharSequence rawPassword);

  boolean matches(CharSequence rawPassword, String encodedPassword);
}
