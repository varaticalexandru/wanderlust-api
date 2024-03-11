package org.alexv.wanderlustapi;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WanderlustApiApplication {

	public static void main(String[] args) {
		laodEnvVars();
		SpringApplication.run(WanderlustApiApplication.class, args);
	}

	public static void laodEnvVars() {
		Dotenv dotenv = Dotenv.load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
	}
}
