package net.restapi.spring.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Sumit
 *
 */
@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@ComponentScan(basePackages = "net.restapi.spring")
public class AppConfig {

}