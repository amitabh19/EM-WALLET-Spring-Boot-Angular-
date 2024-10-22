package com.wallet.main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = "com.wallet.entities")
@EnableJpaRepositories(basePackages = {"com.wallet.repositories"})
@SpringBootApplication(scanBasePackages = {"com.wallet.controllers", "com.wallet.service", "com.wallet.dao"})
public class WalletBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletBackendApplication.class, args);
	}

}
