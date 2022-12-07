package ru.unclediga.jersey.feign;

import feign.Param;
import feign.RequestLine;
import ru.unclediga.jersey.Book;

import java.util.List;

public interface BookClient {
    @RequestLine("GET")
    List<Book> findAll();

    @RequestLine("GET /{id}")
    Book findById(@Param("id") int id);
}
