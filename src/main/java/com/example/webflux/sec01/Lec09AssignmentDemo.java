package com.example.webflux.sec01;

import com.example.webflux.courseUtil.Util;
import com.example.webflux.sec01.assignment.FileService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec09AssignmentDemo {
    public static void main(String[] args) {
        String content = "hello ola";

//        FileService.write("file1.txt", content.getBytes())
//                .subscribe(Util.onNext(),
//                        Util.onError(),
//                        Util.onComplete("Write file completed"));
//
//        FileService.write("file2.txt", content.getBytes())
//                .subscribe(Util.onNext(),
//                        Util.onError(),
//                        Util.onComplete("Write file completed"));


        FileService.read("file2.txt")
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete("Read file completed"));

        FileService.delete("file4.txt")
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete("Delete file complete"));
    }
}
