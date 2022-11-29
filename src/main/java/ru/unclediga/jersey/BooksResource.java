package ru.unclediga.jersey;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("books")
public class BooksResource {

    @GET
    public List<Book> getBooks() {
        return Repository.getBooks();
    }

    @GET
    @Path("/{id}")
    public Book getBook(@PathParam("id") int id) {
        return Repository.getBook(id);
    }
}
