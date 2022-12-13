package ru.unclediga.example.restassured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.Before;
import ru.unclediga.example.MyResourceTest;
import ru.unclediga.jersey.Main;

public class TestRESTAssured extends MyResourceTest {
    @Before
    public void setup() {
        baseURI = Main.BASE_URI;
        //port = 8081;
        // javadocs ----------
        // public static int port
        //   The port that's used by REST assured when it's left out of the specified URI
        //   when making a request. Default port will evaluate to 8080.

    }

    @Test
    public void test1(){
         when()
        .request("GET", "/myresource")
        .then()
        .statusCode(200)
        .body(hasToString("Got it!"));
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
