package io.lalahtalks.accounts.server.test;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = MyTestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWireMock(port = 0)
public class ContextAware {

    @Value("http://localhost:${local.server.port}/accounts")
    private String url;

    @BeforeEach
    public void setUpApi() {
        RestAssured.baseURI = url;
    }

}
