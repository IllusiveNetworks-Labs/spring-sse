package com.team81.client;

import org.springframework.core.ParameterizedTypeReference;

import java.time.Duration;
import java.util.function.Consumer;

/**
 * Created by alon on 4/16/2020.
 */
public interface IPageRestClient {

    <Body, Response> void postPageProcessing(String path,
                                             Body body,
                                             Consumer<Response> consumer,
                                             Duration timeout,
                                             Class<Response> entityClass);

    <Body, Response> void postPageProcessing(String path,
                                             Body body,
                                             Consumer<Response> consumer,
                                             Duration timeout,
                                             ParameterizedTypeReference<Response> entityClass);
}
