package io.lalahtalks.accounts.server;

import io.lalahtalks.accounts.client.dto.AccountDto;
import io.lalahtalks.accounts.client.http.contract.problem.AccountNotFoundProblem;
import io.lalahtalks.accounts.server.test.ContextAware;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import static io.lalahtalks.accounts.client.http.contract.AccountsHttpPaths.ACCOUNT_ID;
import static io.lalahtalks.accounts.client.http.contract.AccountsHttpPaths.ACCOUNT_PATH;
import static io.lalahtalks.accounts.server.test.DataAccessToken.ALL_MIGHTY;
import static io.lalahtalks.accounts.server.test.DataAccessToken.NOBODY;
import static io.lalahtalks.accounts.server.test.DataAccount.ACCOUNT_1_DTO;
import static io.lalahtalks.accounts.server.test.DataAccount.ACCOUNT_1_ID_VALUE;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

class GetAccountTest extends ContextAware {

    @Test
    @Sql({"/sql/clean.sql", "/sql/insert.sql"})
    void it_works() {
        var response = given()
                .auth().preemptive().oauth2(ALL_MIGHTY)
                .with().accept(MediaType.APPLICATION_JSON_VALUE)
                .pathParam(ACCOUNT_ID, ACCOUNT_1_ID_VALUE)
                .get(ACCOUNT_PATH)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().as(AccountDto.class);

        assertThat(response).isEqualTo(ACCOUNT_1_DTO);
    }

    @Test
    @Sql("/sql/clean.sql")
    void account_not_found() {
        var response = given()
                .auth().preemptive().oauth2(ALL_MIGHTY)
                .with().accept(MediaType.APPLICATION_JSON_VALUE)
                .pathParam(ACCOUNT_ID, ACCOUNT_1_ID_VALUE)
                .get(ACCOUNT_PATH)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract()
                .as(AccountNotFoundProblem.class);

        assertThat(response.getDetail()).isEqualTo("account ID 'account_1'");
    }

    @Test
    void forbidden() {
        var response = given()
                .auth().preemptive().oauth2(NOBODY)
                .with().accept(MediaType.APPLICATION_JSON_VALUE)
                .pathParam(ACCOUNT_ID, ACCOUNT_1_ID_VALUE)
                .get(ACCOUNT_PATH)
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .extract()
                .as(Problem.class);

        assertThat(response.getStatus()).isEqualTo(Status.FORBIDDEN);
    }

}
