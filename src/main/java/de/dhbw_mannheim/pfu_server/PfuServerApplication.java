package de.dhbw_mannheim.pfu_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PfuServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfuServerApplication.class, args);
	}

}
