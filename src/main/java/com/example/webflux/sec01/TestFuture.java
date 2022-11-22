package com.example.webflux.sec01;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class TestFuture {
    private static Future<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<String> completableFuture = calculateAsync();
        String result = completableFuture.get();
        log.info(result);

        Future<String> future = CompletableFuture.completedFuture("Hallo");
        System.out.println(future.get());
    }
}
