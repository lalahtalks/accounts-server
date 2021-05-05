package io.lalahtalks.accounts.server.http.api;

import io.lalahtalks.spring.server.security.HttpSecurityAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.stereotype.Component;

@Component
public class MyHttpSecurityAdapter implements HttpSecurityAdapter {

    @Override
    public ServerHttpSecurity configure(ServerHttpSecurity http) {
        return http
                .authorizeExchange()
                .anyExchange().hasAnyAuthority("ROLE_admin", "SCOPE_all-mighty")
                .and();
    }

}
