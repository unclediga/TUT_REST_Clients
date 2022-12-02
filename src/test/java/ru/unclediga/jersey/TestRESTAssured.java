package ru.unclediga.jersey;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.Before;

public class TestRESTAssured extends MyResourceTest{
    @Before
    public void setup() {
        baseURI = "http://localhost";
        port = 8081;
    }

    @Test
    public void test1(){
         when()
        .request("GET", "/myresource")
        .then()
        .statusCode(200)
        .body("", equalTo("Got it!"));
    }

    @Test
    public void test2(){
         given()
        .header("Accept", MediaType.APPLICATION_JSON)
        .pathParam("id","1")
        .get("/libs/{id}")
        .then()
        .log()
        .body()
        .statusCode(200)
        .assertThat()
        .body("id",equalTo(2));
    }
}
