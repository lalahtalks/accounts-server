package io.lalahtalks.accounts.server;

import io.lalahtalks.accounts.client.dto.AccountCreatedDto;
import io.lalahtalks.accounts.server.domain.account.AccountRepository;
import io.lalahtalks.accounts.server.test.ContextAware;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import static io.lalahtalks.accounts.client.http.contract.AccountsHttpPaths.ACCOUNTS_PATH;
import static io.lalahtalks.accounts.server.test.DataAccessToken.ALL_MIGHTY;
import static io.lalahtalks.accounts.server.test.DataAccessToken.NOBODY;
import static io.lalahtalks.accounts.server.test.DataAccount.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

class AccountCreationTest extends ContextAware {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Sql("/sql/clean.sql")
    void it_works() {
        var response = given()
                .auth().preemptive().oauth2(ALL_MIGHTY)
                .with().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(ACCOUNT_CREATION_REQUEST_DTO)
                .post(ACCOUNTS_PATH)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(AccountCreatedDto.class);

        assertThat(response).isEqualTo(ACCOUNT_CREATED_DTO);

        var created = accountRepository.find(ACCOUNT_1_ID);
        assertThat(created).hasValue(ACCOUNT_1);
    }

    @Test
    @Sql({"/sql/clean.sql", "/sql/insert.sql"})
    void account_already_exists() {
        var response = given()
                .auth().preemptive().oauth2(ALL_MIGHTY)
                .with().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(ACCOUNT_CREATION_REQUEST_DTO)
                .post(ACCOUNTS_PATH)
                .then()
                .statusCode(HttpStatus.CONFLICT.value())
                .extract().as(Problem.class);

        assertThat(response.getType()).hasToString("urn:lalahtalks:problem:accounts:account-already-exists");
        assertThat(response.getTitle()).isEqualTo("Account already exists");
        assertThat(response.getStatus()).isEqualTo(Status.CONFLICT);
    }

    @Test
    void forbidden() {
        var response = given()
                .auth().preemptive().oauth2(NOBODY)
                .with().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(ACCOUNT_CREATION_REQUEST_DTO)
                .post(ACCOUNTS_PATH)
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .extract().as(Problem.class);

        assertThat(response.getStatus()).isEqualTo(Status.FORBIDDEN);
    }

}
