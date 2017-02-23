package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicu on 2/23/17.
 */
public class Computing {
    public static List<Relation> getRelations(Data data){
        List<Relation> relations = new ArrayList<>();
        for (int requestId = 0; requestId < data.getNumberOfRequestDescriptions(); requestId++) {
            Request request = data.getRequest(requestId);
            EndPoint endPoint = data.getEndPoint(request.getEndPoint());

            Object[] servers = endPoint.getConnectionCacheServers().toArray();
            for (Object server : servers) {
                int cacheServerId = (Integer)server;
                int diffLatency = endPoint.getDataServerLatency() - endPoint.getLatency(cacheServerId);

                if (diffLatency > 0){
                    int cost = request.getRequests() * diffLatency;
                    relations.add(new Relation(cost,request,cacheServerId));
                }
            }
            System.out.println(relations.size());
        }

        return relations;
    }
}
