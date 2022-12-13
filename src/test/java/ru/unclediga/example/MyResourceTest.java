package ru.unclediga.example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import ru.unclediga.jersey.Main;

public class MyResourceTest {

    private static HttpServer server;

    @BeforeClass
    public static void setUp() throws Exception {
        // start the server
        server = Main.startServer();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        server.stop();
    }
}
