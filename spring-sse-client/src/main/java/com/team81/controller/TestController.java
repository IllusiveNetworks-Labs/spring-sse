package com.team81.controller;

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
@RequestMapping(value = "/web/test")
public class TestController {

    @Autowired
    private IPagedRestClientFactory pagedRestClientFactory;

    @GetMapping
    public
    @ResponseBody
    String test() {
        IPageRestClient pagedRestClient = pagedRestClientFactory.getPagedRestClient(Protocol.HTTP, "localhost", 8081);
        pagedRestClient.postPageProcessing("/web/sse/test", "", System.out::println, Duration.ofSeconds(10), String.class);
        return "Finished to process sse request";
    }

}
