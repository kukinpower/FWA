package edu.school21.cinema.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CinemaUser {

  private long userId;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String email;
  private String password;
  private String imageFilename;

  public CinemaUser(String firstName, String lastName, String phoneNumber, String email,
      String password, String imageFilename) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.password = password;
    this.imageFilename = imageFilename;
  }
}
