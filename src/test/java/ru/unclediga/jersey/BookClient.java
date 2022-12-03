package ru.unclediga.jersey;

import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface BookClient {
    @RequestLine("GET")
    List<Book> findAll();

    @RequestLine("GET /{id}")
    Book findById(@Param("id") int id);
}
