package ru.unclediga.jersey;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("books")
public class BooksResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public List<Book> getBooks() {
        return Repository.getBooks();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Book getBook(@PathParam("id") int id) {
        return Repository.getBook(id);
    }
}
