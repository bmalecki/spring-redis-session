package com.example.one.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {

    private final AtomicLong counter = new AtomicLong();
    private static final String TEMPLATE = "Hello %s: %d";
    private String id;

    HelloController() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        id = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }


    @GetMapping(value = {"/", "/home"})
    public String home() {
        return "home: " + id;
    }

    @GetMapping("/resource")
    public String resource() {
        return "resource: " + id;
    }

}