package edu.school21.cinema.service.impl;

import edu.school21.cinema.service.InitDbService;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InitDbServiceImpl implements InitDbService {

  private final JdbcTemplate jdbcTemplate;

  @PostConstruct
  public void init() {
    runSqlScript("sql/schema.sql");
    runSqlScript("sql/data.sql");
  }

  private void runSqlScript(String pathToScript) {
    try {
      Optional<InputStream> resourceAsStream = Optional.ofNullable(
          InitDbServiceImpl.class.getClassLoader()
              .getResourceAsStream(pathToScript));

      resourceAsStream.ifPresent(inputStream ->
          jdbcTemplate.execute(
              new BufferedReader(
                  new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                  .lines()
                  .collect(Collectors.joining("\n"))));
    } catch (Exception e) {
      log.error("Couldn't run script: " + pathToScript);
    }
  }
}
