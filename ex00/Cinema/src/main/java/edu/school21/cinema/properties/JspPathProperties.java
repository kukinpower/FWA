package edu.school21.cinema.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@NoArgsConstructor
@Configuration
public class JspPathProperties {

  @Value("${jsp.path.sign-up}")
  private String signUp;

  @Value("${jsp.path.sign-in}")
  private String signIn;

  @Value("${jsp.path.profile}")
  private String profile;

  @Value("${jsp.path.home}")
  private String home;

  @Value("${jsp.path.default-image}")
  private String defaultImage;
}
