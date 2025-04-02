package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SoftwiumAPITesting {

    private static final String BASE_URL = "https://softwium.com/api";

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = BASE_URL;

    }

    @Test
    void testGetAllCurrencies() {

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/currencies");


        assertEquals(200, response.getStatusCode(), "Invalid response status code");

        response
                .then()
                .assertThat()
                .body("size()", equalTo(170));

        response.prettyPrint();
    }

    @Test
    void testGetCurrencyById() {
        // Replace '1' with an actual currency ID or test data.
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/currencies/{id}", 1)
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    void testCreateNewBook() {


        // Optional: Validate content, ensuring the response contains countries in the LIC income group
        RestAssured
                .given()
                .queryParam("format", "json")
                .when()
                .post("books")
                .then()
                .statusCode(201);


    }

    @Test
    void testDeleteBook() {


        // Optional: Validate content, ensuring the response contains countries in the LIC income group

        RestAssured
                .given()
                .queryParam("format", "json")
                .when()
                .delete("books/23")
                .then()
                .statusCode(200);
    }
}