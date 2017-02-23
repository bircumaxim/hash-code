package com.company;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nicu on 2/23/17.
 */
public class EndPoint {
    private int index;
    private int[] latency;
    private int dataServerLatency;
    private int cacheServers;
    private Set<Integer> connectionCacheServers = new HashSet<>();

    public EndPoint(int index,int dataServerLatency,int cacheServers) {
        this.index = index;
        this.cacheServers = cacheServers;
        this.latency = new int[cacheServers];
        this.dataServerLatency = dataServerLatency;
    }

    public int getDataServerLatency() {
        return dataServerLatency;
    }

    public void setLatency(int cacheServer, int latency){
        this.latency[cacheServer] = latency;
        connectionCacheServers.add(cacheServer);
    }

    public Set<Integer> getConnectionCacheServers() {
        return connectionCacheServers;
    }

    public int getLatency(int cacheServer){
        if (cacheServer >= latency.length)
            return Integer.MAX_VALUE;
        return latency[cacheServer];
    }

    public int getCacheServers() {
        return cacheServers;
    }
}
