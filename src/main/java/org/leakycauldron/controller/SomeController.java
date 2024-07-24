package org.leakycauldron.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RestController()
public class SomeController {
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    @GetMapping(path = "/someEndpoint")
    public List<Integer> getList() {
        // This is just so we use the pool it and it's not optimized-out
        return IntStream.range(0, 10)
                .boxed()
                .map(x -> executor.submit(() -> x))
                .map(x -> {
                    try {
                        return x.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }
}
