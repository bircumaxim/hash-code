package com.company;

/**
 * Created by nicu on 2/23/17.
 */
public class Data {
    private int numberOfVideos;
    private int numberOfEndpoints;
    private int numberOfRequestDescriptions;
    private int numberOfCacheServers;
    private int capacityOfCacheServer;
    private int[] videoSize;
    private EndPoint[] dataPoints;
    private Request[] requests;

    public int getVideoSize(int index){
        return videoSize[index];
    }

    public void setVideoSize(int index,int size){
        videoSize[index] = size;
    }

    public void setNumberOfVideos(int numberOfVideos) {
        this.numberOfVideos = numberOfVideos;
        this.videoSize = new int[this.numberOfVideos];
    }

    public void setNumberOfEndpoints(int numberOfEndpoints) {
        this.numberOfEndpoints = numberOfEndpoints;
        this.dataPoints = new EndPoint[numberOfEndpoints];
    }

    public void setNumberOfRequestDescriptions(int numberOfRequestDescriptions) {
        this.numberOfRequestDescriptions = numberOfRequestDescriptions;
        this.requests = new Request[this.numberOfRequestDescriptions];
    }

    public void setNumberOfCacheServers(int numberOfCacheServers) {
        this.numberOfCacheServers = numberOfCacheServers;
    }

    public void setCapacityOfCacheServer(int capacityOfCacheServer) {
        this.capacityOfCacheServer = capacityOfCacheServer;
    }

    public int getNumberOfVideos() {
        return numberOfVideos;
    }

    public int getNumberOfEndpoints() {
        return numberOfEndpoints;
    }

    public int getNumberOfRequestDescriptions() {
        return numberOfRequestDescriptions;
    }

    public int getNumberOfCacheServers() {
        return numberOfCacheServers;
    }

    public int getCapacityOfCacheServer() {
        return capacityOfCacheServer;
    }

    public void setEndPoint(int id,EndPoint endPoint){
        dataPoints[id] = endPoint;
    }

    public EndPoint getEndPoint(int id){
        return dataPoints[id];
    }

    public void setRequest(int id,Request request){
        requests[id] = request;
    }

    public Request[] getRequests() {
        return requests;
    }

    public Request getRequest(int id){
        return requests[id];
    }
}
