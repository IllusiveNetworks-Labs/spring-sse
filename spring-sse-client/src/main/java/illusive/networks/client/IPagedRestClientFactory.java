package illusive.networks.client;

import java.net.URI;

/**
 * Created by alon on 7/13/2020.
 */
public interface IPagedRestClientFactory {

    IPageRestClient getPagedRestClient(URI uri);
}
