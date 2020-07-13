package com.team81.controller;

import com.team81.facade.ISseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * Created by alon on 7/13/2020.
 */
@Controller
@RequestMapping(value = "/web/sse")
public class SseController {

    @Autowired
    private ISseFacade sseFacade;

    @PostMapping("/test")
    public
    @ResponseBody
    SseEmitter test() {
        SseEmitter sseEmitter = new SseEmitter();
        sseFacade.test(sseEmitter);
        return sseEmitter;
    }
}
