package com.company.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nicu on 2/24/17.
 */
public class EndPoint {
    private int id;
    private int datacenterLatency;
    private Map<CacheServer,Integer> cacheServersLatency;

    public EndPoint(int id, int datacenterLatency,int cacheServersCount) {
        this.id = id;
        this.datacenterLatency = datacenterLatency;
        this.cacheServersLatency = new HashMap<>(cacheServersCount);
    }

    public int getId() {
        return id;
    }

    public int getDatacenterLatency() {
        return datacenterLatency;
    }

    public Map<CacheServer, Integer> getCacheServersLatency() {
        return cacheServersLatency;
    }
}
