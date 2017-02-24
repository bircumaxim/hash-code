package com.company.model;

/**
 * Created by nicu on 2/24/17.
 */
public class Video {
    private int id;
    private int size;

    public Video(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }
}
