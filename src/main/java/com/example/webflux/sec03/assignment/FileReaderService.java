package com.example.webflux.sec03.assignment;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@Slf4j
public class FileReaderService {

    private static Callable<BufferedReader> openReader(Path path){
        return () -> Files.newBufferedReader(path);
    }

    private static BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> read() {
        return (br, sink) -> {
            try {
                String line = br.readLine();
                if(Objects.isNull(line)) {
                    sink.complete();
                } else {
                    sink.next(line);
                }
            } catch (IOException e) {
                sink.error(e);
            }
            return br;
        };
    }

    private static Consumer<BufferedReader> closeReader() {
        return br -> {
            try {
                br.close();
                log.debug("---close reader---");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static Flux<String> read(Path path) {
        return Flux.generate(
                openReader(path),
                read(),
                closeReader()
                );
    }
}
