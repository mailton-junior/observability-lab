package com.example.obs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class ObservabilityController {

    private final Random random = new Random();

    @GetMapping("/fast")
    public String fast() {
        return "Fast response";
    }

    @GetMapping("/io")
    public String io() throws InterruptedException {
        int sleepTime = 100 + random.nextInt(401); // 100-500ms
        Thread.sleep(sleepTime);
        return "Simulated IO latency: " + sleepTime + "ms";
    }

    @GetMapping("/cpu")
    public String cpu() {
        long result = 0;
        for (int i = 0; i < 1_000_000; i++) {
            result += i * i;
        }
        return "CPU-bound operation result: " + result;
    }

    @GetMapping("/unstable")
    public String unstable() {
        if (random.nextDouble() < 0.2) { // 20% chance of failure
            throw new RuntimeException("Simulated unstable endpoint failure");
        }
        return "Unstable endpoint succeeded";
    }
}
