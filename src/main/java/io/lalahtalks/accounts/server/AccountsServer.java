package io.lalahtalks.accounts.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@SpringBootApplication
public class AccountsServer {

    public static void main(String[] args) {
        SpringApplication.run(AccountsServer.class, args);
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

}
