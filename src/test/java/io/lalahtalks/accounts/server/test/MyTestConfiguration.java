package io.lalahtalks.accounts.server.test;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.Clock;
import java.time.ZoneId;

import static io.lalahtalks.accounts.server.test.DataInstant.NOW;

@TestConfiguration
public class MyTestConfiguration {

    @Bean
    @Primary
    public Clock clock() {
        return Clock.fixed(NOW, ZoneId.of("UTC"));
    }

}
