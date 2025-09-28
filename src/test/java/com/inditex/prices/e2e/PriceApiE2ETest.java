package com.inditex.prices.e2e;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceApiE2ETest {

    @LocalServerPort
    int port;

    @BeforeAll
    static void globalSetup() {
        RestAssured.config = RestAssured.config()
                .jsonConfig(io.restassured.config.JsonConfig.jsonConfig()
                        .numberReturnType(BIG_DECIMAL));
    }

    @Test
    @DisplayName("E2E - Minimal happy path (pricing rule collision)")
    void givenValidRequest_whenGetPrice_then200AndBody() {
        RestAssured.port = port;

        given()
                .queryParam("date", "2020-06-14T10:00:00")
                .queryParam("productId", 35455)
                .queryParam("brandId", 1)
                .when()
                .get("/price")
                .then()
                .statusCode(200)
                .body("brandId", equalTo(1))
                .body("productId", equalTo(35455))
                .body("priceList", equalTo(1))
                .body("price", comparesEqualTo(new BigDecimal("35.50")))
                .body("currency", equalTo("EUR"));
    }

    @Test
    @DisplayName("E2E - 404 when no price applies")
    void givenNoApplicablePrice_whenGetPrice_then404() {
        RestAssured.port = port;

        given()
                .queryParam("date", "2025-06-14T10:00:00")
                .queryParam("productId", 35455)
                .queryParam("brandId", 1)
                .when()
                .get("/price")
                .then()
                .statusCode(404);
    }
}