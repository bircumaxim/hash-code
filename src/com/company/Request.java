package com.company;

/**
 * Created by nicu on 2/23/17.
 */
public class Request {
    private int videoId;
    private int requests;
    private int endPoint;

    public Request(int videoId, int endPoint,int requests) {
        this.videoId = videoId;
        this.requests = requests;
        this.endPoint = endPoint;
    }

    public int getVideoId() {
        return videoId;
    }

    public int getRequests() {
        return requests;
    }

    public int getEndPoint() {
        return endPoint;
    }

    @Override
    public String toString() {
        return "Request{" +
                "videoId=" + videoId +
                ", requests=" + requests +
                ", endPoint=" + endPoint +
                '}';
    }
}
