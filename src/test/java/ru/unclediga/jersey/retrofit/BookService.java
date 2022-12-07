package ru.unclediga.jersey.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.unclediga.jersey.Book;

import java.util.List;

public interface BookService {

    @GET("books")
    Call<List<Book>> getBooks();
    @GET("books/{id}")
    Call<Book> getBook(@Path("id") int id);

}
