package com.tasks.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan(basePackages = { "com.tasks" })
@PropertySource("classpath:spring.properties")
@Import({ SpringMvcConfig.class,ThymeleafConfig.class,SpringSecurityConfig.class, SpringDataConfig.class })
public class AppConfig {

}
