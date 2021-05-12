package io.lalahtalks.accounts.server.test;

import lombok.NoArgsConstructor;

import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DataInstant {

    public static final Instant NOW = Instant.parse("2012-12-21T12:00:00Z");

}
