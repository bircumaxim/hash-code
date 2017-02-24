package com.company;

import com.company.model.CacheServer;
import com.company.model.EndPoint;
import com.company.model.RequestDescriptor;
import com.company.model.Video;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by nicu on 2/24/17.
 */
public class DataHelper {
    public static void write(String filename, Database database) {
        PrintStream stream = null;
        try {
            stream  = new PrintStream(filename);
            List<CacheServer> cacheServersWithVideos = database.getCacheServers()
                    .stream()
                    .filter(cacheServer -> cacheServer.getStoredVideo().size() > 0)
                    .collect(Collectors.toList());

            stream.println(cacheServersWithVideos.size());
            cacheServersWithVideos.forEach(getCacheServerWriter(stream));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null)
                stream.close();
        }
    }

    private static Consumer<CacheServer> getCacheServerWriter(PrintStream stream) {
        return cacheServer -> {
            stream.print(cacheServer.getId());
            cacheServer.getStoredVideo().forEach(video -> stream.print(video.getId()+" "));
            stream.println();
        };
    }

    public static Database read(String filename) {
        Database database = null;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream(filename));
            database = read(scanner);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null)
                scanner.close();
        }
        return database;
    }

    private static Database read(Scanner scanner) {
        Database database;
        int videosNumber = scanner.nextInt();
        int endpointsNumber = scanner.nextInt();
        int requestsNumber = scanner.nextInt();
        int cacheServersNumber = scanner.nextInt();
        int cacheServerSize = scanner.nextInt();
        database = new Database(videosNumber, endpointsNumber, requestsNumber, cacheServersNumber, cacheServerSize);

        readVideos(scanner, database, videosNumber);
        readEndPoints(scanner, database, endpointsNumber);
        readRequests(scanner, database, requestsNumber);

        return database;
    }

    private static void readRequests(Scanner scanner, Database database, int requestsNumber) {
        for (int requestId = 0; requestId < requestsNumber; requestId++) {
            int videoId = scanner.nextInt();
            int endPointId = scanner.nextInt();
            int count = scanner.nextInt();
            RequestDescriptor requestDescriptor = new RequestDescriptor(database.getEndPoints().get(endPointId), database.getVideos().get(videoId), count);
            database.getRequestDescriptors().add(requestId,requestDescriptor);
        }
    }

    private static void readEndPoints(Scanner scanner, Database database, int endpointsNumber) {
        for (int endPointApi = 0; endPointApi < endpointsNumber; endPointApi++) {
            int datacenterLatency = scanner.nextInt();
            int cacheServerCount = scanner.nextInt();
            EndPoint endPoint = new EndPoint(endPointApi, datacenterLatency, cacheServerCount);
            database.getEndPoints().add(endPointApi, endPoint);
            for (int i = 0; i < cacheServerCount; i++) {
                int cacheServerId = scanner.nextInt();
                int cacheServerLatency = scanner.nextInt();
                CacheServer cacheServer = database.getCacheServers().get(cacheServerId);

                cacheServer.getEndPoints().put(endPoint,cacheServerLatency);
                endPoint.getCacheServersLatency().put(cacheServer, cacheServerLatency);
            }
        }
    }

    private static void readVideos(Scanner scanner, Database database, int videosNumber) {
        for (int i = 0; i < videosNumber; i++) {
            int videoSize = scanner.nextInt();
            database.getVideos().add(i, new Video(i, videoSize));
        }
    }
}
