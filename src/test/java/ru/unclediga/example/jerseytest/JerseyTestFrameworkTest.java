package ru.unclediga.example.jerseytest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;
import ru.unclediga.jersey.Book;
import ru.unclediga.jersey.BooksResource;
import ru.unclediga.jersey.MyResource;
import ru.unclediga.jersey.Repository;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class JerseyTestFrameworkTest extends JerseyTest {
    /*
    ...
    INFO: Creating GrizzlyTestContainer configured at the base URI http://localhost:9998/
    дек 13, 2022 1:53:24 PM org.glassfish.grizzly.http.server.NetworkListener start
    INFO: Started listener bound to [localhost:9998]
    дек 13, 2022 1:53:24 PM org.glassfish.grizzly.http.server.HttpServer start
    INFO: [HttpServer] Started.
    ...
     */
    @Override
    protected Application configure() {
        return new ResourceConfig(MyResource.class, BooksResource.class);
    }

    @Test
    public void givenGotIt() {
        Response response = target("/myresource").request()
                .get();

        assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.TEXT_PLAIN, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        String content = response.readEntity(String.class);
        assertEquals("Content of response is: ", "Got it!", content);
    }

    @Test
    public void givenBook2() {
        Response response = target("/books/1").request()
                .get();

        assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        Book content = response.readEntity(Book.class);
        Assert.assertEquals("Content of response is: ", Repository.getBook(1), content);
    }
}
