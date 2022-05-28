package io.lalahtalks.accounts.server.domain.user;

public record Password(String value) {

    @Override
    public String toString() {
        return "Password()";
    }

}
