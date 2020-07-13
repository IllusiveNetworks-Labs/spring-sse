package com.team81.facade;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * Created by alon on 7/13/2020.
 */
public interface ISseFacade {

    void example(SseEmitter sseEmitter);
}
