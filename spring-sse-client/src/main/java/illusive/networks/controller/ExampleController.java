package illusive.networks.controller;

import illusive.networks.RequestBody;
import illusive.networks.Response;
import illusive.networks.client.IPageRestClient;
import illusive.networks.client.IPagedRestClientFactory;
import illusive.networks.client.Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
        URI uri = UriComponentsBuilder.newInstance()
                .scheme(Protocol.HTTP.getValue())
                .host("localhost")
                .port(8081)
                .build()
                .toUri();
        IPageRestClient pagedRestClient = pagedRestClientFactory.getPagedRestClient(uri);
        pagedRestClient.postPageProcessing("/web/sse/example",
                                            new RequestBody("this is a body example"),
                                            System.out::println,
                                            Duration.ofSeconds(10),
                                            Response.class);
        return "Finished to process sse request";
    }

}
