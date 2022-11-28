package ru.unclediga.jersey;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static final List<Book> list = new ArrayList<>(10);
    static{

        list.add(new Book(1,"Book 1"));
        list.add(new Book(2,"Book 2"));
        list.add(new Book(3,"Book 3"));
    }

    public static List<Book> getBooks() {
        return list;
    }

    public static Book getBook(int id) {
        return list.get(id);
    }
    
    
}