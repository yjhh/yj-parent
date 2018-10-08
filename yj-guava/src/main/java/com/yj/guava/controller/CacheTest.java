package com.yj.guava.controller;

import com.yj.guava.GuavaCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/cache")
public class CacheTest {

    private AtomicInteger atomicInteger = new AtomicInteger(Integer.MAX_VALUE);

    @Autowired
    private GuavaCache cache;

    @GetMapping("/set")
    public void setCache(){
        cache.getObjectCache().put("cache",atomicInteger.getAndIncrement());
        cache.getCache().put("cache",atomicInteger.getAndIncrement());
    }

    @GetMapping("/get")
    public void getCache() throws ExecutionException {
        Object o = cache.getObjectCache().getIfPresent("cache");
        System.out.println(o);
        Object o1 = cache.getCache().get("cache");
        System.out.println(o1);
    }

}
