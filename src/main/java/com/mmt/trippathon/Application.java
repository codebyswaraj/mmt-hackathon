package com.mmt.trippathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@PropertySource(value = { "application.properties" }, ignoreResourceNotFound = true)
public class Application extends SpringBootServletInitializer {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
