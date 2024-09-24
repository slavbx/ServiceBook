package org.slavbx.servicebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class }) //Пока не настроен datasource, чтобы проект запустился
public class ServiceBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceBookApplication.class, args);
	}

}
