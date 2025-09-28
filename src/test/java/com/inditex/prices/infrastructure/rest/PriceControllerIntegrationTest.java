package com.inditex.prices.infrastructure.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    private static final String BASE_PATH = "/price";

    @Autowired
    private MockMvc mockMvc;

    static Stream<Arguments> requiredUseCases() {
        return Stream.of(
                arguments("1", "35455", "2020-06-14T10:00:00", 1, 35.50, "EUR"),
                arguments("1", "35455", "2020-06-14T16:00:00", 2, 25.45, "EUR"),
                arguments("1", "35455", "2020-06-14T21:00:00", 1, 35.50, "EUR"),
                arguments("1", "35455", "2020-06-15T10:00:00", 3, 30.50, "EUR"),
                arguments("1", "35455", "2020-06-16T21:00:00", 4, 38.95, "EUR")
        );
    }

    static Stream<Arguments> notFoundRequests() {
        return Stream.of(
                arguments("1", "35455", "2025-06-14T10:00:00")
        );
    }

    static Stream<Arguments> invalidParamRequests() {
        return Stream.of(
                arguments("xyz", "35455", "2020-06-14T10:00:00"),
                arguments("1", "abc", "2020-06-14T10:00:00"),
                arguments("1", "35455", "14-06-2020 10:00")
        );
    }

    @ParameterizedTest(name = "[{index}] Given required case date={2} When GET /price Then 200 priceList={3} price={4}")
    @MethodSource("requiredUseCases")
    void givenValidRequestParameters_whenGetPrice_thenReturnsExpectedPrice(String brandId,
                                                         String productId,
                                                         String date,
                                                         int expectedPriceList,
                                                         double expectedPrice,
                                                         String expectedCurrency) throws Exception {
        mockMvc.perform(get(BASE_PATH)
                        .param("brandId", brandId)
                        .param("productId", productId)
                        .param("date", date))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(Integer.parseInt(brandId)))
                .andExpect(jsonPath("$.productId").value(Integer.parseInt(productId)))
                .andExpect(jsonPath("$.priceList").value(expectedPriceList))
                .andExpect(jsonPath("$.price").value(expectedPrice))
                .andExpect(jsonPath("$.currency").value(expectedCurrency));
    }

    @ParameterizedTest(name = "[{index}] Given no price for date={2} When GET /price Then 404")
    @MethodSource("notFoundRequests")
    void givenDateOutsidePriceRanges_whenGetPrice_thenReturns404(String brandId,
                                                        String productId,
                                                        String date) throws Exception {
        mockMvc.perform(get(BASE_PATH)
                        .param("brandId", brandId)
                        .param("productId", productId)
                        .param("date", date))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest(name = "[{index}] Given invalid params brandId={0}, productId={1}, " +
            "date={2} When GET /price Then 400")
    @MethodSource("invalidParamRequests")
    void givenInvalidRequestParameters_whenGetPrice_thenReturns400(String brandId,
                                                     String productId,
                                                     String date) throws Exception {
        mockMvc.perform(get(BASE_PATH)
                        .param("brandId", brandId)
                        .param("productId", productId)
                        .param("date", date))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenMissingRequiredParameters_whenGetPrice_thenReturns400() throws Exception {
        mockMvc.perform(get(BASE_PATH)
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest());
    }
}
