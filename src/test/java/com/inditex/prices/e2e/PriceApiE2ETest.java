package com.inditex.prices.e2e;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
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
    private int port;

    @BeforeAll
    static void setup() {
        RestAssured.config = RestAssured.config()
                .jsonConfig(io.restassured.config.JsonConfig.jsonConfig()
                        .numberReturnType(BIG_DECIMAL));
    }


    @Test
    // Test case 1: 2020-06-14 10:00 -> Expected priceList=1, price=35.50
    void testCase1_expectedPriceList1() {
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
                .body("price", comparesEqualTo(new BigDecimal("35.50")));
    }

    @Test
    // Test case 2: 2020-06-14 16:00 -> Expected priceList=2, price=25.45
    void testCase2_expectedPriceList2() {
        RestAssured.port = port;

        given()
                .queryParam("date", "2020-06-14T16:00:00")
                .queryParam("productId", 35455)
                .queryParam("brandId", 1)
                .when()
                .get("/price")
                .then()
                .statusCode(200)
                .body("priceList", equalTo(2))
                .body("price", comparesEqualTo(new BigDecimal("25.45")));
    }

    @Test
    // Test case 3: 2020-06-14 21:00 -> Expected priceList=1, price=35.50
    void testCase3_expectedPriceList1() {
        RestAssured.port = port;

        given()
                .queryParam("date", "2020-06-14T21:00:00")
                .queryParam("productId", 35455)
                .queryParam("brandId", 1)
                .when()
                .get("/price")
                .then()
                .statusCode(200)
                .body("brandId", equalTo(1))
                .body("productId", equalTo(35455))
                .body("priceList", equalTo(1))
                .body("price", comparesEqualTo(new BigDecimal("35.50")));
    }

    @Test
    // Test case 4: 2020-06-15 10:00 -> Expected priceList=3, price=30.50
    void testCase4_expectedPriceList3() {
        RestAssured.port = port;

        given()
                .queryParam("date", "2020-06-15T10:00:00")
                .queryParam("productId", 35455)
                .queryParam("brandId", 1)
                .when()
                .get("/price")
                .then()
                .statusCode(200)
                .body("priceList", equalTo(3))
                .body("price", comparesEqualTo(new BigDecimal("30.50")));
    }

    @Test
    // Test case 5: 2020-06-16 21:00 -> Expected priceList=4, price=38.95
    void testCase5_expectedPriceList4() {
        RestAssured.port = port;

        given()
                .queryParam("date", "2020-06-16T21:00:00")
                .queryParam("productId", 35455)
                .queryParam("brandId", 1)
                .when()
                .get("/price")
                .then()
                .statusCode(200)
                .body("priceList", equalTo(4))
                .body("price", comparesEqualTo(new BigDecimal("38.95")));
    }
}