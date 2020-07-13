package com.team81.client;

/**
 * Created by alon on 7/13/2020.
 */
public interface IPagedRestClientFactory {

    IPageRestClient getPagedRestClient(Protocol protocol,
                                       String hostName,
                                       int port);
}
