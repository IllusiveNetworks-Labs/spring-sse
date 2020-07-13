package com.team81.client.impl;

import com.team81.client.IPageRestClient;
import com.team81.client.IPagedRestClientFactory;
import com.team81.client.Protocol;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * Created by alon on 7/13/2020.
 */
@Component
public class PagedRestClientFactory implements IPagedRestClientFactory {

    @Override
    @Lookup
    public IPageRestClient getPagedRestClient(Protocol protocol, String hostName, int port) {
        return null;
    }
}
