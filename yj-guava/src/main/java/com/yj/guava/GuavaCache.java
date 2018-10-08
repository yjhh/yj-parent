package com.yj.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class GuavaCache {


    private LoadingCache<String,Object> cache;

    private Cache<String,Object> objectCache;

    public void init(){
        cache = CacheBuilder.newBuilder().expireAfterAccess(3,TimeUnit.MINUTES).maximumSize(10000L).build(new CacheLoader<String, Object>() {
            @Override
            public Object load(String key) {
                return null;
            }
        });

        objectCache = CacheBuilder.newBuilder().expireAfterAccess(3,TimeUnit.MINUTES).maximumSize(10000L).build();

    }

    public LoadingCache<String, Object> getCache() {
        return cache;
    }

    public void setCache(LoadingCache<String, Object> cache) {
        this.cache = cache;
    }

    public Cache<String, Object> getObjectCache() {
        return objectCache;
    }

    public void setObjectCache(Cache<String, Object> objectCache) {
        this.objectCache = objectCache;
    }
}
