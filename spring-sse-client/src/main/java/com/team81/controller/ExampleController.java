package com.team81.controller;

import com.team81.RequestBody;
import com.team81.Response;
import com.team81.client.IPageRestClient;
import com.team81.client.IPagedRestClientFactory;
import com.team81.client.Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Duration;

/**
 * Created by alon on 7/13/2020.
 */
@Controller
@RequestMapping(value = "/web/sse-example")
public class ExampleController {

    private final IPagedRestClientFactory pagedRestClientFactory;

    @Autowired
    public ExampleController(IPagedRestClientFactory pagedRestClientFactory) {
        this.pagedRestClientFactory = pagedRestClientFactory;
    }

    @GetMapping
    public
    @ResponseBody
    String example() {
        IPageRestClient pagedRestClient = pagedRestClientFactory
                .getPagedRestClient(Protocol.HTTP, "localhost", 8081);
        pagedRestClient.postPageProcessing("/web/sse/example",
                                            new RequestBody("this is a body example"),
                                            System.out::println,
                                            Duration.ofSeconds(10),
                                            Response.class);
        return "Finished to process sse request";
    }

}
