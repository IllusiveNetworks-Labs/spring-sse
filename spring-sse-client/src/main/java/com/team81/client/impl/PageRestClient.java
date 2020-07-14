package com.team81.client.impl;

import com.team81.client.IPageRestClient;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * Created by alon on 4/16/2020.
 */
@Component
@Scope("prototype")
public class PageRestClient implements IPageRestClient {

    private static final Logger logger = LoggerFactory.getLogger(PageRestClient.class);

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private final URI baseUri;

    public PageRestClient(URI uri) {
        Validate.notNull(uri);

        baseUri = uri;
    }

    @Override
    public <Body, Response> void postPageProcessing(String path,
                                                    Body body,
                                                    Consumer<Response> consumer,
                                                    Duration timeout,
                                                    Class<Response> entityClass) {

        String url = UriComponentsBuilder.newInstance().uri(baseUri).build().toUriString();
        try {
            WebClient webClient = WebClient.builder().clientConnector(new ReactorClientHttpConnector()).baseUrl(url).build();

            SimpleResponseSubscriber<Response> subscriber = new SimpleResponseSubscriber<>(consumer);
            Scheduler scheduler = Schedulers.fromExecutor(executorService);
            webClient
                    .post()
                    .uri(path)
                    .body(Mono.just(body), new ParameterizedTypeReference<Body>() {})
                    .retrieve()
                    .bodyToFlux(entityClass)
                    .publishOn(scheduler)
                    .subscribe(subscriber);

            subscriber.await(timeout);
        } catch (Exception e) {
            logger.error("got exception while executing page request for url: {}", url, e);
        }

    }

    @Override
    public <Body, Response> void postPageProcessing(String path,
                                                    Body body,
                                                    Consumer<Response> consumer,
                                                    Duration timeout,
                                                    ParameterizedTypeReference<Response> entityClass) {

        String url = UriComponentsBuilder.newInstance().uri(baseUri).build().toUriString();
        try {
            WebClient webClient = WebClient.builder()
                    .clientConnector(new ReactorClientHttpConnector()).baseUrl(url).build();

            SimpleResponseSubscriber<Response> subscriber = new SimpleResponseSubscriber<>(consumer);
            Scheduler scheduler = Schedulers.fromExecutor(executorService);
            webClient
                .post()
                .uri(path)
                .body(Mono.just(body), new ParameterizedTypeReference<Body>() {})
                .retrieve()
                .bodyToFlux(entityClass)
                .publishOn(scheduler)
                .subscribe(subscriber);

            subscriber.await(timeout);
        } catch (Exception e) {
            logger.error("got exception while executing page request for url: {}", url, e);
        }
    }
}
