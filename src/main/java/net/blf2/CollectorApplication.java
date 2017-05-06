package net.blf2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.blf2.dao")
public class CollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollectorApplication.class, args);
	}
}
