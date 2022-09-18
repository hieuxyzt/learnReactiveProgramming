package com.example.webflux.sec01.assignment;

import com.example.webflux.courseUtil.Util;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor
public class FileService {
    public static final Logger log = LoggerFactory.getLogger(FileService.class);
    static Path path = Paths.get("src/main/resources/assignment/sec01/");

    public static Mono<String> read(String name) {
        return Mono.fromSupplier(() -> {
            try {
                return readFile(name);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static Mono<Void> write(String name, byte[] content) {
        return Mono.fromRunnable(() -> {
            try {
                writeFile(name, content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static Mono<Void> delete(String name) {
        return Mono.fromRunnable(() -> {
            deleteFile(name);
        });
    }


    private static String readFile(String name) throws IOException {
        log.info("In Read");
//        Util.sleepSeconds(1);
        return Files.readString(path.resolve(name));
    }

    private static void writeFile(String name, byte[] content) throws IOException {
        log.info("In Write");
//        Util.sleepSeconds(1);
        Files.write(path.resolve(name), content);
    }

    private static void deleteFile(String name) {
        log.info("In Delete");
        try {
            Files.delete(path.resolve(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
