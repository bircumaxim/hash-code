package com.company;

/**
 * Created by nicu on 2/23/17.
 */
public class EndPoint {
    private int index;
    private int[] latency;
    private int dataServerLatency;
    private int cacheServers;

    public EndPoint(int index,int dataServerLatency,int cacheServers) {
        this.index = index;
        this.cacheServers = cacheServers;
        this.latency = new int[cacheServers];
        this.dataServerLatency = dataServerLatency;
    }

    public void setLatency(int cacheServer,int latency){
        this.latency[cacheServer] = latency;
    }

    public int getLatency(int cacheServer){
        return latency[cacheServer];
    }

    public int getCacheServers() {
        return cacheServers;
    }
}
