package org.jasig.cas.client.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.IMap;

/**
 * @author Martin Snyman <martin_snyman@campuseai.org>
 * @since 1.0.0
 * 
 */
public class HazelcastBackedProxyGrantingTicketStorageImpl extends AbstractEncryptedProxyGrantingTicketStorageImpl {

    public static final String HAZELCAST_CACHE_MAP_NAME = "org.jasig.cas.client.proxy.HazelcastBackedProxyGrantingTicketStorageImpl.cache";

    protected static final Logger log = LoggerFactory.getLogger(HazelcastBackedProxyGrantingTicketStorageImpl.class);

    private final IMap<String, String> cache;

    /**
     * 
     */
    public HazelcastBackedProxyGrantingTicketStorageImpl() {
        this.cache = Hazelcast.getMap(HAZELCAST_CACHE_MAP_NAME);
        log.debug("Initialize with default cache named:" + this.cache.getName());
    }

    /**
     * 
     */
    public HazelcastBackedProxyGrantingTicketStorageImpl(final IMap<String, String> cache) {
        super();
        this.cache = cache;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jasig.cas.client.proxy.ProxyGrantingTicketStorage#cleanUp()
     */
    public void cleanUp() {
        // no op
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.jasig.cas.client.proxy.AbstractEncryptedProxyGrantingTicketStorageImpl#retrieveInternal(java.lang.String)
     */
    @Override
    protected String retrieveInternal(final String proxyGrantingTicketIou) {
        return proxyGrantingTicketIou == null ? null : cache.get(proxyGrantingTicketIou);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jasig.cas.client.proxy.AbstractEncryptedProxyGrantingTicketStorageImpl#saveInternal(java.lang.String,
     * java.lang.String)
     */
    @Override
    protected void saveInternal(final String proxyGrantingTicketIou, final String proxyGrantingTicket) {
        cache.put(proxyGrantingTicketIou, proxyGrantingTicket);
    }
}
