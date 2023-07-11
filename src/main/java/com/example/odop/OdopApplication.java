package com.example.odop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "com.example.entity" })
@EnableJpaRepositories(basePackages = {
		"com.example.repository.AR", "com.example.repository.GR", "com.example.repository.MH",
		"com.example.repository.SG", "com.example.repository.WJ"
})
@MapperScan(basePackages = {
		"com.example.mapper.AR", "com.example.mapper.GR", "com.example.mapper.MH",
		"com.example.mapper.SG", "com.example.mapper.WJ"
})
@ComponentScan(basePackages = {
		"com.example.service.AR", "com.example.service.GR", "com.example.service.MH",
		"com.example.service.SG", "com.example.service.WJ", "com.example.service",
		"com.example.controller",
		"com.example.controller.AR", "com.example.controller.GR", "com.example.controller.MH",
		"com.example.controller.SG", "com.example.controller.WJ",
		"com.example.restcontroller.AR", "com.example.restcontroller.GR", "com.example.restcontroller.MH",
		"com.example.restcontroller.SG", "com.example.restcontroller.WJ",
		"com.example.config", "com.example.filter"
})
public class OdopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OdopApplication.class, args);
	}

}
