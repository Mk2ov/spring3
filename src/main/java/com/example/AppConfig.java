package com.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy; // <--- Важливо
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.example")
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy
public class AppConfig {
}