package com.team81.client.impl;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Created by alon on 4/28/2020.
 */
public class SimpleResponseSubscriber<Response> implements Subscriber<Response> {

    private static final Logger logger = LoggerFactory.getLogger(SimpleResponseSubscriber.class);

    private final CountDownLatch latch = new CountDownLatch(1);
    private final Consumer<Response> consumer;

    public SimpleResponseSubscriber(Consumer<Response> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Response response) {
        logger.debug("got response: {}", response);
        consumer.accept(response);
    }

    @Override
    public void onError(Throwable throwable) {
        this.latch.countDown();
        logger.error("got error: {}", throwable.getMessage());
    }

    @Override
    public void onComplete() {
        this.latch.countDown();
        logger.debug("completed");
    }

    public void await(Duration duration) throws InterruptedException {
        this.latch.await(duration.toMillis(), TimeUnit.MILLISECONDS);
    }
}
