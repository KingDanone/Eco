package com.eco.projetoeco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class ProjetoecoApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ProjetoecoApplication.class);

    @Autowired
    private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoecoApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        logger.info("Resolved spring.datasource.url: {}", env.getProperty("spring.datasource.url"));
    }

}
