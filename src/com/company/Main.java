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
                "data",
                "kittens",
                "me_at_the_zoo",
                "trending_today",
                "videos_worth_spreading"
        };

        Arrays.stream(files).forEach(file -> executorService.submit(() -> solve(file)));

        //solve("data");
    }

    private static void solve(String name) {
        System.out.println("Computing "+name);
        Data data = DataReader.read(name+".in");


        //print(relations, videoStored);

        Arrays.sort(data.getRequests(), new Comparator<Request>() {
            @Override
            public int compare(Request o1, Request o2) {
                return o2.getRequests() - o1.getRequests();
            }
        });

        int numberOfCacheServers = data.getNumberOfCacheServers();
        int numberOfEndpoints = data.getNumberOfEndpoints();

        List<List<Integer>> matrix = new ArrayList<>(numberOfCacheServers);

        for (int j = 0; j < numberOfCacheServers; j++) {
            ArrayList<Integer> element = new ArrayList<>();
            matrix.add(j, element);
            for (int i = 0; i < numberOfEndpoints; i++) {
                EndPoint endPoint = data.getEndPoint(i);
                int diff = endPoint.getDataServerLatency() - endPoint.getLatency(j);
                element.add(i,diff);
            }
        }

        int[] availableSpace = new int[numberOfCacheServers];
        for (int i = 0; i < numberOfCacheServers; i++) {
            availableSpace[i] = data.getCapacityOfCacheServer();
        }

        Map<Integer,Set<Integer>> videoPerCacheServer = new HashMap<>();

        for ( int requestId = 0;requestId< data.getNumberOfRequestDescriptions();requestId++) {
            Request request = data.getRequest(requestId);
            int endPointId = request.getEndPoint();
            EndPoint endPoint = data.getEndPoint(request.getEndPoint());
            int videoId = request.getVideoId();
            int videoSize = data.getVideoSize(videoId);


            int selectedServer = -1;
            int selectedServerRatio = -1;
            int size = matrix.size();
            for(int cacheServerId = 0; cacheServerId < size;cacheServerId++){

                if (!endPoint.getConnectionCacheServers().contains(cacheServerId))
                    continue;

                int ratio = matrix.get(cacheServerId).get(endPointId);

                if ((selectedServer == -1 || selectedServerRatio < ratio) && (availableSpace[cacheServerId] - videoSize >= 0)){
                    selectedServer = cacheServerId;
                    selectedServerRatio = ratio;
                }
            }

            if (selectedServer != -1){
                Set<Integer> videosForSelectedServer = videoPerCacheServer.computeIfAbsent(selectedServer, (val) -> new HashSet<Integer>());

                if (!videosForSelectedServer.contains(videoId)) {
                    availableSpace[selectedServer] -= videoSize;
                    if (availableSpace[selectedServer] == 0) {
                        matrix.remove(selectedServer);
                        System.out.println("Server is full. removing");
                    }

                    videosForSelectedServer.add(videoId);
                }
            }
        }

        DataWriter.write(name+".out",videoPerCacheServer);

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
