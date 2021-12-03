package edu.school21.cinema.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CinemaUser {

  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String password;
}
