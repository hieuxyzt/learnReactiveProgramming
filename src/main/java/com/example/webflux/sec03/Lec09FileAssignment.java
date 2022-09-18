package com.example.webflux.sec03;

import com.example.webflux.courseUtil.Util;
import com.example.webflux.sec03.assignment.FileReaderService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Lec09FileAssignment {
    static Path path = Paths.get("src/main/resources/assignment/sec03/");

    public static void main(String[] args) throws IOException {
        String fileName = "file1.txt";
        FileReaderService
                .read(path.resolve(fileName))
                .log()
                .map(s -> {
                    if(Util.faker().random().nextInt(0, 10) > 5)
                        throw new RuntimeException("opps");
                    return s;
                })
                .take(10)
                .subscribe(Util.subscriber());
    }

}
