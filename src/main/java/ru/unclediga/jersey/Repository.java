package ru.unclediga.jersey;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static final List<Book> books = new ArrayList<>(10);
    private static final List<Library> libs = new ArrayList<>(10);
    static{

        books.add(new Book(1,"Book 1"));
        books.add(new Book(2,"Book 2"));
        books.add(new Book(3,"Book 3"));

        libs.add(new Library(1,"Library 1"));
        libs.add(new Library(2,"Library 2"));
        libs.add(new Library(3,"Library 3"));
    }

    public static List<Book> getBooks() {
        return books;
    }

    public static Book getBook(int id) {
        return books.get(id);
    }

    public static Library getLibrary(int id) {
        return libs.get(id);
    }

    public static List<Library> getLibraries() {
        return libs;
    }
    
    
}