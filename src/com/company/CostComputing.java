package com.company;

import com.company.model.CacheServer;
import com.company.model.EndPoint;
import com.company.model.RequestDescriptor;

/**
 * Created by nicu on 2/24/17.
 */
public class CostComputing {
    private Database database;

    public CostComputing(Database database) {
        this.database = database;
    }

    public int[][] getCacheServerToEndPointCost(){
        int cacheServersSize = getCacheServersSize();
        int endPointsSize = getEndPointsSize();
        int[][] cost = new int[cacheServersSize][endPointsSize];

        for (int cacheServerId = 0; cacheServerId < cacheServersSize; cacheServerId++) {
            CacheServer cacheServer = database.getCacheServers().get(cacheServerId);
            for (int endPointId = 0; endPointId < endPointsSize; endPointId++) {
                EndPoint endPoint = database.getEndPoints().get(endPointId);

                if (endPoint.getCacheServersLatency().containsKey(cacheServer)) {
                    int cacheServerLatency = endPoint.getCacheServersLatency().get(cacheServer);
                    cost[cacheServerId][endPointId] = endPoint.getDatacenterLatency() - cacheServerLatency;
                } else {
                    cost[cacheServerId][endPointId] = Integer.MAX_VALUE;
                }

            }
        }

        return cost;
    }

    public int[][] getCacheServerToEndPointCostForRequests(){
        int[][] cost = getCacheServerToEndPointCost();
        int[][] matrix = new int[cost.length][cost[0].length];

        for(RequestDescriptor requestDescriptor : database.getRequestDescriptors()){
            int count = requestDescriptor.getCount();
            EndPoint endPoint = requestDescriptor.getEndPoint();

            endPoint.getCacheServersLatency().forEach((cacheServer, integer) -> {
                int c = cost[cacheServer.getId()][endPoint.getId()];
                if (c < Integer.MAX_VALUE && c > 0)
                    matrix[cacheServer.getId()][endPoint.getId()] +=  c * count;
            });
        }

        return matrix;
    }

    private int getCacheServersSize() {
        return database.getCacheServers().size();
    }

    private int getEndPointsSize() {
        return database.getEndPoints().size();
    }


}
