package com.company;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);


        String[] files = {
                //"data",
                "kittens",
                //"me_at_the_zoo",
                //"trending_today",
                //"videos_worth_spreading"
        };

        //Arrays.stream(files).forEach(file -> executorService.submit(() -> solve(file)));

        solve("kittens");
    }

    private static void solve(String name) {
        System.out.println("Computing "+name);
        Data data = DataReader.read(name+".in");

        List<Relation> relations = Computing.getRelations(data);
        Collections.sort(relations,(a, b) -> (int)(b.getCost() - a.getCost()));

        Map<Integer,Set<Integer>> videoStored = new HashMap<>();
        Map<Integer,Set<Integer>> videoCachedForEndpoint = new HashMap<>();
        int[] usedSpace = new int[data.getNumberOfCacheServers()];

        for (Relation relation : relations){
            int cacheServer = relation.getCacheServer();
            int endPoint = relation.getRequest().getEndPoint();

            int videoId = relation.getRequest().getVideoId();
            int videoSize = data.getVideoSize(videoId);

            if (data.getCapacityOfCacheServer() - usedSpace[cacheServer] >= videoSize){
                Set<Integer> videosCachedForServer = videoStored.computeIfAbsent(cacheServer, it -> new HashSet<>());
                Set<Integer> videosCached = videoCachedForEndpoint.computeIfAbsent(endPoint, (it) -> new HashSet<>());
                if (!videosCached.contains(videoId) && !videosCachedForServer.contains(videoId)){
                    videosCached.add(videoId);
                    usedSpace[cacheServer] += videoSize;

                    videosCachedForServer.add(videoId);

                }
            }
        }

        List<Integer> shouldRemove = new ArrayList<>();
        videoStored.forEach((it,set) -> {
            if (set.size() == 0)
                shouldRemove.add(it);
        });

        shouldRemove.forEach(videoStored::remove);

        //print(relations, videoStored);

        DataWriter.write(name+".out",videoStored);
        System.out.println("Finished "+name);
    }

    private static void print(List<Relation> relations, Map<Integer, Set<Integer>> videoStored) {
        videoStored.forEach((cacheServer,videoIds) -> {
            System.out.print("Cache server "+cacheServer+" has ");
            videoIds.forEach(it -> System.out.print(it+" "));
            System.out.println();
        });

        System.out.println();
        relations.stream().forEach(System.out::println);
    }


}
