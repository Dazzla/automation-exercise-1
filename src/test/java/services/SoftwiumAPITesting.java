package services;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SoftwiumAPITesting {

    private static final String BASE_URL = "https://softwium.com/api";
    private static RequestSpecification jsonRequest;

    @BeforeAll
    static void setup() {
        jsonRequest = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    @Test
    void testGetAllCurrencies() {
        Response response = RestAssured.given()
                .spec(jsonRequest)
                .when()
                .get("/currencies");

        assertEquals(200, response.getStatusCode(), "Unexpected response status");

        List<String> codes = response.jsonPath().getList("code");
        assertFalse(codes.isEmpty(), "Currency codes should not be empty");
        assertTrue(codes.contains("USD"), "Currency list should include USD");
    }

    @Test
    void testGetCurrencyById() {
        RestAssured.given()
                .spec(jsonRequest)
                .pathParam("id", 1)
                .when()
                .get("/currencies/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    void testCreateNewBook() {
        RestAssured.given()
                .spec(jsonRequest)
                .queryParam("format", "json")
                .when()
                .post("/books")
                .then()
                .statusCode(201);
    }

    @Test
    void testDeleteBook() {
        RestAssured.given()
                .spec(jsonRequest)
                .queryParam("format", "json")
                .when()
                .delete("/books/{id}", 23)
                .then()
                .statusCode(200);
    }
}
