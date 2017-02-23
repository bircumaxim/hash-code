package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by nicu on 2/23/17.
 */
public class DataReader {
    public static Data read(String filename){
        Data data = new Data();
        try {
            Scanner scanner = new Scanner(new FileInputStream(filename));

            data.setNumberOfVideos(scanner.nextInt());
            data.setNumberOfEndpoints(scanner.nextInt());
            data.setNumberOfRequestDescriptions(scanner.nextInt());
            data.setNumberOfCacheServers(scanner.nextInt());
            data.setCapacityOfCacheServer(scanner.nextInt());

            for (int i = 0; i < data.getNumberOfVideos(); i++) {
                data.setVideoSize(i,scanner.nextInt());
            }

            for (int i = 0; i < data.getNumberOfEndpoints(); i++) {
                EndPoint endPoint = new EndPoint(i,scanner.nextInt(),scanner.nextInt());
                for (int j = 0; j < endPoint.getCacheServers(); j++) {
                    int cacheServer = scanner.nextInt();
                    int cacheLatency = scanner.nextInt();
                    endPoint.setLatency(cacheServer,cacheLatency);
                }
                data.setEndPoint(i,endPoint);
            }

            for (int i = 0; i < data.getNumberOfRequestDescriptions(); i++) {
                data.setRequest(i,new Request(scanner.nextInt(),scanner.nextInt(),scanner.nextInt()));
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
