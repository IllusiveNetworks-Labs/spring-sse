package illusive.networks.client.impl;

import illusive.networks.client.IPageRestClient;
import illusive.networks.client.IPagedRestClientFactory;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * Created by alon on 7/13/2020.
 */
@Component
public class PagedRestClientFactory implements IPagedRestClientFactory {

    @Override
    @Lookup
    public IPageRestClient getPagedRestClient(URI uri) {
        return null;
    }
}
