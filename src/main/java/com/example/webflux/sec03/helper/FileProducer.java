package com.example.webflux.sec03.helper;

import reactor.core.publisher.SynchronousSink;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.function.Consumer;

public class FileProducer implements Consumer<SynchronousSink<String>> {
    private SynchronousSink<String> sink;
    public static final Path path = Paths.get("src/main/resources/assignment/sec01");
    @Override
    public void accept(SynchronousSink<String> stringSynchronousSink) {
        sink = stringSynchronousSink;
    }

    public void produce() throws IOException {
        Scanner scanner = new Scanner(path.resolve("file1.txt"));
        sink.next(scanner.nextLine());
        scanner.close();
    }
}
