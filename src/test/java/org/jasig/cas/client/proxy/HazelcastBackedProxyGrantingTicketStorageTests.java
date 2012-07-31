package org.jasig.cas.client.proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.IMap;

/**
 * @author Martin Snyman <martin_snyman@campuseai.org>
 * @since 1.0.0
 * 
 */
public class HazelcastBackedProxyGrantingTicketStorageTests {

    @Test
    public void testTicketRetrieval() {
        final IMap<String, String> hcache = Hazelcast
                .getMap(HazelcastBackedProxyGrantingTicketStorageImpl.HAZELCAST_CACHE_MAP_NAME);
        assertEquals(0, hcache.size());

        final HazelcastBackedProxyGrantingTicketStorageImpl cache = new HazelcastBackedProxyGrantingTicketStorageImpl();
        assertNull(cache.retrieve(null));
        assertNull(cache.retrieve("foobar"));

        cache.save("proxyGrantingTicketIou", "proxyGrantingTicket");
        assertEquals("proxyGrantingTicket", cache.retrieve("proxyGrantingTicketIou"));
        assertTrue("proxyGrantingTicket".equals(hcache.get("proxyGrantingTicketIou")));

    }
}
