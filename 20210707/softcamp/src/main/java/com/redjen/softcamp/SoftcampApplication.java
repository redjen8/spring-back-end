package com.redjen.softcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.redjen.softcamp.controller", "com.redjen.softcamp.domain", "com.redjen.softcamp.repository", "com.redjen.softcamp.service"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SoftcampApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftcampApplication.class, args);
	}
}
