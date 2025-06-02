package com.purgomalum.methods;

import com.purgomalum.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

public class ProfanityTest extends TestBase {
    @Test
    public void shouldCensorProfanity() {

        Response response = given()
                .queryParam("text", "Ohh goddamnit")
                .when()
                .get("/service/json");

        //check status code
        response.then().statusCode(200);

        // Assert the 'result' does NOT contain the word "goddamnit"
        response.then().body("result", not(containsString("goddamnit")));
        response.prettyPrint();
    }

    @Test
    public void shouldCensorBlacklistedWord() {
        Response response = given()
                .queryParam("text", "apple keep away doctor")
                .queryParam("add", "apple")
                .when()
                .get("/service/json");

        response.then().statusCode(200);

        // Extract result field from JSON
        String result = response.jsonPath().getString("result");

        // Assert that 'apple' was censored
        assert !result.contains("apple") : "Expected 'apple' to be censored in the result";

        response.prettyPrint();
    }

    @Test
    public void shouldReplaceWithFillText() {
        Response response = given()
                .queryParam("text", "This is wop sentence")
                .queryParam("fill_text", "[BLOCKED]")
                .when()
                .get("/service/json");
        response.then().statusCode(200);

        String result = response.jsonPath().getString("result");

        // Assert that "[BLOCKED]" appears in the censored result
        assert result.contains("[BLOCKED]") : "Expected '[BLOCKED]' replacement not found in result";
        response.prettyPrint();
    }

    @Test
    public void shouldReplaceWithFillCharacter() {
        Response response = given()
                .queryParam("text", "This is wop sentence")
                .queryParam("fill_char", "*")
                .when()
                .get("/service/json");

        response.then().statusCode(200);

        String result = response.jsonPath().getString("result");

        // Assert that "***" appears in the censored result
        assert result.contains("***") : "Expected '***' replacement not found in result";
        response.prettyPrint();
    }
}