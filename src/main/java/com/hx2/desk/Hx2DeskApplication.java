package com.hx2.desk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
//@EnableTransactionManagement
@ComponentScan("com.hx2.desk")
public class Hx2DeskApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Hx2DeskApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Hx2DeskApplication.class, args);
	}
}
