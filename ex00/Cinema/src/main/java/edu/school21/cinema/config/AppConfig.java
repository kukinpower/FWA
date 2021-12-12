package edu.school21.cinema.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.cinema.properties.DataSourceProperties;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan("edu.school21.cinema")
@PropertySource("classpath:../application.properties")
@RequiredArgsConstructor
public class AppConfig {

  private final DataSourceProperties dataSourceProperties;

  @Bean
  public DataSource dataSource() {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl(dataSourceProperties.getUrl());
    hikariConfig.setUsername(dataSourceProperties.getUsername());
    hikariConfig.setPassword(dataSourceProperties.getPassword());
    hikariConfig.setDriverClassName(dataSourceProperties.getDriverClassName());
    hikariConfig.setConnectionTimeout(dataSourceProperties.getConnectionTimeout());

    return new HikariDataSource(hikariConfig);
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
