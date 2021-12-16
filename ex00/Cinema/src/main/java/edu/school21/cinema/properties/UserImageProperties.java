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
public class UserImageProperties {

  @Value("${user-image.default-image}")
  private String defaultImage;
}
