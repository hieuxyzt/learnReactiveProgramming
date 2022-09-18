package com.example.webflux.sec01;

import com.github.javafaker.Faker;

public class FakeDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(
                    Faker.instance().name().fullName()
            );
        }
    }
}
