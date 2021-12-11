package edu.school21.cinema.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("edu.school21.cinema")
@PropertySource("classpath:../application.properties")
public class AppConfig {

}
