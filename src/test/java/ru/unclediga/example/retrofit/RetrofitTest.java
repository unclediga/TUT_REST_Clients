package ru.unclediga.example.retrofit;

import org.junit.BeforeClass;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.unclediga.example.MyResourceTest;
import ru.unclediga.jersey.Book;
import ru.unclediga.jersey.Main;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RetrofitTest extends MyResourceTest {
    private static BookService service;

    @BeforeClass
    public static void startUp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Main.BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(BookService.class);
    }

    @Test
    public void BookTest() throws IOException {
        Call<List<Book>> call = service.getBooks();
        final List<Book> books = call.execute().body();

        assertEquals("Books list size", 3, books.size());
    }

    @Test
    public void BookTest2() throws IOException {
        Call<Book> call = service.getBook(1);
        final Book book = call.execute().body();

        assertNotNull("Books list size", book);
        assertEquals("Books Id", 2, book.getId());
    }
}
