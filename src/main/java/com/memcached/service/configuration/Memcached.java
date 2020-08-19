package com.memcached.service.configuration;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.transcoders.SerializingTranscoder;

public class Memcached implements Cache{

    private String name;

    private MemcachedClient cache;

    private int expiration;
    
    public Memcached(String name,String memcachedAddresses, int expiration) throws IOException {
        this.name = name;
        this.expiration = expiration;
        cache = new MemcachedClient(
            new ConnectionFactoryBuilder()
                .setTranscoder(new SerializingTranscoder())
                .setProtocol(ConnectionFactoryBuilder.Protocol.BINARY)
                .build(),
            AddrUtil.getAddresses(memcachedAddresses));
    }

    public String getName() {
        return name;
    }

    public Object getNativeCache() {
        return cache;
    }

    public ValueWrapper get(final Object key) {
        Object value = null;
        try {
            value = cache.get(key.toString());
        } catch (final Exception e) {
        }
        if (value == null) {
            return null;
        }
        return new SimpleValueWrapper(value);
    }


    public void put(final Object key, final Object value) {
        if (value != null) {
            cache.set(key.toString(), expiration, value);
        }
    }


    public void evict(final Object key) {
        this.cache.delete(key.toString());
    }

    public void clear() {
        cache.flush();
    }

    public <T> T get(Object o, Class<T> aClass) {
        return null;
    }

    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    public ValueWrapper putIfAbsent(Object o, Object o1) {
        return null;
    }

}
