package com.example.webflux.section9.helper;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class BookOrder {
    private String category;
    private String title;
    private String author;
    private Double price;


    public BookOrder() {
        Book book = Faker.instance().book();
        this.category = book.genre();
        this.title = book.title();
        this.author = book.author();
        this.price = Double.valueOf(Faker.instance().random().nextInt(100, 200));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(new BookOrder().getCategory());

        }
    }
}
