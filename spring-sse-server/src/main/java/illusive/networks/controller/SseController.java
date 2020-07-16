package illusive.networks.controller;

import illusive.networks.facade.ISseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * Created by alon on 7/13/2020.
 */
@Controller
@RequestMapping(value = "/web/sse")
public class SseController {

    private final ISseFacade sseFacade;

    @Autowired
    public SseController(ISseFacade sseFacade) {
        this.sseFacade = sseFacade;
    }

    @PostMapping("/example")
    public
    @ResponseBody
    SseEmitter example(@RequestBody illusive.networks.RequestBody requestBody) {
        System.out.println(String.format("got request with body: %s", requestBody));
        SseEmitter sseEmitter = new SseEmitter();
        sseFacade.example(sseEmitter);
        return sseEmitter;
    }
}
