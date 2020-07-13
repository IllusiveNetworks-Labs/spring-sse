package com.team81.facade.impl;

import com.team81.facade.ISseFacade;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by alon on 7/13/2020.
 */
@Service
public class SseFacade implements ISseFacade {

    @Override
    public void test(SseEmitter sseEmitter) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            for (int i = 0; i < 100; ++i) {
                try {
                    sseEmitter.send(String.format("test: %d", i), MediaType.APPLICATION_JSON);
                    Thread.sleep(1000);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(String.format("Failed to send test: %d", i));
                }
            }
        });
    }
}