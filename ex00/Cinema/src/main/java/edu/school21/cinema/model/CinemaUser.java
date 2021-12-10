package edu.school21.cinema.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CinemaUser {

  private long id;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String password;
}
