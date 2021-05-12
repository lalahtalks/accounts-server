package io.lalahtalks.accounts.server;

import io.lalahtalks.accounts.client.dto.AccountCreatedDto;
import io.lalahtalks.accounts.client.dto.AccountCreationRequestDto;
import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.account.Account;
import io.lalahtalks.accounts.server.domain.account.AccountId;
import io.lalahtalks.accounts.server.domain.account.AccountRepository;
import io.lalahtalks.accounts.server.test.ContextAware;
import io.lalahtalks.accounts.server.test.DataAccessToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static io.lalahtalks.accounts.server.test.DataInstant.NOW;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

class AccountCreationTest extends ContextAware {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Sql("/sql/clean.sql")
    void with_all_mighty() {
        var request = AccountCreationRequestDto.builder()
                .email("test1@test.com")
                .password("passwd")
                .build();

        var response = given()
                .auth().preemptive().oauth2(DataAccessToken.ALL_MIGHTY)
                .with().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .post("/accounts")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().as(AccountCreatedDto.class);

        var expectedResponse = AccountCreatedDto.builder()
                .accountId("user_1")
                .createdAt(NOW)
                .build();
        assertThat(response).isEqualTo(expectedResponse);

        var created = accountRepository.find(new AccountId("user_1"));
        var expectedCreated = Account.builder()
                .id(new AccountId("user_1"))
                .email(new Email("test1@test.com"))
                .createdAt(NOW)
                .build();
        assertThat(created).hasValue(expectedCreated);
    }

}
