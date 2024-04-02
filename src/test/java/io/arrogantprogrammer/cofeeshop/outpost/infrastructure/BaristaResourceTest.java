package io.arrogantprogrammer.cofeeshop.outpost.infrastructure;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

@QuarkusTest
public class BaristaResourceTest {
    static final Logger LOGGER = LoggerFactory.getLogger(BaristaResourceTest.class);

    String json = """
            {
                "uuid": "123e4567-e89b-12d3-a456-426614174000",
                "item": "COFFEE",
                "name": "Larry"
            }
            """;

    @Test
    public void testMakeCoffee() {
        LOGGER.info("Testing makeCoffee method");
        given()
                .body(json)
                .header("Content-Type", "application/json")
                .when()
                .post("/make")
                .then()
                .statusCode(201);
    }
}
