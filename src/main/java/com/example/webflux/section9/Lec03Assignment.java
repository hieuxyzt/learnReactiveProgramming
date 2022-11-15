package com.example.webflux.section9;

import com.example.webflux.courseUtil.Util;
import com.example.webflux.section9.helper.BookOrder;
import com.example.webflux.section9.helper.BookRevenue;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Lec03Assignment {
    public static void main(String[] args) {
        Set<String> allowedCategories = Set.of(
                "Science fiction",
                "Fantasy",
                "Suspense/Thriller",
                "Humor",
                "Fiction narrative"
        );
        bookStream()
                .filter(bookOrder -> allowedCategories.contains(bookOrder.getCategory()))
                .buffer(Duration.ofSeconds(3))
                .map(Lec03Assignment::revenueCalculator)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static BookRevenue revenueCalculator(List<BookOrder> books) {
        Map<String, Double> map = books.stream()
                .collect(Collectors.groupingBy(
                        BookOrder::getCategory,
                        Collectors.summingDouble(BookOrder::getPrice)
                ));
        return new BookRevenue(map);
    }

    private static Flux<BookOrder> bookStream() {
        return Flux.interval(Duration.ofMillis(10))
                .map(i -> new BookOrder());
    }
}
