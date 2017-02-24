package com.company.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nicu on 2/24/17.
 */
public class CacheServer {
    private int id;
    private int size;
    private Map<EndPoint,Integer> endPoints = new HashMap<>();
    private List<Video> storedVideo = new ArrayList<>();

    public CacheServer(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public Map<EndPoint,Integer> getEndPoints() {
        return endPoints;
    }

    public List<Video> getStoredVideo() {
        return storedVideo;
    }
}
