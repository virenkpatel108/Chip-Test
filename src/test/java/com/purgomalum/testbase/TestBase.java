package com.purgomalum.testbase;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
   @BeforeAll
   public static void inIT(){
        RestAssured.baseURI ="https://www.purgomalum.com/";
    }
}
