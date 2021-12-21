package edu.school21.cinema.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Getter
@Setter
@NoArgsConstructor
@Configuration
@Primary
public class DataSourceProperties {

  @Value("${datasource.url}")
  private String url;

  @Value("${datasource.username}")
  private String username;

  @Value("${datasource.password}")
  private String password;

  @Value("${datasource.driver-class-name}")
  private String driverClassName;

  @Value("${datasource.connection-timeout}")
  private Long connectionTimeout;
}
