package com.wallet.main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = "com.wallet.entities")
@EnableJpaRepositories(basePackages = {"com.wallet.repositories","com.wallet.dao"})
@ComponentScan(basePackages = {"com.wallet.controllers", "com.wallet.service", "com.wallet.dao"})
@SpringBootApplication()
public class WalletBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletBackendApplication.class, args);
	}

}
