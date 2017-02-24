package com.company.model;

/**
 * Created by nicu on 2/24/17.
 */
public class RequestDescriptor {
    private EndPoint endPoint;
    private Video video;
    private int count;

    public RequestDescriptor(EndPoint endPoint, Video video, int count) {
        this.endPoint = endPoint;
        this.video = video;
        this.count = count;
    }

    public EndPoint getEndPoint() {
        return endPoint;
    }

    public Video getVideo() {
        return video;
    }

    public int getCount() {
        return count;
    }

}
