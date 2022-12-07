package ru.unclediga.jersey.feign;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import ru.unclediga.jersey.Book;
import ru.unclediga.jersey.Repository;

import java.util.List;

public class FeignTest {
    BookClient bookClient = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(BookClient.class))
            .logLevel(Logger.Level.FULL)
            .target(BookClient.class, "http://localhost:8081/books");

    @Test
    public void getAllBooksTest() {
        List<Book> books = bookClient.findAll();
        assertEquals(books.size(), 3);
    }

    @Test
    public void getBook2Test() {
        Book book = bookClient.findById(1);
        Assert.assertEquals(book.getId(), Repository.getBook(1).getId());
    }
}
