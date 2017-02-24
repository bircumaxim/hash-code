package com.company;

import com.company.model.CacheServer;
import com.company.model.EndPoint;
import com.company.model.RequestDescriptor;
import com.company.model.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by nicu on 2/24/17.
 */
public class Database {
    private List<Video> videos;
    private List<CacheServer> cacheServers;
    private List<RequestDescriptor> requestDescriptors;
    private List<EndPoint> endPoints;

    public Database(int videosNumber,int endpointsNumber,int requestsNumber,int cacheServersNumber,int cacheServerSize) {
        this.videos = new ArrayList<>(videosNumber);
        this.cacheServers = new ArrayList<>(cacheServersNumber);
        this.requestDescriptors = new ArrayList<>(requestsNumber);
        this.endPoints = new ArrayList<>(endpointsNumber);
        IntStream.range(0,cacheServersNumber)
                .forEach(val -> this.cacheServers.add(val,new CacheServer(val,cacheServerSize)));
    }

    public List<Video> getVideos() {
        return videos;
    }

    public List<CacheServer> getCacheServers() {
        return cacheServers;
    }

    public List<RequestDescriptor> getRequestDescriptors() {
        return requestDescriptors;
    }

    public List<EndPoint> getEndPoints() {
        return endPoints;
    }
}
